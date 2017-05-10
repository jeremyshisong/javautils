package com.klx.dao;

import com.klx.domain.CartEntity;
import com.klx.vo.Cart;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 16/12/7.
 */

@Repository("cartDao")
public class CartDao extends BaseDao {
    private static Logger logger = Logger.getLogger(CartDao.class);

    public List<CartEntity> getUserCarts(String user_id, int start, int count) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("start", start);
        params.put("count", count);
        List<CartEntity> list = this.getSqlSessionTemplate().selectList(Cart.class.getName() + ".getUserCarts", params);
        return list;
    }


    public int addCarts(List<Cart> carts) {
        int rows = this.getSqlSessionTemplate().insert(Cart.class.getName() + ".addCarts", carts);
        return rows;
    }

}
