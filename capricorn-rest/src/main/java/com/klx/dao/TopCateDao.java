package com.klx.dao;

import com.klx.vo.TopDrugsCate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shisong on 16/12/1.
 */
@Repository("topCateDto")
public class TopCateDao extends BaseDao {
    private static Logger logger = Logger.getLogger(TopCateDao.class);

    public List<TopDrugsCate> getTopDrugs() {
        List<TopDrugsCate> list = this.getSqlSessionTemplate().selectList(TopDrugsCate.class.getName() + ".getTopDrugs");
        return list;
    }
}
