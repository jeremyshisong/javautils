package com.chan.nl.crawl.analyse.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chan.nl.common.Article;
import com.chan.nl.crawl.analyse.PageAnalyse;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shisong on 17/5/15.
 */
public class UCPageAnalyse implements PageAnalyse {
    public List<String> analysis(Document doc) {
        System.out.println(doc);
        List<String> ret = new ArrayList<String>();
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

    public List<Article> parse(String json) {

        List<Article> ret = new ArrayList<Article>();
        if (StringUtils.isBlank(json)) {
            return ret;
        }


        try {
            JSONObject obj = JSONObject.parseObject(json);
            JSONObject data = obj.getJSONObject("data");

            JSONArray items = data.getJSONArray("items");

            JSONObject articles = data.getJSONObject("articles");

            for (Iterator it = items.iterator(); it.hasNext(); ) {
                JSONObject item = (JSONObject) it.next();

                String id = item.getString("id");

                JSONObject art_obj = articles.getJSONObject(id);

                Article article = new Article();

                article.setId(id);
                article.setTitle(art_obj.getString("title"));
                article.setUC_url(art_obj.getString("url"));
                article.setOriginal_url(art_obj.getString("original_url"));

                ret.add(article);
            }

        } catch (Exception e) {

        }

        return ret;
    }

}
