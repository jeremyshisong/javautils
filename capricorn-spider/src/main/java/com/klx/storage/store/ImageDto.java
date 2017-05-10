package com.klx.storage.store;

import com.alibaba.fastjson.JSON;
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
@Repository("imageDto")
public class ImageDto extends BaseDto {
    private static Logger logger = Logger.getLogger(ImageDto.class);

    public void update(List<String> drug_codes,int image_status) {
        Map<String, Object> params = new HashMap<String, Object>();

        logger.info("update drugs image begain");

        List<String> faild_codes = new ArrayList<String>();
        for (String drug_code : drug_codes) {
            params.put("drug_code", drug_code);
            params.put("image_status", image_status);
            int rows = this.getSqlSessionTemplate().update(Drug.class.getName() + ".updateDrugImage", params);
            if (rows == 0) {
                faild_codes.add(drug_code);
            }
        }
        logger.info("update drugs image end, fail_codes=[" + JSON.toJSONString(faild_codes) + "]");
    }

    public List<String> getDrugCodes(int start, int count) {
        logger.info("select drugCodes begain");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", start);
        params.put("count", count);

        List<String> ret = this.getSqlSessionTemplate().selectList(Drug.class.getName() + ".getDrugImage", params);

        logger.info("select drugCodes end, rows=" + ret );
        return ret;
    }
}

