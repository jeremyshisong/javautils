package com.klx.service;

import com.alibaba.fastjson.JSON;
import com.klx.oss.ImageUploader;
import com.klx.storage.store.ImageDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/12/4.
 */
@Service("imageUploadService")
public class ImageUploadService {
    @Autowired
    private ImageDto imageDto;
    @Autowired
    private ImageUploader imageUploader;
    private static int status_succ = 1;
    private static int status_defualt = -1;
    private static String status_succ_key = "succs";
    private static String status_defualt_key = "defaults";


    private static Logger logger = Logger.getLogger(ImageUploadService.class);


    private static int count = 100;

    public void uploadImages() {
        List<String> image_codes;
        int page = 1;
        int start;
        boolean hasMore = true;
        Map<String, List<String>> result_code_map;
        List<String> log_succs = new ArrayList<String>();
        List<String> log_defualt = new ArrayList<String>();
        while (hasMore) {
            start = (page - 1) * count;
            image_codes = imageDto.getDrugCodes(start, count);
            if (image_codes == null || image_codes.size() == 0) {
                hasMore = false;
            } else {
                result_code_map = imageUploader.upload(image_codes);
                List<String> succ_codes = result_code_map.get(status_succ_key);
                List<String> defualt_codes = result_code_map.get(status_defualt_key);
                if (succ_codes.size() > 0) {
                    imageDto.update(succ_codes, status_succ);
                    log_succs.addAll(succ_codes);
                }
                if (defualt_codes.size() > 0) {
                    imageDto.update(defualt_codes, status_defualt);
                    log_defualt.addAll(defualt_codes);
                }
                page++;
            }
        }


        logger.info("upload images finished,sucss drug codes =[" + JSON.toJSONString(log_succs) + "]");
        logger.info("upload images finished,defualt drug codes =[" + JSON.toJSONString(log_defualt) + "]");


    }

}
