package com.klx.spider;

import com.klx.util.RequstHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shisong on 16/11/29.
 */
@Service("drugsSpider")
public class DrugsSpider {
    @Autowired
    private RequstHander requstHander;

    public String spider(String cateId, int pageNum) {
        return requstHander.drugs(cateId, pageNum);
    }
}
