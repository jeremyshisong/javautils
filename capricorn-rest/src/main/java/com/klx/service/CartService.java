package com.klx.service;

import com.klx.commons.ResponseInfo;
import com.klx.dao.CartDao;
import com.klx.domain.CartEntity;
import com.klx.domain.CartModel;
import com.klx.vo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 16/12/7.
 */
@Service("cartService")
public class CartService {
    @Autowired
    private CartDao cartDao;
    private static int count = 100;


    public ResponseInfo getUserCarts(String userId) {
        ResponseInfo respons = new ResponseInfo();
        CartModel cartModel = _getUserCarts(userId);

        if (cartModel != null) {
            respons.setCode("200");
            respons.setMessage("succ");
            respons.setValue(cartModel);
        }

        return respons;
    }


    public ResponseInfo addCart(Cart cart) {
        ResponseInfo respons = new ResponseInfo();

        List<Cart> carts = new ArrayList<Cart>();

        carts.add(cart);

        int rows = cartDao.addCarts(carts);


        CartModel cartModel = _getUserCarts(cart.getUserId());

        if (cartModel != null) {
            if (rows > 0) {
                respons.setCode("200");
                respons.setMessage("succ");
            } else {
                respons.setCode("201");
                respons.setMessage("add cart faild");
            }
            respons.setValue(cartModel);
        }

        return respons;
    }

    private CartModel _getUserCarts(String userId) {
        List<CartEntity> carts = new ArrayList<CartEntity>();

        try {
            int start = 0;
            boolean hasMore = true;
            while (hasMore) {
                List<CartEntity> tmp = cartDao.getUserCarts(userId, start, count);
                if (tmp != null && tmp.size() > 0) {
                    carts.addAll(tmp);
                    if (tmp.size() == count) {
                        continue;
                    }
                }
                hasMore = false;
            }
        } catch (Exception e) {

        }

        CartModel cartModel = new CartModel();
        cartModel.setUserId(userId);
        cartModel.setDrugs(carts);
        if (carts != null && carts.size() > 0) {
            cartModel.setStoreId(carts.get(0).getStoreCode());
            cartModel.setTotalPrice();
        }
        return cartModel;
    }


}
