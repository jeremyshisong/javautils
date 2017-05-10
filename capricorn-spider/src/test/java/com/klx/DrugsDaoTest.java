package com.klx;

import com.klx.storage.dao.DrugsDao;
import com.klx.storage.vo.Drug;

import java.util.List;

/**
 * Created by shisong on 16/11/30.
 */
public class DrugsDaoTest {
    public static void main(String[] args) {
        DrugsDao drugsDao = new DrugsDao();
        drugsDao.loadAllDrugs2DB();

        System.out.println("ok");
    }
}
