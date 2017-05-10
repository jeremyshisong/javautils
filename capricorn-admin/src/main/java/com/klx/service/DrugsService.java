package com.klx.service;

import com.klx.commons.ResponseInfo;
import com.klx.dao.DrugsDao;
import com.klx.vo.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 16/12/3.
 */
@Repository("drugsService")
public class DrugsService {
    @Autowired
    private DrugsDao drugsDao;

    public ResponseInfo getDrugsByCate(String cate_code, int pageNum, int count) {
        ResponseInfo respons = new ResponseInfo();
        List<Drug> drugs = null;
        try {
            if (pageNum <= 0) {
                pageNum = 1;
            }
            if (count < 1) {
                count = 10;
            }
            int start = (pageNum - 1) * count;
            drugs = drugsDao.getDrugsByCate(cate_code, start, count);
        } catch (Exception e) {

        }
        if (drugs != null && drugs.size() > 0) {
            respons.setCode("200");
            respons.setMessage("succ");
            respons.setValue(drugs);
        }
        return respons;
    }

    public ResponseInfo getDrugsByName(String drug_name, int pageNum, int count) {
        ResponseInfo respons = new ResponseInfo();
        List<Drug> drugs = null;
        try {
            if (pageNum <= 0) {
                pageNum = 1;
            }
            if (count < 1) {
                count = 10;
            }
            int start = (pageNum - 1) * count;
            drugs = drugsDao.getDrugsByName(drug_name, start, count);
        } catch (Exception e) {

        }
        if (drugs != null) {
            respons.setCode("200");
            respons.setMessage("succ");
            respons.setValue(drugs);
        } else {
            respons.setCode("201");
            respons.setMessage("query faild");
            respons.setValue(new ArrayList<String>());
        }
        return respons;
    }

}
