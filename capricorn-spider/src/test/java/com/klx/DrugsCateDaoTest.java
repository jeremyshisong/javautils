package com.klx;

import com.alibaba.fastjson.JSON;
import com.klx.storage.dao.DrugsCateDao;
import com.klx.storage.vo.SecDrugsCate;
import com.klx.storage.vo.TopDrugsCate;

import java.util.List;

/**
 * Created by shisong on 16/11/30.
 */
public class DrugsCateDaoTest {
    public static void main(String[] args) {
        DrugsCateDao drugsCateDao = new DrugsCateDao();
        List<TopDrugsCate> drugsCateList = drugsCateDao.loadTopCate();
        List<SecDrugsCate> secDrugsCates = drugsCateDao.loadSecCate();

        System.out.println(JSON.toJSONString(drugsCateList));
        System.out.println(JSON.toJSONString(secDrugsCates));

    }
}
