package com.chan.nl.crawl.analyse.impl;

import com.chan.nl.crawl.analyse.PageAnalyse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 17/5/15.
 */
public class UCPageAnalyse implements PageAnalyse {
    public List<String> analyse(String html) {
        List<String> ret = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements titles = doc.select("div.txt-area-title");
        for (Element td : titles) {
            Element news;
            try {
                news = td.getElementsByTag("a").first();
                String url = news.attr("href");
                String title = news.attr("title");
                ret.add(title);
            } catch (Exception e) {
                continue;
            }
        }
        return ret;
    }
}
