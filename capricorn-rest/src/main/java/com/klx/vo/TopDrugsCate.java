package com.klx.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by shisong on 16/11/30.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class TopDrugsCate implements Serializable {
    private Integer id;
    private String cateCode;
    private String cateName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
