package com.meizu.nlp.classification.indexing;

import com.hankcs.lucene.HanLPIndexAnalyzer;
import com.meizu.nlp.classification.repository.bean.Item;
import com.meizu.nlp.classification.repository.dao.ItemsDao;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 17/4/25.
 */
@Service("fileIndexWriter")
public class FileIndexWriter {
    public static final String INDEX = "/data/indexs";
    private Analyzer defaultAnalyzer = new StandardAnalyzer();
    private Analyzer hanLPIndexAnalyzer = new HanLPIndexAnalyzer();
    private Analyzer witespaceAnalyzer = new WhitespaceAnalyzer();
    private PerFieldAnalyzerWrapper analyzerWrapper;
    private IndexWriter indexWrite;
    private Directory directory;
    private Map<String, Analyzer> fieldAnalyzers;
    @Autowired
    private ItemsDao itemsDao;


    private void initFieldAnalyzers() {
        System.out.println("init fieldAnalyzers start...");
        fieldAnalyzers = new HashMap<String, Analyzer>();
        fieldAnalyzers.put("title", hanLPIndexAnalyzer);
        fieldAnalyzers.put("cate", witespaceAnalyzer);
        analyzerWrapper = new PerFieldAnalyzerWrapper(defaultAnalyzer, fieldAnalyzers);
        System.out.println("init fieldAnalyzers finished...");
    }

    @PostConstruct
    public void init() {
        initFieldAnalyzers();
        //indexwriter 配置信息
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzerWrapper);
        //索引的打开方式，没有索引文件就新建，有就打开
        indexWriterConfig.setOpenMode(OpenMode.CREATE);
        try {
            //指定索引硬盘存储路径
            directory = FSDirectory.open(Paths.get(INDEX));
            //指定所以操作对象indexWrite
            indexWrite = new IndexWriter(directory, indexWriterConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("FileIndexWriter init finished");
    }


    public void writeIndex() {
        boolean hasMore = true;
        List<Item> itemList;
        int start = 0;
        int limit = 1000;
        System.out.println("start write index...");

        while (hasMore) {
            itemList = itemsDao.selectAllItems(start, limit);
            List<Document> documents = initDocuments(itemList);
            try {
                indexWrite.addDocuments(documents);
                System.out.println("add documents start = [" + start + "] ;size=[" + documents.size() + "]");
                start += limit;
                hasMore = itemList.size() > 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将indexWrite操作提交，如果不提交，之前的操作将不会保存到硬盘
        try {
            //这一步很消耗系统资源，所以commit操作需要有一定的策略
            indexWrite.commit();
            //关闭资源
            indexWrite.close();
            directory.close();
            System.out.println("write finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Document> initDocuments(List<Item> items) {
        List<Document> documents = new ArrayList<Document>();
        for (Item item : items) {
            documents.add(initDocument(item));
        }

        return documents;
    }

    private Document initDocument(Item item) {
        TextField title = textField("title", item.getTitle());
        TextField cate = textField("cate", item.getCate());
        List<TextField> list = new ArrayList<TextField>();
        list.add(title);
        list.add(cate);
        return document(list);
    }


    private Document document(List<TextField> textFields) {
        if (textFields == null || textFields.size() == 0) {
            return null;
        }
        Document document = new Document();
        for (TextField textField : textFields) {
            document.add(textField);
        }

        return document;
    }

    private TextField textField(String fieldName, String text) {
        return new TextField(fieldName, text, Field.Store.YES);
    }
}

