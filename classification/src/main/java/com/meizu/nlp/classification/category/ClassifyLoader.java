package com.meizu.nlp.classification.category;

import com.meizu.nlp.classification.repository.dao.ItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by shisong on 17/4/27.
 */
@Service("classifyLoader")
public class ClassifyLoader {
    @Autowired
    private ItemsDao itemsDao;

    private CategoryTree tree;

    @PostConstruct
    public void init() {
        tree = new CategoryTree();
        initTree();
    }

    public void initTree() {
        List<String> cates = itemsDao.selectAllCates();
        tree.insert(cates);
    }

    public CategoryTree getTree() {
        return tree;
    }
}
