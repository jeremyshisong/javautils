package com.klx.spider.entity.drugsCate;

import java.util.List;

/**
 * Created by shisong on 16/11/29.
 */
public class DrugsCateMainModel {
    private String goodsCatgCode;
    private String goodsCatgName;
    private List<DrugsCateModel> o2oHealthAppsGoodsClassifyModels;

    public String getGoodsCatgCode() {
        return goodsCatgCode;
    }

    public void setGoodsCatgCode(String goodsCatgCode) {
        this.goodsCatgCode = goodsCatgCode;
    }

    public String getGoodsCatgName() {
        return goodsCatgName;
    }

    public void setGoodsCatgName(String goodsCatgName) {
        this.goodsCatgName = goodsCatgName;
    }

    public List<DrugsCateModel> getO2oHealthAppsGoodsClassifyModels() {
        return o2oHealthAppsGoodsClassifyModels;
    }

    public void setO2oHealthAppsGoodsClassifyModels(List<DrugsCateModel> o2oHealthAppsGoodsClassifyModels) {
        this.o2oHealthAppsGoodsClassifyModels = o2oHealthAppsGoodsClassifyModels;
    }
}
