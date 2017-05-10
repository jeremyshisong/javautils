package com.klx.storage.dao;

import com.klx.praser.impl.CatePraseHander;
import com.klx.spider.DrugsCateSpider;
import com.klx.spider.entity.drugsCate.DrugsCateEntity;
import com.klx.spider.entity.drugsCate.DrugsCateMainModel;
import com.klx.spider.entity.drugsCate.DrugsCateModel;
import com.klx.storage.vo.SecDrugsCate;
import com.klx.storage.vo.TopDrugsCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 16/11/30.
 */
@Repository("drugsCateDao")
public class DrugsCateDao {

    @Autowired
    private DrugsCateSpider cateSpider;
    @Autowired
    private CatePraseHander catePraseHander;

    public List<TopDrugsCate> loadTopCate() {
        List<TopDrugsCate> ret;
        String cateJSON = cateSpider.spider();
        DrugsCateEntity goodCateEntity = catePraseHander.prase(cateJSON);
        ret = parseTopCate(goodCateEntity);
        return ret;
    }

    public List<SecDrugsCate> loadSecCate(DrugsCateEntity goodCateEntity) {
        return parseSecCate(goodCateEntity);
    }


    public List<SecDrugsCate> loadSecCate() {
        String cateJSON = cateSpider.spider();
        DrugsCateEntity goodCateEntity = catePraseHander.prase(cateJSON);
        return parseSecCate(goodCateEntity);
    }

    private List<TopDrugsCate> parseTopCate(DrugsCateEntity goodCateEntity) {
        List<TopDrugsCate> topDrugsCates = new ArrayList<TopDrugsCate>();

        if (goodCateEntity == null) {
            return topDrugsCates;
        }

        List<DrugsCateMainModel> goodsCateMainModels = goodCateEntity.getO2oHealthAppsGoodsClassifyModels();

        if (goodsCateMainModels == null || goodsCateMainModels.size() == 0) {
            return topDrugsCates;
        }

        for (DrugsCateMainModel goodsCateMainModel : goodsCateMainModels) {
            TopDrugsCate topDrugsCate = new TopDrugsCate();

            topDrugsCate.setCode(goodsCateMainModel.getGoodsCatgCode());
            topDrugsCate.setName(goodsCateMainModel.getGoodsCatgName());

            topDrugsCates.add(topDrugsCate);

        }

        return topDrugsCates;

    }

    private List<SecDrugsCate> parseSecCate(DrugsCateEntity goodCateEntity) {
        List<SecDrugsCate> secDrugsCategories = new ArrayList<SecDrugsCate>();

        if (goodCateEntity == null) {
            return secDrugsCategories;
        }

        List<DrugsCateMainModel> goodsCateMainModels = goodCateEntity.getO2oHealthAppsGoodsClassifyModels();

        if (goodsCateMainModels == null || goodsCateMainModels.size() == 0) {
            return secDrugsCategories;
        }

        for (DrugsCateMainModel goodsCateMainModel : goodsCateMainModels) {
            String upperCateCode = goodsCateMainModel.getGoodsCatgCode();


            List<DrugsCateModel> goodsClassifyModels = goodsCateMainModel.getO2oHealthAppsGoodsClassifyModels();

            if (goodsClassifyModels == null || goodsClassifyModels.size() == 0) {
                continue;
            }

            for (DrugsCateModel goodsCateModel : goodsClassifyModels) {
                SecDrugsCate secDrugsCategory = new SecDrugsCate();

                secDrugsCategory.setUpperCateCode(upperCateCode);
                secDrugsCategory.setCode(goodsCateModel.getGoodsCatgCode());
                secDrugsCategory.setName(goodsCateModel.getGoodsCatgName());
                secDrugsCategory.setMainDrugsCode(goodsCateModel.getMainGoodsCode());

                secDrugsCategories.add(secDrugsCategory);

            }
        }

        return secDrugsCategories;

    }
}
