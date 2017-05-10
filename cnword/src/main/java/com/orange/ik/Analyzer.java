package com.orange.ik;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shisong on 16/12/28.
 */
public class Analyzer {

    public List<String> read(String filePath) {
        if ((filePath == null || filePath.trim().length() == 0)) {
            System.out.println("file name is empty");
            return new ArrayList<String>();
        }
        List<String> ret = new ArrayList<String>();
        BufferedReader in = null;

        String line = "";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            in = new BufferedReader(isr);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            line = in.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        while (line != null) {
            ret.add(line);
            try {
                line = in.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ret;
    }

    public void write(String readPath, List<String> list) {
        if (readPath == null || readPath.trim().length() == 0) {
            return;
        }

        String writePath = readPath + "_a";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(writePath));
            for (String s : list) {
                out.write(s);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public List<String> doAnlizy(List<String> appNames) {
        List<String> set = new ArrayList<String>();
        for (String name : appNames) {
            Set<String> results = this.nameSegment(name);
            if (results != null && results.size() > 0) {
                for (String s : results) {
                    if (!set.contains(s)) {
                        set.add(s);
                    }
                }
            }
        }
        return set;
    }

    private Set<String> nameSegment(String param) {
        Set<String> set = new HashSet<String>();
        if (!StringUtils.isEmpty(param)) {
            //构建IK分词器，不使用smart分词模式
            org.apache.lucene.analysis.Analyzer analyzer = new IKAnalyzer(false);

            //获取Lucene的TokenStream对象
            TokenStream ts = null;
            try {
                ts = analyzer.tokenStream("myField", new StringReader(param));
                //获取词元文本属性
                CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);

                //重置TokenStream（重置StringReader）
                ts.reset();
                //迭代获取分词结果
                while (ts.incrementToken()) {
                    //保留长度大于1的分词结果
                    String retTmp = term.toString();
                    if (retTmp != null && term.length() > 1) {
                        set.add(retTmp);
                    }
                }
                //关闭TokenStream（关闭StringReader）
                ts.end();   // Perform end-of-stream operations, e.g. set the final offset.

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //释放TokenStream的所有资源
                if (ts != null) {
                    try {
                        ts.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return set;
    }

}
