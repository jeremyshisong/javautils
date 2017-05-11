package com.meizu.nlp.classification.repository.dao;

import com.meizu.nlp.classification.repository.bean.Item;

import java.util.List;

public interface ItemsDao {
    List<Item> selectAllItems(int start, int limit);
    List<String> selectAllCates();
}
