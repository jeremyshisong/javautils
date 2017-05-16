package com.chan.nl.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by shisong on 16/11/3.
 */
public class ValueComparator implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Map.Entry<String, Integer> m, Map.Entry<String, Integer> n) {
        return n.getValue() - m.getValue();
    }
}