package com.chan.nl.crawl.download;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by shisong on 17/5/15.
 */
public class DownLoad {

    public Document getDoc(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public String getJson(String url) throws ClientProtocolException, IOException {
        String ret = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)   //socket超时
                .setConnectTimeout(5000)   //connect超时
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String code = String.valueOf(response.getStatusLine().getStatusCode());
            ret = EntityUtils.toString(response.getEntity(), "utf-8");
            //System.out.println(html);
        } catch (IOException e) {
            System.out.println("Connection timeout");
        }
        return ret;
    }

}
