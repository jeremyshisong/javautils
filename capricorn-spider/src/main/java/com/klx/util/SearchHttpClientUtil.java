package com.klx.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component("searchHttpClientUtil")
public class SearchHttpClientUtil {
    private static final Logger LOG = Logger.getLogger(SearchHttpClientUtil.class);
    private CloseableHttpClient httpClient;
    private int timeout = 30000;


    @PostConstruct
    public void init() {
        timeout = 30000;
        httpClient = HttpClients.createDefault();
    }


    public SearchHttpClientUtil() {
        timeout = 30000;
        httpClient = HttpClients.createDefault();
    }


    public String getSyncData(HttpGet httpGet) throws Exception {
        String strResult = null;
        CloseableHttpResponse response = null;
        try {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();
            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            if (true) {
                HttpEntity entity = response.getEntity();
                strResult = EntityUtils.toString(entity, "utf-8");
            } else {
                throw new Exception("获取数据异常####HttpStatus=" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            LOG.warn("getDataFromApi error " + e.getMessage());
            if (httpGet != null) {
                httpGet.abort();
            }
            throw e;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        if (StringUtils.isNotBlank(strResult)) {
            strResult = strResult.trim();
        }
        return strResult;
    }


    public String getSyncData(HttpPost httpGet) throws Exception {
        String strResult = null;
        CloseableHttpResponse response = null;
        try {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();
            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            if (true) {
                HttpEntity entity = response.getEntity();
                strResult = EntityUtils.toString(entity, "utf-8");
            } else {
                throw new Exception("获取数据异常####HttpStatus=" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            LOG.warn("getDataFromApi error " + e.getMessage());
            if (httpGet != null) {
                httpGet.abort();
            }
            throw e;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        if (StringUtils.isNotBlank(strResult)) {
            strResult = strResult.trim();
        }
        return strResult;
    }
}
