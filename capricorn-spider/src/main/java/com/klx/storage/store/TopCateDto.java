package com.klx.storage.store;

import com.klx.storage.vo.TopDrugsCate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shisong on 16/12/1.
 */
@Repository("topCateDto")
public class TopCateDto extends BaseDto {
    private static Logger logger = Logger.getLogger(TopCateDto.class);

    public int insert(List<TopDrugsCate> topDrugsCates) {
        logger.info("insert topDrugsCates begain");
        int rows = this.getSqlSessionTemplate().insert(TopDrugsCate.class.getName() + ".addTopDrugsCates", topDrugsCates);
        logger.info("insert topDrugsCates end, rows=[" + rows + "]");
        return rows;
    }

    public List<String> topNames() {
        logger.info("select topNames begain");
        List<String> topNames = this.getSqlSessionTemplate().selectList(TopDrugsCate.class.getName() + ".getTopNames");
        logger.info("select topNames end, rows=[" + topNames == null ? 0 : topNames.size() + "]");
        return topNames;
    }
}
