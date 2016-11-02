package com.label;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by shisong on 16/6/27.
 */
public class Search {
    public static void main(String[] args) throws Exception {
        Search search = new Search();
        Map<String, String> resluts = search.getResults("p图");
        System.out.println("");
    }

    private CloseableHttpClient httpClient;

    public Search() {
        this.httpClient = HttpClients.createDefault();
    }

    public Map<String, String> getResults(String keyword) {
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> ret = new LinkedHashMap<String, String>();
        HttpGet httpget = new HttpGet("http://aso100.com/app/baseinfo/appid/414478124/country/cn");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            return ret;
        }
        HttpEntity entity = response.getEntity();
        String html = null;
        try {
            html = EntityUtils.toString(entity);
        } catch (IOException e) {
            return ret;
        }
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByClass("app-desc");
        String name;

        for (Element element : elements) {
            Elements elName = element.getElementsByClass("name");
            if (elName != null && elName.size() > 0) {
                Element element1 = elName.get(0);
                name = element1.attr("title");
                ret.put(name, "");
            }
        }
        return ret;
    }


    public String getAppId(String keyword) {
        String ret = "";
        String keyword0 = keyword;
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ret;
        }
        HttpGet httpget = new HttpGet("http://aso100.com/search?search=" + keyword);
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            return ret;
        }
        HttpEntity entity = response.getEntity();
        String html;
        try {
            html = EntityUtils.toString(entity);
        } catch (IOException e) {
            return ret;
        }
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByClass("media-heading");

        for (Element element : elements) {
            Elements elName = element.getAllElements();
            int first = 0;
            if (elName != null && elName.size() > 0) {
                Element element1 = elName.get(1);
                String ids = element1.attr("href");
                String names = element1.text();
                String name = getName(names);
                String id = getId(ids);
                if (!StringUtils.isEmpty(name)) {
                    if (keyword0.equals(name) || name.contains(keyword0) || first == 0) {
                        return id;
                    }
                }
            }
        }
        return ret;
    }

    public String getAppReleaseDate(String appId) {
        String ret = "";
        try {
            appId = URLEncoder.encode(appId, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ret;
        }
        HttpGet httpget = new HttpGet("http://aso100.com/app/baseinfo/appid/" + appId + "/country/cn");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            return ret;
        }
        HttpEntity entity = response.getEntity();
        String html;
        try {
            html = EntityUtils.toString(entity);
        } catch (IOException e) {
            return ret;
        }
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("td");
        String date = "";
        if (elements != null && elements.size() > 6) {
            try {
                date = elements.get(5).text();
            } catch (Exception e) {

            }
        }
        return date;
    }

    private String getId(String url) {
        if (!StringUtils.isEmpty(url)) {
            try {
                String[] strings = url.split("/");
                if (strings.length > 0) {
                    return strings[4];
                }
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

    private String getName(String url) {
        if (!StringUtils.isEmpty(url)) {
            try {
                String[] strings = url.split("、");
                if (strings.length > 0) {
                    return strings[1];
                }
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }
}
