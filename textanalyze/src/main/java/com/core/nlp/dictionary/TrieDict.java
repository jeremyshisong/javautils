package com.core.nlp.dictionary;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by shisong on 16/11/3.
 */

public class TrieDict {
    private TrieNode root;///树根

    public TrieDict() {
        root = new TrieNode();
    }


    /**
     * 从','分割的字符串中加载词典
     *
     * @param list
     */
    public TrieDict(String list) {
        if (StringUtils.isBlank(list)) {
            return;
        }

        List<String> lst;

        try {
            lst = Arrays.asList(list.split(","));
            if (list == null || lst.size() == 0) {
                return;
            }
            root = new TrieNode();
            for (String name : lst) {
                this.insert(name);
            }
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 从list数组中加载词典
     *
     * @param list
     */

    public TrieDict(List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        root = new TrieNode();
        for (String name : list) {
            this.insert(name);
        }
    }


    /**
     * 从字典文件中加载词典
     *
     * @param path
     */
    public void loadDict(String path) {
        root = new TrieNode();
        InputStream is = this.getClass().getResourceAsStream(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            long s = System.currentTimeMillis();
            while (br.ready()) {
                String line = br.readLine();
                this.insert(line);
            }
            System.out.println(String.format(Locale.getDefault(), "main dict load finished, time elapsed %d ms",
                    System.currentTimeMillis() - s));
        } catch (IOException e) {
            System.err.println(String.format(Locale.getDefault(), "%s load failure!", path));
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (IOException e) {
                System.err.println(String.format(Locale.getDefault(), "%s close failure!", path));
            }
        }
    }

    /**
     * 插入字串，用循环代替迭代实现
     *
     * @param words
     */
    private void insert(String words) {
        if (StringUtils.isNoneBlank(words)) {
            insert(this.root, words);
        }
    }

    public String subContains(String sentence) {
        if (StringUtils.isBlank(sentence)) {
            return null;
        }

        for (int i = 0; i < sentence.length(); i++) {
            String subString = sentence.substring(i);
            String subCheck = search(this.root, subString);
            if (StringUtils.isNoneBlank(subCheck)) {
                return subCheck;
            }
        }

        return null;

    }


    public List<String> segments(String sentence) {
        if (StringUtils.isBlank(sentence)) {
            return null;
        }

        List<String> ret = new ArrayList<String>();

        for (int i = 0; i < sentence.length(); i++) {
            String subString = sentence.substring(i);
            String subCheck = search(this.root, subString);
            if (StringUtils.isNoneBlank(subCheck)) {
                ret.add(subCheck);
            }
        }

        return ret;

    }

    /**
     * 插入字串，用循环代替迭代实现
     *
     * @param root
     * @param words
     */
    private void insert(TrieNode root, String words) {
        words = words.toLowerCase();
        if (StringUtils.isBlank(words)) {
            return;
        }
        char[] chrs = words.toCharArray();

        for (int i = 0; i < chrs.length; i++) {
            Map<Character, TrieNode> childs = root.getChilds();
            Set childsWords = childs.keySet();

            TrieNode thisNode;

            Character thisChar = chrs[i];

            if (childsWords.contains(chrs[i])) {
                thisNode = childs.get(thisChar);
            } else {
                thisNode = new TrieNode(thisChar);
                childs.put(thisChar, thisNode);
            }

            if (i == chrs.length - 1) {
                thisNode.setBound(true);
                break;
            }

            ///root指向子节点，继续处理
            root = thisNode;
        }

    }

    /**
     * 查询某字串是否在字典树中
     *
     * @param word
     * @return true if exists ,otherwise  false
     */
    private String search(TrieNode root, String word) {
        if (StringUtils.isBlank(word)) {
            return null;
        }
        char[] chs = word.toLowerCase().toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, length = chs.length; i < length; i++) {
            Character ch = chs[i];
            buffer.append(ch);
            Set<Character> childSet = root.getChilds().keySet();
            TrieNode childNode;
            if (!childSet.contains(ch)) {
                return null;
            } else {
                childNode = root.getChilds().get(ch);
                if (childNode.isBound()) {
                    return buffer.toString();
                }
            }

            root = childNode;
        }

        if (root.isBound()) {
            return buffer.toString();
        } else {
            return null;
        }
    }

    public class TrieNode {

        /**
         * 结点关键字，其值为中文词中的一个字
         */
        private char key = (char) 0;
        /**
         * 如果该字在词语的末尾，则bound=true
         */
        private boolean bound = false;

        /**
         * 指向下一个结点的指针结构，用来存放当前字在词中的下一个字的位置
         */
        private Map<Character, TrieNode> childs = new LinkedHashMap<Character, TrieNode>();

        public TrieNode() {
        }

        public TrieNode(char k) {
            this.key = k;
        }

        public char getKey() {
            return key;
        }

        public void setKey(char key) {
            this.key = key;
        }

        public boolean isBound() {
            return bound;
        }

        public void setBound(boolean bound) {
            this.bound = bound;
        }

        public Map<Character, TrieNode> getChilds() {
            return childs;
        }
    }
}