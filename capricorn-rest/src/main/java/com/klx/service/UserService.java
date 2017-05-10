package com.klx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.klx.domain.SessionBean;
import com.klx.utils.HttpClientMrg;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shisong on 17/1/8.
 */
@Service("userService")
public class UserService {

    @Autowired
    private HttpClientMrg httpClientMrg;

    @Autowired
    private SessionService sessionService;

    private static String appId = "wxd0bbd8a0de4f859e";

    private static String secret = "565e4d981b28d5294f3530aad53c4712";

    private static final Logger logger = Logger.getLogger(UserService.class);

    public String getOpenId(String token) {
        SessionBean sessionBean = sessionService.getByKey(token);
        if (sessionBean == null) {
            return null;
        }

        return sessionBean.getOpenId();
    }


    public String getToken(String code) {
        Map error = new HashMap();
        error.put("errcode", 40029);
        error.put("errmsg", "invalid code");
        if (StringUtils.isBlank(code)) {
            return JSON.toJSONString(error);
        }

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";

        try {
            String res = httpClientMrg.request(url, 3000);
            String openId = parse(res);
            if (StringUtils.isBlank(openId)) {
                return JSON.toJSONString(error);
            }

            SessionBean sessionBean = sessionService.getByOpenId(openId);

            if (sessionBean != null && !sessionBean.isExpired()) {
                sessionBean.setTtl(System.currentTimeMillis());
                return sessionBean.getToken();
            } else {
                sessionBean = sessionService.add(openId);
                return sessionBean.getToken();
            }

        } catch (Exception e) {
            return JSON.toJSONString(error);
        }
    }

    private String parse(String res) {
        try {
            JSONObject object = JSON.parseObject(res);
            String openId = object.getString("openid");
            return StringUtils.isBlank(openId) ? null : openId;
        } catch (Exception e) {
            return null;
        }

    }
}
