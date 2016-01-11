/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meizu.galaxy2.biz.api.O2OSearchService;
import hello.constants.InfoFields;
import hello.constants.ParamsMap;
import hello.constants.ParamsVO;
import hello.utils.BeanCopyer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
@RequestMapping("/search.do")
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    O2OSearchService o2oSearchService;

    @RequestMapping(params = "method=o2o")
    public ModelAndView handleAppSearchRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, ParamsVO paramsVO) throws ServletException, IOException {
        String radio = request.getParameter("radiobutton");
        O2OSearchService.Result results = null;
        String ret = null;
        try {
            if ("business".equals(radio)) {
                if (StringUtils.isNotBlank(paramsVO.getKeyword())) {
                    O2OSearchService.Param param = new O2OSearchService.Param();
                    BeanCopyer.copy(paramsVO, param);
                    results = o2oSearchService.searchAll(param);
                }
            } else {
                O2OSearchService.Param param = new O2OSearchService.Param();
                BeanCopyer.copy(paramsVO, param);
                if (StringUtils.isNotBlank(paramsVO.getBref())) {
                    param.setKeyword(paramsVO.getBref());
                    results = o2oSearchService.suggest(param);
                }
            }
        } catch (Exception e) {
        }
        if (results == null) {
            ret = "None";
        } else {
            ret = results.getJson();
        }

        if (radio == null) {
            radio = "business";
        }

        //modelMap.put("keyword", param.getKeyword());
        modelMap.put("results", ret);
        modelMap.put("radio", radio);
        loadParams(modelMap, paramsVO);
        return new ModelAndView("o2o");
    }

    private void loadParams(ModelMap modelMap, ParamsVO param) {
        Field[] fields = ParamsVO.class.getDeclaredFields();
        String fieldName = "";
        Object fieldValue = "";
        for (Field field : fields) {
            field.setAccessible(true);
            fieldName = field.getName();
            try {
                fieldValue = field.get(param);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
            }
            if (fieldValue != null) {
                modelMap.put(fieldName, fieldValue);
                try {
                    String number = String.valueOf(fieldValue);
                    if ("0".equals(number) || "0.0".equals(number)) {
                        modelMap.put(fieldName, ParamsMap.params.get(fieldName));
                    }
                } catch (Exception e) {
                }
            } else {
                modelMap.put(fieldName, ParamsMap.params.get(fieldName));
            }
        }
    }

    private String getResJSON(String res) {
        JSONObject object = JSONObject.parseObject(res);
        JSONArray array = object.getJSONArray("list");
        return array.toJSONString();
    }

    private String infoObjectFilter(String arryList) {
        JSONArray array = JSONArray.parseArray(arryList);
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            removeOtherFields(obj);
        }

        return array.toJSONString();

    }

    private String setGameJSONArray(String gameJSONString, String gameJSONArray) {
        try {
            JSONObject obj = JSON.parseObject(gameJSONString);
            JSONArray array = JSONArray.parseArray(gameJSONArray);
            obj.put("list", array);
            return obj.toJSONString();
        } catch (Exception e) {
            return gameJSONString;
        }
    }

    private void removeOtherFields(JSONObject obj) {
        List<String> keys = new ArrayList<String>();
        keys.addAll(obj.keySet());
        for (String key : keys) {
            if (!InfoFields.fields.contains(key)) {
                obj.remove(key);
            }
        }
    }

}
