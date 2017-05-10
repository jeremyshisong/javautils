package com.klx;

import com.klx.service.ImageUploadService;
import com.klx.storage.dao.DrugsCateDao;
import com.klx.task.DataMappingTask;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by shisong on 16/12/1.
 */
public class SpringTest extends BaseDaoTest {
    public static DrugsCateDao drugsCateDao;
    public static DataMappingTask topCateMappingTask;
    public static ImageUploadService imageUploadService;

    @BeforeClass
    public static void init() {

        drugsCateDao = applicationContext.getBean("drugsCateDao", DrugsCateDao.class);
        topCateMappingTask = applicationContext.getBean("dataMappingTask", DataMappingTask.class);
        imageUploadService = applicationContext.getBean("imageUploadService", ImageUploadService.class);
    }


    @Test
    public void testTopCateDto() throws Exception {
        topCateMappingTask.init();
    }


    @Test
    public void testImage() throws Exception {
        imageUploadService.uploadImages();
    }
}
