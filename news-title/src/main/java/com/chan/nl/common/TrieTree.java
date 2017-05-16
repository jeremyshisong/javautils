package com.chan.nl.common;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by shisong on 16/11/3.
 */

public class TrieTree {
    public class TrieNode {

        /**
         * 结点关键字，其值为中文词中的一个字
         */
        private char key = (char) 0;
        /**
         * 如果该字在词语的末尾，则bound=true
         */
        private boolean bound = false;

        private int dumps = 0;
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

        public int getDumps() {
            return dumps;
        }

        public void setDumps(int dumps) {
            this.dumps = dumps;
        }

        public void addDumps() {
            this.dumps++;
        }

        public Map<Character, TrieNode> getChilds() {
            return childs;
        }
    }


    private TrieNode root;///树根

    private int wordCount = 0;

    public TrieTree() {
        ///初始化trie 树
        root = new TrieNode();
    }

    public int getWordCount() {
        return wordCount;
    }

    public void addWordCount() {
        this.wordCount++;
    }


    /**
     * 插入字串，用循环代替迭代实现
     *
     * @param words
     */
    public void insert(String words) {
        if (StringUtils.isNoneBlank(words)) {
            insert(this.root, words);
        }
    }

    public Map<String, Integer> indexTags(String words) {
        Map<String, Integer> map = preTraversalSearch(words);
        return mergeTags(map);
    }

    public Map<String, Integer> queryTag(String words) {
        Map<String, Integer> map = preTraversalSearch(words);
        map = maxLengthTags(map);
        return map;

    }


    /**
     * 遍历Trie树，查找所有的words以及出现次数
     *
     * @return HashMap<String, Integer> map
     */
    public Map<String, Integer> getAllWords() {
        return preTraversal(this.root, "");
    }


    /**
     * 判断某字串是否在字典树中
     *
     * @param word
     * @return true if exists ,otherwise  false
     */
    public boolean isExist(String word) {
        return search(this.root, word);
    }


    /**
     * 前缀遍历字典树
     *
     * @param num 节点重复数过滤值
     * @return
     */

    public Map<String, Integer> prefixs(int num) {
        return preTraversal(this.root, num, "");
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

        boolean preLetter = true;
        boolean nextLetter = true;

        for (int i = 0; i < chrs.length; i++) {
            Map<Character, TrieNode> childs = root.getChilds();
            Set childsWords = childs.keySet();

            TrieNode thisNode;

            Character thisChar = chrs[i];

            if (thisChar < 'a' || thisChar > 'z') {
                preLetter = false;
            }


            if (childsWords.contains(chrs[i])) {
                thisNode = childs.get(thisChar);
            } else {
                thisNode = new TrieNode(thisChar);
                childs.put(thisChar, thisNode);
            }

            if (preLetter) {
                if (i < chrs.length - 1) {
                    char nextChar = chrs[i + 1];
                    if (nextChar < 'a' || nextChar > 'z') {
                        nextLetter = false;
                    }
                } else {
                    nextLetter = false;
                }
            }

            if (!preLetter || !nextLetter) {
                thisNode.addDumps();
            }

            if (i == chrs.length - 1) {
                thisNode.setBound(true);
                this.addWordCount();
                break;
            }

            ///root指向子节点，继续处理
            root = thisNode;
        }

    }


    /**
     * 前序遍历。。。
     *
     * @param root    子树根节点
     * @param prefixs 查询到该节点前所遍历过的前缀
     * @return
     */
    private Map<String, Integer> preTraversal(TrieNode root, String prefixs) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        if (root != null) {

            if (root.isBound()) {
                //当前即为一个单词
                map.put(prefixs, root.getDumps());
            }

            for (Map.Entry<Character, TrieNode> entry : root.getChilds().entrySet()) {
                TrieNode node = entry.getValue();
                Character ch = node.key;
                ////递归调用前序遍历
                String tempStr = prefixs + ch;
                map.putAll(preTraversal(node, tempStr));

            }
        }

        return map;
    }

    /**
     * 前缀遍历词典树获取中心词
     *
     * @param words query or index name
     * @return
     */
    private Map<String, Integer> preTraversalSearch(String words) {
        words = words.toLowerCase();
        if (StringUtils.isBlank(words)) {
            return new HashMap<String, Integer>();
        }

        TrieNode root = this.root;

        char[] chrs = words.toCharArray();


        Map<String, Integer> ret = new HashMap<String, Integer>();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < chrs.length; i++) {
            Map<Character, TrieNode> childs = root.getChilds();
            Set childsWords = childs.keySet();

            TrieNode thisNode;

            Character thisChar = chrs[i];
            buffer.append(thisChar);

            if (childsWords.contains(chrs[i])) {
                thisNode = childs.get(thisChar);
                if (thisNode.dumps > 1 && buffer.length() > 1) {
                    ret.put(buffer.toString(), thisNode.dumps);
                }
            } else {
                return ret;
            }
            ///root指向子节点，继续处理
            root = thisNode;
        }

        return ret;

    }


    /**
     * 查询某字串是否在字典树中
     *
     * @param word
     * @return true if exists ,otherwise  false
     */
    private boolean search(TrieNode root, String word) {
        if (StringUtils.isBlank(word)) {
            return false;
        }
        char[] chs = word.toLowerCase().toCharArray();
        for (int i = 0, length = chs.length; i < length; i++) {
            Character ch = chs[i];
            Set<Character> childSet = root.getChilds().keySet();
            TrieNode childNode;
            if (!childSet.contains(ch)) {
                ///如果不存在，则查找失败
                return false;
            } else {
                childNode = root.getChilds().get(ch);
            }

            root = childNode;
        }

        return true;
    }


    private Map<String, Integer> preTraversal(TrieNode root, int num, String prefixs) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        if (root != null) {

            if (root.dumps >= num && prefixs.length() > 1 || root.dumps > 2 && prefixs.length() >= 4) {
                //当前即为一个单词
                map.put(prefixs, root.dumps);
            }

            for (Map.Entry<Character, TrieNode> entry : root.getChilds().entrySet()) {
                TrieNode node = entry.getValue();
                Character ch = node.key;
                ////递归调用前序遍历
                String tempStr = prefixs + ch;
                map.putAll(preTraversal(node, num, tempStr));

            }
        }

        return map;
    }


    private Map<String, Integer> mergeTags(Map<String, Integer> tags) {
        if (tags == null || tags.size() == 0) {
            return tags;
        }

        List<String> dumps = new ArrayList<String>();
        List<String> keys = new ArrayList<String>(tags.keySet());

        Collections.sort(keys, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

        for (int i = 0, length = keys.size(); i < length; i++) {
            String s = keys.get(i);

            if (endsWith(s, "[A-Za-z0-9]")) {
                if (i < length - 1) {
                    String next = keys.get(i + 1);
                    if (endsWith(next, "[A-Za-z0-9]")) {
                        dumps.add(s);
                    }
                }
            }
        }

        for (String s : dumps) {
            tags.remove(s);
        }

        return tags;
    }


    private Map<String, Integer> maxLengthTags(Map<String, Integer> tags) {
        if (tags == null || tags.size() == 0) {
            return tags;
        }

        List<String> keys = new ArrayList<String>(tags.keySet());

        Collections.sort(keys, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

        Map<String, Integer> ret = new HashMap<String, Integer>();

        String maxLengthKey = keys.get(keys.size() - 1);
        ret.put(maxLengthKey, tags.get(maxLengthKey));

        return ret;

    }

    private boolean endsWith(String words, String reg) {
        if (StringUtils.isBlank(words)) {
            return false;
        }

        char[] chrs = words.toCharArray();
        char ends = chrs[chrs.length - 1];

        String s = "" + ends;

        return s.matches(reg);
    }


}