package com.chan.nl.analyse;

import com.chan.nl.crawl.analyse.PageAnalyse;
import com.chan.nl.crawl.analyse.impl.NetEasePageAnalyse;
import com.chan.nl.crawl.analyse.impl.QQPageAnalyse;
import com.chan.nl.crawl.analyse.impl.UCPageAnalyse;
import com.chan.nl.crawl.download.DownLoad;
import com.chan.nl.crawl.url.UrlFetch;
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
        String html = downLoad.getHTML(net163);

        List<String> results = netEasePageAnalyse.analyse(html);
        System.out.println("size="+results.size());
        for (String title : results) {
            System.out.println(title);
        }
    }


    @Test
    public void testUCPageAnalyse() throws IOException {
        DownLoad downLoad = new DownLoad();
        PageAnalyse ucPageAnalyse = new UCPageAnalyse();
        String newsUC = UrlFetch.getNewsUC();
        String html = downLoad.getHTML(newsUC);

        List<String> results = ucPageAnalyse.analyse(html);

        for (String title : results) {
            System.out.println(title);
        }
    }


    @Test
    public void testQQPageAnalyse() throws IOException {
        DownLoad downLoad = new DownLoad();
        PageAnalyse qqPageAnalyse = new QQPageAnalyse();
        String newsQQ = UrlFetch.getNewsQQ();
        String html = downLoad.getHTML(newsQQ);

        List<String> results = qqPageAnalyse.analyse(html);

        System.out.println("size="+results.size());


        for (String title : results) {
            System.out.println(title);
        }
    }

}
