package com.klx.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/**
 * Created by shisong on 16/11/28.
 */
public class InitReqHead {
    private static String cateUrl = "http://apps.o2o.w.yf.156156.com/openx/app/appgoodsmanager/findGoodsClassifyInfo";
    private static String goodsUrl = "http://apps.o2o.w.yf.156156.com/openx/app/appgoodsmanager/findBiStoreGoodsInfo";

    public static HttpGet initCateHead() {
        HttpGet httpGet = new HttpGet(cateUrl);
        JSONObject object = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("customerCode", "");
        head.put("devAppVersion", "1.6.2");
        head.put("devSysVersion", "6.0");
        head.put("devType", "3");
        head.put("latitude", "28.1883");
        head.put("longitude", "113.091");
        head.put("onlyCode", "WDee8ORJnggDAMXEz9KH2E7P");
        head.put("umengCode", "AsDlYVRsMFX3EggOTdxtWywDTbaft6D_i5dTVdAdRzLu");
        object.put("head", head);
        String _openx_head = object.toJSONString();
        httpGet.setHeader("_openx_head", _openx_head);
        return httpGet;
    }

    public static HttpPost initStoreGoodsHead(String cateId,int pageNum) {
        HttpPost httpPost = new HttpPost(goodsUrl);
        JSONObject head = new JSONObject();
        head.put("customerCode", "");
        head.put("devAppVersion", "1.6.2");
        head.put("devSysVersion", "6.0");
        head.put("devType", "3");
        head.put("latitude", "28.1883");
        head.put("longitude", "113.091");
        head.put("onlyCode", "WDee8ORJnggDAMXEz9KH2E7P");
        head.put("umengCode", "AsDlYVRsMFX3EggOTdxtWywDTbaft6D_i5dTVdAdRzLu");

        String strHead = head.toJSONString();

        JSONObject headObject = new JSONObject();
        headObject.put("head", strHead);

        JSONObject model = new JSONObject();
        model.put("cityCode", "430100");
        model.put("goodsCatgCode", cateId);
        model.put("limit", "10");
        model.put("page", pageNum);
        model.put("storeCode", "5857");

        JSONObject httpBody = new JSONObject();
        httpBody.put("o2oHealthAppsReqGoodsModel", model);
        httpBody.put("_openx_head", headObject);


        HttpEntity entity;
        try {
            entity = new StringEntity(httpBody.toJSONString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return httpPost;
    }
}
