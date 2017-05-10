package com.chan.nl;


import com.chan.nl.common.TrieTree;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by shisong on 16/11/2.
 */
public class Process {
    private String regEx;

    public Process() {
        regEx = "[·`~!@#$%^&*()=+|{}':;',\\[\\].<>/?~！@#￥%……&*（）——|{}【】‘；：”“’。，、？]";
    }

    public List<String> split(String s) {
        List<String> ret = new ArrayList<String>();
        if (StringUtils.isBlank(s)) {
            return ret;
        }

        String[] arrayStr = s.split(regEx);
        return Arrays.asList(arrayStr);
    }

    public void putMap(Map<String, Integer> map, List<String> list) {
        if (map == null || list == null || list.size() == 0) {
            return;
        }

        for (String s : list) {
            s = s.trim();
            if (map.containsKey(s)) {
                Integer count = map.get(s);
                count++;
                map.put(s, count);
            } else {
                map.put(s, 1);
            }
        }

    }

    public TrieTree loadTrieTree(Map<String, Integer> map) {
        if (map == null || map.size() == 0) {
            return null;
        }

        Set<String> names = map.keySet();

        if (names == null || names.size() == 0) {
            return null;
        }

        TrieTree tree = new TrieTree();

        for (String name : names) {
            tree.insert(name);
        }

        return tree;

    }
}
