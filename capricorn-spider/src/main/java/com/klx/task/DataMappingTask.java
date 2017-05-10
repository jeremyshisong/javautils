package com.klx.task;

import com.klx.cache.CacheEntity;
import com.klx.service.ImageUploadService;
import com.klx.storage.dao.DrugsCateDao;
import com.klx.storage.dao.DrugsDao;
import com.klx.storage.store.DrugsDto;
import com.klx.storage.store.SecCateDto;
import com.klx.storage.store.TopCateDto;
import com.klx.storage.vo.SecDrugsCate;
import com.klx.storage.vo.TopDrugsCate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shisong on 16/12/1.
 */
@Service("dataMappingTask")
public class DataMappingTask {
    @Autowired
    private DrugsCateDao drugsCateDao;

    @Autowired
    private DrugsDao drugsDao;
    @Autowired
    private TopCateDto topCateDto;
    @Autowired
    private SecCateDto secCateDto;
    @Autowired
    private DrugsDto drugsDto;
    @Autowired
    CacheEntity cacheEntity;

    @Autowired
    private ImageUploadService imageUploadService;


    private static boolean spider_on_start = false;

    private Lock lock = new ReentrantLock();


    private static Logger logger = Logger.getLogger(DataMappingTask.class);

    public void init() {


        List<String> topNames = topCateDto.topNames();
        List<String> secNames = secCateDto.getSecNames();
        List<String> drugNames = drugsDto.getDrugNames();
        if (topNames != null) {
            cacheEntity.setTopNames(topNames);
        } else {
            cacheEntity.setTopNames(new ArrayList<String>());
        }

        if (secNames != null) {
            cacheEntity.setSecNames(secNames);
        } else {
            cacheEntity.setSecNames(new ArrayList<String>());

        }

        if (drugNames != null) {
            cacheEntity.setDrugNames(drugNames);
        } else {
            cacheEntity.setDrugNames(new ArrayList<String>());
        }

        if (spider_on_start) {
            initData2DB();
        }
        uploadImages();
    }

    public void initData2DB() {
        boolean getLock = lock.tryLock();
        logger.info("init data to db begin");
        long start = System.currentTimeMillis();
        try {
            if (getLock) {

                initTopCate2DB();
                initSecCate2DB();
                initDrugs2DB();

                long end = System.currentTimeMillis();
                logger.info("init data to db finished,time cost=[" + (end - start) + "]ms");


                long img_begin = System.currentTimeMillis();
                logger.info("fetch image to oss begin");
                imageUploadService.uploadImages();
                long img_end = System.currentTimeMillis();
                logger.info("fetch image to oss begin,time cost=[" + (img_end - img_begin) + "]ms");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (getLock) {
                lock.unlock();
            }
        }
    }


    public void uploadImages() {
        boolean getLock = lock.tryLock();
        try {
            long begin = System.currentTimeMillis();
            logger.info("fetch images to oss begin");
            if (getLock) {
                imageUploadService.uploadImages();
            }
            long end = System.currentTimeMillis();
            logger.info("fetch images to oss end, cost[" + (end - begin) + "]ms");
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (getLock) {
                lock.unlock();
            }
        }
    }

    private void initTopCate2DB() {
        logger.info("load top cate data begin");
        List<TopDrugsCate> drugsCateList = drugsCateDao.loadTopCate();
        logger.info("load size = " + drugsCateList.size() + ",top cate data finished");


        List<String> topNames = cacheEntity.getTopNames();

        List<TopDrugsCate> insertList = new ArrayList<TopDrugsCate>();

        if (drugsCateList != null) {

            for (TopDrugsCate topDrugsCate : drugsCateList) {
                String name = topDrugsCate.getName();

                if (!topNames.contains(name)) {
                    insertList.add(topDrugsCate);
                }
            }

        }

        if (insertList.size() == 0) {
            logger.info("no new top cate info to insert");
            return;
        }


        logger.info("insert size = " + insertList.size() + ",top cate data begin");
        int rows = topCateDto.insert(insertList);
        logger.info("insert size = " + insertList.size() + ", top cate data finished");

        if (rows > 0) {
            for (TopDrugsCate topDrugsCate : insertList) {
                topNames.add(topDrugsCate.getName());
            }

            cacheEntity.setTopNames(topNames);
        }
    }

    private void initSecCate2DB() {
        logger.info("load sec cate data begin");
        List<SecDrugsCate> drugsCates = drugsCateDao.loadSecCate();
        logger.info("load size = " + drugsCates.size() + ",sec cate data begin");

        List<String> secNames = cacheEntity.getSecNames();

        List<SecDrugsCate> insertList = new ArrayList<SecDrugsCate>();

        if (drugsCates != null) {

            for (SecDrugsCate secDrugsCate : drugsCates) {
                String name = secDrugsCate.getName();

                if (!secNames.contains(name)) {
                    insertList.add(secDrugsCate);
                }
            }

        }

        if (insertList.size() == 0) {
            logger.info("no new sec cate info to insert");
            return;
        }


        logger.info("insert size = " + insertList.size() + ", sec cate data begin");
        int rows = secCateDto.insert(insertList);
        logger.info("insert size = " + insertList.size() + ", sec cate data finished");
        if (rows > 0) {
            for (SecDrugsCate secDrugsCate : insertList) {
                secNames.add(secDrugsCate.getName());
            }

            cacheEntity.setSecNames(secNames);
        }
    }

    private void initDrugs2DB() {
        logger.info("init drugs data begin");
        drugsDao.loadAllDrugs2DB();
        logger.info("init drugs data finished");

    }
}
