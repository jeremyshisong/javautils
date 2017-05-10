package com.klx.spider;

import com.klx.util.RequstHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shisong on 16/11/29.
 */
@Service("drugsCateSpider")
public class DrugsCateSpider {
    @Autowired
    private RequstHander requstHander;

    public DrugsCateSpider() {
        this.requstHander = new RequstHander();
    }

    public String spider() {
        return requstHander.drugsCate();
    }
}
