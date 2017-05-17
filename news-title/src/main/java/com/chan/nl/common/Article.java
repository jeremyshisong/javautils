package com.chan.nl.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by shisong on 17/5/16.
 */
public class Article {
    private  String id;
    private String title;
    private String uc_url;
    private String original_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @JSONField(name="uc_url")
    public String getUC_url() {
        return uc_url;
    }

    public void setUC_url(String uc_url) {
        this.uc_url = uc_url;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }
}
