package com.klx.storage.dao;

import com.alibaba.fastjson.JSON;
import com.klx.cache.CacheEntity;
import com.klx.praser.impl.DrugsPraseHander;
import com.klx.spider.DrugsSpider;
import com.klx.spider.entity.drugs.DrugsEntity;
import com.klx.spider.entity.drugs.DrugsModel;
import com.klx.storage.store.DrugsDto;
import com.klx.storage.vo.Drug;
import com.klx.storage.vo.SecDrugsCate;
import com.klx.util.ObjectCopyer;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 16/11/30.
 */
@Repository("drugsDao")
public class DrugsDao {
    @Autowired
    private DrugsSpider drugsSpider;
    @Autowired
    private DrugsPraseHander drugsPraseHander;
    @Autowired
    private DrugsCateDao drugsCateDao;
    @Autowired
    private DrugsDto drugsDto;

    @Autowired
    CacheEntity cacheEntity;

    private static Logger logger = Logger.getLogger(DrugsDao.class);

    public void loadAllDrugs2DB() {
        List<SecDrugsCate> secDrugsCates = drugsCateDao.loadSecCate();
        List<String> drugNames = cacheEntity.getDrugNames();
        List<String> deltaDrugNames = new ArrayList<String>();
        if (secDrugsCates != null && secDrugsCates.size() > 0) {
            String cateId;
            int pageNum;
            for (SecDrugsCate secDrugsCate : secDrugsCates) {
                cateId = secDrugsCate.getCode();
                pageNum = 1;
                boolean hasMore = true;
                while (hasMore) {
                    String jsonDrugs = drugsSpider.spider(cateId, pageNum);
                    DrugsEntity drugsEntity = drugsPraseHander.prase(jsonDrugs);
                    List<Drug> drugs = parseDrugs(drugsEntity, cateId);
                    if (drugs.size() > 0) {

                        List<Drug> insertList = new ArrayList<Drug>();

                        for (Drug drug : drugs) {
                            String name = drug.getGoodsName();

                            if (StringUtils.isNoneBlank(name) && !drugNames.contains(name)) {
                                insertList.add(drug);
                            }
                        }

                        if (insertList.size() > 0) {

                            boolean succ = _insert(insertList, cateId, pageNum);
                            if (succ) {
                                deltaDrugNames.addAll(_insertNames(insertList));
                            }
                        }

                        pageNum++;
                    } else {
                        hasMore = false;
                    }
                }
            }
        }

        if (deltaDrugNames.size() > 0) {
            drugNames.addAll(deltaDrugNames);
            cacheEntity.setDrugNames(drugNames);
        }
    }

    private List<String> _insertNames(List<Drug> drugs) {
        List<String> names = new ArrayList<String>();
        for (Drug drug : drugs) {
            names.add(drug.getGoodsName());
        }
        return names;

    }


    private boolean _insert(List<Drug> drugs, String cateId, int pageNum) {
        try {
            drugsDto.insert(drugs);
        } catch (Exception e) {
            logger.info("insert drugs orrcue an error,drugs =" + JSON.toJSONString(drugs) + e.getMessage());
            return false;
        }

        //// TODO: 16/11/30 delete begin
        int randMillis = (int) (1000 + Math.random() * 3000);
        try {
            Thread.sleep(randMillis);
            logger.info("sleep" + randMillis + "millis...zZZ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        _print(cateId, pageNum, drugs);

        //// TODO: 16/11/30 delete end

        return true;

    }


    //// TODO: 16/11/30 delete
    private void _print(String cateId, int page, List<Drug> drugs) {
        logger.info("------cateId=[" + cateId + "];page=[" + page + "]-------------------");
        for (Drug drug : drugs) {
            logger.info(drug.getCommonName());
        }
        logger.info("-------------------------"
        );

    }


    public List<Drug> parseDrugs(DrugsEntity drugsEntity, String cateId) {
        List<Drug> drugList = new ArrayList<Drug>();

        if (drugsEntity == null) {
            return drugList;
        }

        List<DrugsModel> drugsModels = drugsEntity.getO2oHealthAppsGoodsModels();

        if (drugsModels == null || drugsModels.size() == 0) {
            return drugList;
        }

        for (DrugsModel drugsModel : drugsModels) {
            Drug drug = new Drug();
            ObjectCopyer.copy(drugsModel, drug);
            drug.setCateCode(cateId);
            drugList.add(drug);
        }
        return drugList;

    }


}
