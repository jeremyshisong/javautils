package com.klx.storage.store;

import com.klx.storage.vo.Drug;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/12/1.
 */
@Repository("drugsDto")
public class DrugsDto extends BaseDto {
    private static Logger logger = Logger.getLogger(DrugsDto.class);

    public int insert(List<Drug> drugs) {
        logger.info("insert drugs begain");
        int rows = this.getSqlSessionTemplate().insert(Drug.class.getName() + ".addDrugs", drugs);
        logger.info("insert drugs end, rows=[" + rows + "]");
        return rows;
    }

    public List<String> getDrugNames() {
        logger.info("select drugNames begain");
        int page = 1;
        int count = 1000;
        int start;
        boolean hasMore = true;
        List<String> drugNames;
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> ret = new ArrayList<String>();
        while (hasMore) {
            start = (page - 1) * count;
            params.put("start", start);
            params.put("count", count);
            drugNames = this.getSqlSessionTemplate().selectList(Drug.class.getName() + ".getDrugNames", params);
            if (drugNames == null || drugNames.size() == 0) {
                hasMore = false;
            } else {
                ret.addAll(drugNames);
                page++;
            }
        }
        logger.info("select drugNames end, rows=[" + ret == null ? 0 : ret.size() + "]");
        return ret;
    }
}

