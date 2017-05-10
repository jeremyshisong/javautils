/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package com.klx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.klx.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
@RequestMapping(value = "/restapi")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class);

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/onLogin", method = RequestMethod.POST)
    @ResponseBody
    public String onLogin(@RequestBody String json,
                          HttpServletRequest request, HttpServletResponse response) {
        String code;
        try {
            JSONObject params = JSON.parseObject(json);
            code = params.getString("code");

        } catch (Exception e) {
            code = null;
        }
        return userService.getToken(code);
    }
}
