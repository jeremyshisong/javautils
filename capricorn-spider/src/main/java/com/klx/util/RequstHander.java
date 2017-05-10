package com.klx.util;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shisong on 16/11/28.
 */
@Service("requstHander")
public class RequstHander {
    @Autowired
    private SearchHttpClientUtil searchHttpClientUtil;

    public RequstHander() {
        this.searchHttpClientUtil = new SearchHttpClientUtil();
    }


    private String doRequest(HttpGet httpGet) {
        try {
            return searchHttpClientUtil.getSyncData(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String doRequest(HttpPost httpGet) {
        try {
            return searchHttpClientUtil.getSyncData(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String drugsCate() {
        HttpGet httpGet = InitReqHead.initCateHead();
        return doRequest(httpGet);
    }

    public String drugs(String cateId, int pageNum) {
        HttpPost httpGet = InitReqHead.initStoreGoodsHead(cateId,pageNum);
        return doRequest(httpGet);
    }
}
