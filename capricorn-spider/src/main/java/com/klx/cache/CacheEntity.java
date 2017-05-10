package com.klx.cache;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by shisong on 16/12/4.
 */
@Component("cacheEntity")
public class CacheEntity {
    private List<String> topNames;
    private List<String> secNames;
    private List<String> drugNames;

    public List<String> getTopNames() {
        return topNames;
    }

    public void setTopNames(List<String> topNames) {
        this.topNames = topNames;
    }

    public List<String> getSecNames() {
        return secNames;
    }

    public void setSecNames(List<String> secNames) {
        this.secNames = secNames;
    }

    public List<String> getDrugNames() {
        return drugNames;
    }

    public void setDrugNames(List<String> drugNames) {
        this.drugNames = drugNames;
    }
}
