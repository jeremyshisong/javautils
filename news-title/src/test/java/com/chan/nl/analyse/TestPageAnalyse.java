package com.chan.nl.analyse;

import com.alibaba.fastjson.JSON;
import com.chan.nl.common.Article;
import com.chan.nl.crawl.analyse.PageAnalyse;
import com.chan.nl.crawl.analyse.impl.NetEasePageAnalyse;
import com.chan.nl.crawl.analyse.impl.QQPageAnalyse;
import com.chan.nl.crawl.analyse.impl.UCPageAnalyse;
import com.chan.nl.crawl.download.DownLoad;
import com.chan.nl.crawl.url.UrlFetch;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by shisong on 17/5/15.
 */
public class TestPageAnalyse {
    @Test
    public void test163PageAnalyse() throws IOException {
        DownLoad downLoad = new DownLoad();
        PageAnalyse netEasePageAnalyse = new NetEasePageAnalyse();
        String net163 = UrlFetch.getNews163();
        Document doc = downLoad.getDoc(net163);

        List<String> results = netEasePageAnalyse.analysis(doc);
        System.out.println("size=" + results.size());
        for (String title : results) {
            System.out.println(title);
        }
    }


    @Test
    public void testUCPageAnalyse() throws IOException {
        DownLoad downLoad = new DownLoad();
        PageAnalyse ucPageAnalyse = new UCPageAnalyse();
        String newsUC = UrlFetch.getNewsUC();
        Document doc = downLoad.getDoc(newsUC);

        List<String> results = ucPageAnalyse.analysis(doc);

        for (String title : results) {
            System.out.println(title);
        }
    }

    @Test
    public void testUCPageParse() throws IOException {
        DownLoad downLoad = new DownLoad();
        UCPageAnalyse ucPageAnalyse = new UCPageAnalyse();
        String newsUC = UrlFetch.getNewsUCArticle();
        String json = downLoad.getJson(newsUC);

        List<Article> articles = ucPageAnalyse.parse(json);

        System.out.println("size="+articles.size());

        System.out.println(JSON.toJSONString(articles));
    }


    @Test
    public void testQQPageAnalyse() throws IOException {
        DownLoad downLoad = new DownLoad();
        PageAnalyse qqPageAnalyse = new QQPageAnalyse();
        String newsQQ = UrlFetch.getNewsQQ();
        Document doc = downLoad.getDoc(newsQQ);

        List<String> results = qqPageAnalyse.analysis(doc);

        System.out.println("size=" + results.size());


        for (String title : results) {
            System.out.println(title);
        }
    }

}
