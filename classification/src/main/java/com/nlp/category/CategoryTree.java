package com.nlp.category;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shisong on 17/4/27.
 */
public class CategoryTree {
    private CategoryNode root;///树根

    public CategoryTree() {
        root = new CategoryNode();
    }


    public CategoryNode getRoot() {
        return root;
    }

    public void insert(List<String> strCates) {
        if (strCates == null || strCates.size() == 0) {
            return;
        }
        for (String str : strCates) {
            String[] cates = null;
            try {
                cates = str.split(",");
            } catch (Exception e) {
            }

            this.insert(this.root, cates);
        }

    }

    /**
     * 插入字串，用循环代替迭代实现
     *
     * @param root
     * @param cates
     */
    private void insert(CategoryNode root, String[] cates) {
        if (cates == null || cates.length == 0) {
            return;
        }
        for (int i = 0; i < cates.length; i++) {
            Map<String, CategoryNode> childs = root.getChilds();
            Set childsWords = childs.keySet();

            CategoryNode thisNode;

            String thisTitle = cates[i];

            if (childsWords.contains(cates[i])) {
                thisNode = childs.get(thisTitle);
            } else {
                thisNode = new CategoryNode(thisTitle);
                thisNode.setLevel(i);
                childs.put(thisTitle, thisNode);
            }

            if (i == cates.length - 1) {
                thisNode.setLeaf(true);
                break;
            }
            ///root指向子节点，继续处理
            root = thisNode;
        }
    }


    /**
     * 类型树节点
     */
    public class CategoryNode {
        /**
         * 类型标题
         */
        private String title = "";
        /**
         * 所处的层级
         */
        private Integer level = -1;
        /**
         * 是否叶子节点
         */
        boolean isLeaf = false;
        /**
         * 下层节点
         */
        Map<String, CategoryNode> childs = new LinkedHashMap<String, CategoryNode>();

        public CategoryNode() {
        }

        public CategoryNode(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public boolean isLeaf() {
            return isLeaf;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        public Map<String, CategoryNode> getChilds() {
            return childs;
        }

        public void setChilds(Map<String, CategoryNode> childs) {
            this.childs = childs;
        }
    }

}
