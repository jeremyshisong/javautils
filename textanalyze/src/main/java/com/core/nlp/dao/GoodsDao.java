package com.core.nlp.dao;

import com.core.nlp.entity.Item;

import java.util.List;

public interface GoodsDao {

    List<Item> selectAllItems(int start, int limit);
}
