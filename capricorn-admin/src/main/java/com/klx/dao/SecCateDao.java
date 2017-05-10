package com.klx.dao;

import com.klx.vo.SecDrugsCate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/12/1.
 */
@Repository("secCateDto")
public class SecCateDao extends BaseDao {
    private static Logger logger = Logger.getLogger(SecCateDao.class);

    public List<SecDrugsCate> getSecDrugsByUpper(String upper_code) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("upper_code", upper_code);
        List<SecDrugsCate> list = this.getSqlSessionTemplate().selectList(SecDrugsCate.class.getName() + ".getSecDrugsByUpper", params);
        return list;
    }

    public List<SecDrugsCate> getAllCate() {
        List<SecDrugsCate> list = this.getSqlSessionTemplate().selectList(SecDrugsCate.class.getName() + ".getAllCate");
        return list;
    }
}
