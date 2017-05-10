package com.klx.domain;

import com.klx.vo.Drug;

/**
 * Created by shisong on 16/12/8.
 */

public class CartEntity extends Drug {
    private Integer count;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
