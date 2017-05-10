package com.klx;

import com.klx.spider.entity.drugs.DrugsEntity;
import com.klx.spider.entity.drugsCate.DrugsCateEntity;
import com.klx.praser.impl.CatePraseHander;
import com.klx.praser.impl.DrugsPraseHander;
import com.klx.spider.DrugsCateSpider;
import com.klx.spider.DrugsSpider;

/**
 * Created by shisong on 16/11/30.
 */
public class SpiderTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        DrugsCateSpider cateSpider = new DrugsCateSpider();
        DrugsSpider goodsSpider = new DrugsSpider();
        //RequstHander requstHander = new RequstHander();
        //String drugs = requstHander.drugs();
        String cate = cateSpider.spider();
        String goods = goodsSpider.spider("a",1);

        CatePraseHander catePraseHander = new CatePraseHander();
        DrugsPraseHander goodsPraseHander = new DrugsPraseHander();
        DrugsCateEntity goodCateEntity = catePraseHander.prase(cate);
        DrugsEntity goodsEntity = goodsPraseHander.prase(goods);

        //System.out.println(drugsCate);
        System.out.println(cate);
        System.out.println(goods);
    }
}
