package com.nlp.repository.dao;

import com.nlp.repository.bean.Item;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository("itemsDao")
public class ItemsDaoImpl implements ItemsDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private static final Logger LOG = Logger.getLogger(ItemsDaoImpl.class);

    @Override
    public List<Item> selectAllItems(int start, int limit) {
        HashMap params = new HashMap();
        params.put("start", start);
        params.put("limit", limit);
        List<Item> items = sqlSessionTemplate.selectList(ItemsDaoImpl.class.getName() + ".selectAllItems", params);
        whitespace(items);
        return items;
    }

    @Override
    public List<String> selectAllCates() {
        List<String> items = sqlSessionTemplate.selectList(ItemsDaoImpl.class.getName() + ".selectAllCates");
        whitespace2(items);
        return items;
    }

    private void whitespace(List<Item> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        for (Item item : items) {
            String cate0 = item.getCate();
            try {
                String cate = cate0.replace(" ", "");
                item.setCate(cate);
                if (!cate0.equals(cate)) {
                    System.out.println("checked");
                }
            } catch (Exception e) {
            }
        }
    }

    private void whitespace2(List<String> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        List<String> ret = new ArrayList<String>();
        for (String item : items) {
            String cate0 = item;
            try {
                String cate = cate0.replace(" ", "");
                ret.add(cate);
                if (!cate0.equals(cate)) {
                    System.out.println("checked");
                }
            } catch (Exception e) {
            }
        }
        items.clear();
        items.addAll(ret);
    }
}
