package com.chan.nl.crawl.url;

/**
 * Created by shisong on 17/5/15.
 */
public class UrlFetch {
    private static String NEWS_163 = "http://news.163.com/rank/";
    private static String NEWS_UC = "http://news.uc.cn/c_redian/";
    private static String NEWS_UC_ARTICLE = "http://iflow.uczzd.cn/iflow/api/v1/channel/51830095?method=his&ftime=1494839881258&count=20&summary=0&bid=999&m_ch=000&uc_param_str=dnnivebichfrmintnwcpgieiwidsudpf&zzd_from=UCtoutiaoPC-iflow&app=UCtoutiaoPC-iflow&client_os=UCtoutiaoPC-iflow&fr=pc";

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

    public static String getNewsUCArticle() {
        return NEWS_UC_ARTICLE;
    }
}
