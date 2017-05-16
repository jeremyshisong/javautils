package com.meizu.nlp.classification.category;

import com.hankcs.lucene.HanLPIndexAnalyzer;
import com.meizu.nlp.classification.ClassificationResult;
import com.meizu.nlp.classification.SimpleNaiveBayesClassifier;
import com.meizu.nlp.classification.constant.Categorys;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.SlowCompositeReaderWrapper;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 17/4/25.
 */
@Service("classify")
public class Classify {
    public static final String INDEX = "/data/indexs";
    public static final String[] CATEGORIES = Categorys.CATEGORIES;
    private static int[][] counts;
    private static Map<String, Integer> catindex;

    private Analyzer defaultAnalyzer = new StandardAnalyzer();
    private Analyzer hanLPIndexAnalyzer = new HanLPIndexAnalyzer();
    private Analyzer witespaceAnalyzer = new WhitespaceAnalyzer();

    private PerFieldAnalyzerWrapper analyzerWrapper;

    private Map<String, Analyzer> fieldAnalyzers;

    private SimpleNaiveBayesClassifier classifier;

    private IndexReader reader;
    private LeafReader ar;

    private static final Logger logger = Logger.getLogger(Classify.class);


    @PostConstruct
    public void initFieldAnalyzers() throws IOException {
        logger.info("init fieldAnalyzers start...");

        fieldAnalyzers = new HashMap<String, Analyzer>();
        fieldAnalyzers.put("title", hanLPIndexAnalyzer);
        fieldAnalyzers.put("cate", witespaceAnalyzer);
        analyzerWrapper = new PerFieldAnalyzerWrapper(defaultAnalyzer, fieldAnalyzers);

        train();

        logger.info("init fieldAnalyzers finished...");

    }

    private void train() throws IOException {
        logger.info("classify model train start...");

        init();

        classifier = new SimpleNaiveBayesClassifier();
        reader = DirectoryReader.open(dir());
        ar = SlowCompositeReaderWrapper.wrap(reader);

        classifier.train(ar, "title", "cate", analyzerWrapper);

        logger.info("classify model train finished...");
    }

    public String classify(String inputDocument) throws IOException {
        ClassificationResult<BytesRef> result = classifier.assignClass(inputDocument);
        return result.getAssignedClass().utf8ToString();
    }


    public ClassificationResult<BytesRef> assignClass(String inputDocument) throws IOException {
        return classifier.assignClass(inputDocument);
    }

    public List<ClassificationResult<BytesRef>> getClasses(String inputDocument, int max) throws IOException {
        return classifier.getClasses(inputDocument, max);
    }

    public void verify() throws Exception {
        final long startTime = System.currentTimeMillis();
        final int maxdoc = reader.maxDoc();
        logger.info("maxdoc = [" + maxdoc + "];");

        for (int i = 0; i < maxdoc; i++) {
            Document doc = ar.document(i);
            String correctAnswer = doc.get("cate");

            logger.info("classifier doc cate=[" + correctAnswer + "]; title=[" + doc.get("title") + "]");

            final int cai = idx(correctAnswer);
            ClassificationResult<BytesRef> result = classifier.assignClass(doc.get("title"));
            String classified = result.getAssignedClass().utf8ToString();
            logger.info("classifier cate=[" + classified + "]");

            final int cli = idx(classified);
            counts[cai][cli]++;
        }
        final long endTime = System.currentTimeMillis();
        final int elapse = (int) (endTime - startTime) / 1000;

        // print results
        int fc = 0, tc = 0;
        for (int i = 0; i < CATEGORIES.length; i++) {
            for (int j = 0; j < CATEGORIES.length; j++) {
                System.out.printf(" %3d ", counts[i][j]);
                if (i == j) {
                    tc += counts[i][j];
                } else {
                    fc += counts[i][j];
                }
            }
        }
        float accrate = (float) tc / (float) (tc + fc);
        float errrate = (float) fc / (float) (tc + fc);
        System.out.printf("\n\n*** accuracy rate = %f, error rate = %f; time = %d (sec); %d docs\n", accrate, errrate, elapse, maxdoc);

        reader.close();
    }

    static Directory dir() throws IOException {
        return FSDirectory.open(Paths.get(INDEX));
    }

    static void init() {
        counts = new int[CATEGORIES.length][CATEGORIES.length];
        catindex = new HashMap<String, Integer>();
        for (int i = 0; i < CATEGORIES.length; i++) {
            catindex.put(CATEGORIES[i], i);
        }
    }

    static int idx(String cat) {
        Integer index = catindex.get(cat);
        if (index == null) {
            logger.info("###ERROR###cat name= [" + cat + "]");
            return 0;
        } else
            return index.intValue();
    }
}

