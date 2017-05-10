package com.klx.praser.impl;

import com.alibaba.fastjson.JSON;
import com.klx.praser.PraserHander;
import com.klx.spider.entity.drugs.DrugsEntity;
import org.springframework.stereotype.Service;

/**
 * Created by shisong on 16/11/29.
 */
@Service("drugsPraseHander")
public class DrugsPraseHander implements PraserHander {
    public DrugsEntity prase(String json) {
        return JSON.parseObject(json, DrugsEntity.class);
    }
}
