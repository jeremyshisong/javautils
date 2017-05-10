package com.nlp.category;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 17/4/27.
 */
public class TestCategory {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
            "classpath:classify-spring-context.xml");

    @Test
    public void testCategoryTree() throws Exception {
        CategoryTree categoryTree = new CategoryTree();
        List<String> strCates = new ArrayList<String>();
        strCates.add("鞋靴,流行男鞋,定制鞋");
        strCates.add("鞋靴,流行男鞋,工装鞋");
        categoryTree.insert(strCates);
    }
}
