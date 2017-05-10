package com.klx.domain;

import java.util.List;

/**
 * Created by shisong on 16/12/8.
 */
public class CartModel {
    private String userId;
    private String storeId;
    private List<CartEntity> drugs;

    private Double totalPrice;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public List<CartEntity> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<CartEntity> drugs) {
        this.drugs = drugs;
    }

    public Double getTotalPrice() {
        if (totalPrice == null) {
            if (drugs == null || drugs.size() == 0) {
                return 0.0;
            }
            Double total = 0.0;
            for (CartEntity cartEntity : drugs) {
                total += cartEntity.getCostPrice() * cartEntity.getCount();
            }
            totalPrice = total;
        }
        return totalPrice;

    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice() {
        if (totalPrice == null) {
            if (drugs == null || drugs.size() == 0) {
                totalPrice = 0.0;
                return;
            }
            Double total = 0.0;
            for (CartEntity cartEntity : drugs) {
                total += cartEntity.getCostPrice() * cartEntity.getCount();
            }
            totalPrice = total;
        }
    }
}
