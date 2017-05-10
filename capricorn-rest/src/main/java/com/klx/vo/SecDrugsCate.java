package com.klx.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by shisong on 16/11/30.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SecDrugsCate implements Serializable {
    private Integer id;
    private String cateCode;
    private String cateName;
    private String upperCateCode;
    private String upperCateName;
    private String mainDrugsCode;

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

    public String getUpperCateCode() {
        return upperCateCode;
    }

    public void setUpperCateCode(String upperCateCode) {
        this.upperCateCode = upperCateCode;
    }

    public String getUpperCateName() {
        return upperCateName;
    }

    public void setUpperCateName(String upperCateName) {
        this.upperCateName = upperCateName;
    }

    public String getMainDrugsCode() {
        return mainDrugsCode;
    }

    public void setMainDrugsCode(String mainDrugsCode) {
        this.mainDrugsCode = mainDrugsCode;
    }
}
