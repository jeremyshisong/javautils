package com.klx;

import com.alibaba.fastjson.JSON;
import com.klx.oss.ImageUploader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/11/30.
 */
public class ImageUploaderTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ImageUploader imageUploader = new ImageUploader();
        List<String> ids = new ArrayList<String>();
        ids.add("5012926");
        Map<String, List<String>> faildIds = imageUploader.upload(ids);
        System.out.println(JSON.toJSONString(faildIds));
    }
}
