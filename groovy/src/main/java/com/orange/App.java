package com.orange;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        JSONArray array = new JSONArray();
        JSONObject obj1 = new JSONObject();
        obj1.put("1","aa");
        JSONObject obj2 = new JSONObject();
        obj2.put("1","aa");
        System.out.println(obj1.equals(obj2));
    }
}
