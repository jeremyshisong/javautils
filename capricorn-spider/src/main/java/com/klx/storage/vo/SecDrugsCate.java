package com.klx.storage.vo;

/**
 * Created by shisong on 16/11/30.
 */
public class SecDrugsCate {
    private int id;
    private String code;
    private String name;
    private String upperCateCode;
    private String mainDrugsCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpperCateCode() {
        return upperCateCode;
    }

    public void setUpperCateCode(String upperCateCode) {
        this.upperCateCode = upperCateCode;
    }

    public String getMainDrugsCode() {
        return mainDrugsCode;
    }

    public void setMainDrugsCode(String mainDrugsCode) {
        this.mainDrugsCode = mainDrugsCode;
    }
}
