package com.klx.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/11/29.
 */
@Component("imageUploader")
public class ImageUploader {
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIeaXVKYji9snJ";
    private static String accessKeySecret = "2Zp4HtqpjiVmX5VsbYXbzcbhuWrBm5";
    private static String yfImageDomain = "http://yf-base.img-cn-shenzhen.aliyuncs.com/product/";
    private static String bucketName = "highlegopic";
    private static String product = "product";
    private static String suffix = "a1.jpg";
    private static String separate = "/";

    private static int status_succ =1;
    private static int status_defualt =-1;


    private static String status_succ_key ="succs";
    private static String status_defualt_key ="defaults";


    private OSSClient ossClient;
    private static Logger logger = Logger.getLogger(ImageUploader.class);


    @PostConstruct
    public void init() {
        this.ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    }

    public ImageUploader() {
        this.ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public Map<String, List<String>> upload(List<String> codes) {
        if (codes == null || codes.size() == 0) {
            return new HashMap<String, List<String>>();
        }
        List<String> succs = new ArrayList<String>();
        List<String> defaults = new ArrayList<String>();
        Map<String, List<String>> ret = new HashMap<String, List<String>>();
        try {
            for (int i = 0; i < codes.size(); i++) {
                if (isExist(codes.get(i))) {
                    succs.add(codes.get(i));
                    continue;
                }
                int result = _upload(codes.get(i));
                if (result == status_succ) {
                    succs.add(codes.get(i));
                }
                if (result == status_defualt) {
                    defaults.add(codes.get(i));
                }

                //// TODO: 16/11/30 delete begin
                int randMillis = (int) (1000 + Math.random() * 3000);
                try {
                    Thread.sleep(randMillis);
                    logger.info("sleep" + randMillis + "millis...zZZ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        } finally {
        }

        ret.put(status_succ_key, succs);
        ret.put(status_defualt_key, defaults);
        return ret;
    }


    private boolean isExist(String code) {
        boolean succ;
        try {
            succ = ossClient.doesObjectExist(bucketName, _bucketKey(code));
            logger.info("check code [" + code + "]  exist=" + succ);
        } catch (Exception e) {
            succ = false;
            logger.info("check code [" + code + "] faild set exist=false");
        }
        return succ;
    }

    private int _upload(String code) {
        InputStream inputStream = _url(code);

        if (inputStream == null) {
            logger.info("source image not exsit,drug code =[" + code + "],set default image");
            return status_defualt;
        }
        PutObjectResult result;
        int succ = 0;
        try {
            result = ossClient.putObject(bucketName, _bucketKey(code), inputStream);
            logger.info("oss put object succ,drug code =[" + code + "]");
            logger.info("ETag=" + result.getETag());
            logger.info("RequestId=" + result.getRequestId());
            succ = status_succ;
        } catch (ClientException e) {
            // 本地异常如网络异常等
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常
            logger.error("RequestId=" + e.getRequestId());
            logger.error("ErrorCode=" + e.getErrorCode());
            logger.error("HostId=" + e.getHostId());
            logger.error("RawMessage=" + e.getErrorMessage());
        } finally {
        }

        return succ;
    }

    private String _bucketKey(String id) {
        String bucketKey = product + separate + id + separate + id + suffix;
        return bucketKey;
    }

    private InputStream _url(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(yfImageDomain);
        builder.append(id);
        builder.append(separate);
        builder.append(id);
        builder.append(suffix);
        try {
            return new URL(builder.toString()).openStream();
        } catch (Exception e) {
            return null;
        }
    }
}
