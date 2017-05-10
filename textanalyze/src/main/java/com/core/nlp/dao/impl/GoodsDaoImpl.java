package com.core.nlp.dao.impl;

import com.core.nlp.dao.GoodsDao;
import com.core.nlp.entity.Item;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("goodsDao")
public class GoodsDaoImpl implements GoodsDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private static final Logger LOG = Logger.getLogger(GoodsDaoImpl.class);

    @Override
    public List<Item> selectAllItems(int start, int limit) {
        HashMap params = new HashMap();
        params.put("start", start);
        params.put("limit", limit);
        List<Item> items = sqlSessionTemplate.selectList(GoodsDaoImpl.class.getName() + ".selectAllItems", params);
        return items;
    }

}
