package com.klx.praser.impl;

import com.alibaba.fastjson.JSON;
import com.klx.praser.PraserHander;
import com.klx.spider.entity.drugsCate.DrugsCateEntity;
import org.springframework.stereotype.Service;

/**
 * Created by shisong on 16/11/29.
 */
@Service("catePraseHander")
public class CatePraseHander implements PraserHander {
    public CatePraseHander() {
    }

    public DrugsCateEntity prase(String json) {
        return JSON.parseObject(json, DrugsCateEntity.class);
    }
}
