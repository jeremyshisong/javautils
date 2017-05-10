package com.klx.service;

import com.klx.commons.ResponseInfo;
import com.klx.dao.SecCateDao;
import com.klx.dao.TopCateDao;
import com.klx.domain.TopCateModel;
import com.klx.vo.SecDrugsCate;
import com.klx.vo.TopDrugsCate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/12/3.
 */
@Repository("drugsCateService")
public class DrugsCateService {
    @Autowired
    private TopCateDao topCateDao;
    @Autowired
    private SecCateDao secCateDao;

    public ResponseInfo getTopDrugs() {
        ResponseInfo respons = new ResponseInfo();
        List<TopDrugsCate> drugsCates = null;
        try {
            drugsCates = topCateDao.getTopDrugs();
        } catch (Exception e) {

        }

        if (drugsCates != null && drugsCates.size() > 0) {
            respons.setCode("200");
            respons.setMessage("succ");
            respons.setValue(drugsCates);
        }

        return respons;
    }

    public ResponseInfo getSecDrugsByUpper(String upper_code) {
        ResponseInfo respons = new ResponseInfo();
        List<SecDrugsCate> drugsCates = null;
        try {
            drugsCates = secCateDao.getSecDrugsByUpper(upper_code);
        } catch (Exception e) {

        }

        if (drugsCates != null && drugsCates.size() > 0) {
            respons.setCode("200");
            respons.setMessage("succ");
            respons.setValue(drugsCates);
        }

        return respons;
    }

    public ResponseInfo getAllCate() {
        ResponseInfo respons = new ResponseInfo();
        List<SecDrugsCate> drugsCates = null;
        try {
            drugsCates = secCateDao.getAllCate();
        } catch (Exception e) {

        }

        if (drugsCates != null && drugsCates.size() > 0) {

            Map<String, TopCateModel> topCateModelMap = new LinkedHashMap<String, TopCateModel>();

            for (int i = 0; i < drugsCates.size(); i++) {
                SecDrugsCate secDrugsCate = drugsCates.get(i);
                String topCateCode = secDrugsCate.getUpperCateCode();
                if (StringUtils.isNotBlank(topCateCode)) {
                    TopCateModel topCateModel;
                    if (!topCateModelMap.keySet().contains(topCateCode)) {
                        topCateModel = new TopCateModel();
                        topCateModel.setCateCode(secDrugsCate.getUpperCateCode());
                        topCateModel.setCateName(secDrugsCate.getUpperCateName());

                    } else {
                        topCateModel = topCateModelMap.get(topCateCode);
                    }
                    secDrugsCate.setUpperCateName(null);
                    topCateModel.getSecCates().add(secDrugsCate);
                    topCateModelMap.put(topCateCode, topCateModel);

                }
            }

            List<TopCateModel> topCate = new ArrayList<TopCateModel>(topCateModelMap.values());
            respons.setCode("200");
            respons.setMessage("succ");
            respons.setValue(topCate);
        }

        return respons;
    }


}
