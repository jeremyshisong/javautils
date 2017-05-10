/**
 * Project: galaxy-video
 * <p/>
 * File Created at 2015-4-18
 * $Id$
 */
package com.klx.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * TODO Comment of HttpClientMrgUtil
 *
 * @author shisong
 */
@Service("httpClientMrg")
public class HttpClientMrg {

    private int maxTotal = 20;
    private int defMaxPerRoute = 20;

    private CloseableHttpClient httpClient;
    private PoolingHttpClientConnectionManager connMgr;
    private static final Logger logger = Logger.getLogger(HttpClientMrg.class);


    @PostConstruct
    public void init() throws KeyManagementException, NoSuchAlgorithmException {
        connMgr = initCM();
    }

    public String request(String url, int timeout) throws Exception {
        String strResult = null;
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setHeader("Connection", "Keep-Alive");
            RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();
            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                strResult = EntityUtils.toString(entity);
            } else {
                throw new Exception("获取数据异常####HttpStatus=" + response.getStatusLine().getStatusCode() + ";req url=" + url);
            }
        } catch (Exception e) {
            logger.warn("getDataFromApi error " + e.getMessage());
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


    private PoolingHttpClientConnectionManager initCM() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslcontext = createIgnoreVerifySSL();
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
                .getSocketFactory();
        LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("http", plainsf)
                .register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
                registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(defMaxPerRoute);
        //HttpHost httpHost = new HttpHost(host, 80);
        // 将目标主机的最大连接数增加
        //cm.setMaxPerRoute(new HttpRoute(httpHost), maxPerRoute);
        return cm;
    }

    /**
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }


}