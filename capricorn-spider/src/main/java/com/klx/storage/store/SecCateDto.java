package com.klx.storage.store;

import com.klx.storage.vo.SecDrugsCate;
import com.klx.storage.vo.TopDrugsCate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shisong on 16/12/1.
 */
@Repository("SecCateDto")
public class SecCateDto extends BaseDto{
    private static Logger logger = Logger.getLogger(SecCateDto.class);

    public int insert(List<SecDrugsCate> secDrugsCates){
        logger.info("insert secDrugsCates begain");
        int rows = this.getSqlSessionTemplate().insert(SecDrugsCate.class.getName() + ".addSecDrugsCates", secDrugsCates);
        logger.info("insert secDrugsCates end, rows=["+rows+"]");

        return rows;
    }

    public List<String> getSecNames() {
        logger.info("select secNames begain");
        List<String> secNames = this.getSqlSessionTemplate().selectList(SecDrugsCate.class.getName() + ".getSecNames");
        logger.info("select secNames end, rows=[" + secNames==null?0:secNames.size() + "]");
        return secNames;
    }
}
