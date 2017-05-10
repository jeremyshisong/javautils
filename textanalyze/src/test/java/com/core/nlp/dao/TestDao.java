package com.core.nlp.dao;

import com.core.nlp.entity.Item;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by shisong on 17/4/21.
 */
public class TestDao {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
            "classpath:spring-context.xml");

    @Test
    public void testDao() {
        GoodsDao goodsDao = ctx.getBean("goodsDao", GoodsDao.class);
        List<Item> goods = goodsDao.selectAllItems(1, 10);
        System.out.println(goods.size());

    }
}
