package com.chan.nl.crawl.url;

/**
 * Created by shisong on 17/5/15.
 */
public class UrlFetch {
    private static String NEWS_163 = "http://news.163.com/rank/";
    private static String NEWS_UC = "http://news.uc.cn/c_redian/";
    private static String NEWS_QQ = "http://news.qq.com/paihang.htm";

    public static String getNews163() {
        return NEWS_163;
    }

    public static String getNewsUC() {
        return NEWS_UC;
    }

    public static String getNewsQQ() {
        return NEWS_QQ;
    }
}
