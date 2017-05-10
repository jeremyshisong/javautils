package com.klx.service;

import com.klx.domain.SessionBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by shisong on 17/1/8.
 */
@Service("sessionService")
public class SessionService {

    private Map<String, SessionBean> session;
    private Map<String, String> openIds;

    private int size = 5000;

    @PostConstruct
    public void init() {
        session = new HashMap<String, SessionBean>(size);
        openIds = new HashMap<String, String>(size);
    }

    public SessionBean add(String openId) {
        SessionBean bean = new SessionBean();
        bean.setOpenId(openId);
        bean.setExpired(false);
        bean.setTtl(System.currentTimeMillis());
        String token = String.valueOf(UUID.randomUUID());
        bean.setToken(token);

        openIds.put(openId, token);
        return bean;
    }

    public SessionBean getByKey(String key) {
        return session.get(key);
    }


    public SessionBean getByOpenId(String openId) {
        if (!openIds.containsKey(openId)) {
            return null;
        }
        return session.get(openIds.get(openId));
    }

    public boolean remove(String key) {
        if (session.keySet().contains(key)) {
            SessionBean bean = session.get(key);
            String openId = bean.getOpenId();
            openIds.remove(openId);
            session.remove(key);
            return true;
        }
        return false;
    }
}
