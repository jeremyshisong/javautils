/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meizu.nlp.classification.ClassificationResult;
import com.meizu.nlp.classification.Classify;
import com.meizu.nlp.classification.category.CategoryTree;
import com.meizu.nlp.classification.category.ClassifyLoader;
import org.apache.lucene.util.BytesRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * TODO Comment of BookService
 *
 * @author shisong
 */
@Service("classifyService")
public class ClassifyService {
    @Autowired
    private Classify classify;
    @Autowired
    private ClassifyLoader classifyLoader;

    public String classify(String keyword) throws IOException {
        String result = classify.classify(keyword);
        return result;
    }

    public void verify() throws Exception {
        classify.verify();
    }

    public String assignClass(String keyword) throws IOException {
        ClassificationResult<BytesRef> result = classify.assignClass(keyword);
        JSONObject ret = new JSONObject();
        ret.put("Class",result.getAssignedClass().utf8ToString());
        ret.put("Score",result.getScore());
        return ret.toJSONString();
    }

    public String getClasses(String keyword, int max) throws IOException {
        List<ClassificationResult<BytesRef>> result = classify.getClasses(keyword, max);
        return JSON.toJSONString(result);
    }

    public String treeHtml() {
        CategoryTree.CategoryNode root = classifyLoader.getTree().getRoot();
        StringBuffer buffer = new StringBuffer();
        String start = "<ul id=\"browser\" class=\"filetree treeview-famfamfam\">\n";
        String end = "</ul>";

        buffer.append(start);

        buffer.append(covert(root));
        buffer.append(end);

        return buffer.toString();

    }

    private String covert(CategoryTree.CategoryNode root) {
        String li = "<li class=\"closed\">";
        String _li = "</li>";
        String ul = "<ul>";
        String _ul = "</ul>";
        StringBuffer ret = new StringBuffer();

        if (root.getLevel() != -1) {
            ret.append(li);
            ret.append(root.getTitle());
        }


        Map<String, CategoryTree.CategoryNode> childs = root.getChilds();
        if (childs != null && childs.size() > 0) {
            if (root.getLevel() != -1) {
                ret.append(ul);
            }
            for (Map.Entry<String, CategoryTree.CategoryNode> entry : childs.entrySet()) {
                ret.append(covert(entry.getValue()));
            }
            if (root.getLevel() != -1) {
                ret.append(_ul);
            }
        }

        if (root.getLevel() != -1) {
            ret.append(_li);
        }

        return ret.toString();

    }

}
