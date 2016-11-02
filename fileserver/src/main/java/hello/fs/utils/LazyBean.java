package hello.fs.utils;

import org.apache.log4j.Logger;

/**
 * Created by ltao on 2015-3-20.
 * 辅助类，用来阻塞主线程，使得kiev服务可以完成初始化
 */


public class LazyBean {
    private static final Logger logger=Logger.getLogger(LazyBean.class);
    public  void lazy()
    {
        int millSecond=5000;
        logger.info("lazy init ...");
        try {
            Thread.sleep(millSecond);
        }
        catch (Exception e)
        {
            logger.error("lazy bean occurs an error",e);
        }
        logger.info("lazy finish ...");
    }

}
