package com.klx.dao;

import com.klx.vo.Drug;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/12/1.
 */
@Repository("drugsDao")
public class DrugsDao extends BaseDao {
    private static Logger logger = Logger.getLogger(DrugsDao.class);

    public List<Drug> getDrugsByCate(String cate_code, int start, int count) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cate_code", cate_code);
        params.put("start", start);
        params.put("count", count);
        List<Drug> list = this.getSqlSessionTemplate().selectList(Drug.class.getName() + ".getDrugsByCate", params);
        return list;
    }

    public List<Drug> getDrugsByName(String drug_name, int start, int count) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("drug_name", drug_name);
        params.put("start", start);
        params.put("count", count);
        List<Drug> list = this.getSqlSessionTemplate().selectList(Drug.class.getName() + ".getDrugsByName", params);
        return list;
    }
}

