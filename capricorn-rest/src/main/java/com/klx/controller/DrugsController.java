/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package com.klx.controller;

import com.alibaba.fastjson.JSON;
import com.klx.commons.ResponseInfo;
import com.klx.service.CartService;
import com.klx.service.DrugsCateService;
import com.klx.service.DrugsService;
import com.klx.service.UserService;
import com.klx.vo.Cart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
@RequestMapping(value = "/restapi")
public class DrugsController {

    @Autowired
    private DrugsCateService drugsCateService;
    @Autowired
    private DrugsService drugsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(DrugsController.class);

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cate/top", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo top(
            HttpServletRequest request, HttpServletResponse response) {
        return drugsCateService.getTopDrugs();
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cate/{top_cate}", method = RequestMethod.GET)
    @ResponseBody

    public ResponseInfo sec(@PathVariable(value = "top_cate") String top_cate,
                            HttpServletRequest request, HttpServletResponse response) {
        return drugsCateService.getSecDrugsByUpper(top_cate);
    }


    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cate/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo allCate(
            HttpServletRequest request, HttpServletResponse response) {
        return drugsCateService.getAllCate();
    }


    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/drugs/cate/{cate_code}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo cate_drugs(
            @PathVariable("cate_code") String cate_code,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "count") int count,
            HttpServletRequest request, HttpServletResponse response) {
        return drugsService.getDrugsByCate(cate_code, page, count);
    }


    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/drugs/name", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo name_drugs(
            @RequestParam(value = "drug_name") String drug_name,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "count") int count,
            HttpServletRequest request, HttpServletResponse response) {
        return drugsService.getDrugsByName(drug_name, page, count);
    }


    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/drugs/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo name_drugs(
            @PathVariable(value = "id") String id,
            HttpServletRequest request, HttpServletResponse response) {
        return drugsService.getDrugsById(id);
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/carts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo user_carts(@RequestHeader(value = "token") String token,
                                   HttpServletRequest request, HttpServletResponse response) {

        String user_id = userService.getOpenId(token);
        return cartService.getUserCarts(user_id);
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/carts/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo add_carts(@RequestHeader(value = "token") String token, @RequestBody String json,
                                  HttpServletRequest request, HttpServletResponse response) {
        Cart cart = JSON.parseObject(json, Cart.class);
        String user_id = userService.getOpenId(token);
        cart.setUserId(user_id);
        return cartService.addCart(cart);
    }
}
