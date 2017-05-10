package com.klx.domain;

import com.klx.vo.SecDrugsCate;
import com.klx.vo.TopDrugsCate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 16/12/3.
 */
public class TopCateModel extends TopDrugsCate {
    private List<SecDrugsCate> secCates;

    public TopCateModel() {
        this.secCates = new ArrayList<SecDrugsCate>();
    }

    public List<SecDrugsCate> getSecCates() {
        return secCates;
    }

    public void setSecCates(List<SecDrugsCate> secCates) {
        this.secCates = secCates;
    }
}
