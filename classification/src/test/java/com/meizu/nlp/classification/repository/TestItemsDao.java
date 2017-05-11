package com.meizu.nlp.classification.repository;

import com.meizu.nlp.classification.repository.bean.Item;
import com.meizu.nlp.classification.repository.dao.ItemsDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by shisong on 17/4/25.
 */
public class TestItemsDao {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
            "classpath:classify-spring-context.xml");

    @Test
    public void testDao() {
        ItemsDao itemsDao = ctx.getBean("itemsDao", ItemsDao.class);
        List<Item> items = itemsDao.selectAllItems(1, 10);
        System.out.println(items.size());
    }

    @Test
    public void testCateDao() {
        ItemsDao itemsDao = ctx.getBean("itemsDao", ItemsDao.class);
        List<String> items = itemsDao.selectAllCates();
        System.out.println(items.size());
    }
}
