package com.nlp.repository.dao;

import com.nlp.repository.bean.Item;

import java.util.List;

public interface ItemsDao {
    List<Item> selectAllItems(int start, int limit);
    List<String> selectAllCates();
}
