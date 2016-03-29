/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.o2o.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meizu.galaxy2.biz.api.O2OSearchService;
import com.meizu.galaxy2.biz.api.SearchApiResult;
import hello.o2o.constants.InfoFields;
import hello.o2o.constants.ParamsMap;
import hello.o2o.constants.ParamsVO;
import hello.o2o.utils.BeanCopyer;
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
@RequestMapping("/o2o.do")
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    O2OSearchService o2oSearchService;

    @RequestMapping(params = "method=search")
    public ModelAndView handleO2OSearchRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, ParamsVO paramsVO) throws ServletException, IOException {
        String radio = request.getParameter("radiobutton");
        SearchApiResult results = null;
        String ret;
        String sort = request.getParameter("sort");
        if (StringUtils.isNotBlank(sort)) {
            O2OSearchService.SortBy condition;
            int isort = Integer.valueOf(sort);
            switch (isort) {
                case 0:
                    condition = O2OSearchService.SortBy.DEFAULT;
                    break;
                case 1:
                    condition = O2OSearchService.SortBy.DISTANCE;
                    break;
                case 2:
                    condition = O2OSearchService.SortBy.LOWEST_PRICE;
                    break;
                case 3:
                    condition = O2OSearchService.SortBy.HIGHEST_PRICE;
                    break;
                default:
                    condition = O2OSearchService.SortBy.HIGHEST_RATING;
            }
            paramsVO.setCondition(condition);
            modelMap.put("sort", sort);
        }
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
                }
                if (StringUtils.isNotBlank(paramsVO.getSuggestCity())) {
                    param.setCity(paramsVO.getSuggestCity());
                }
                results = o2oSearchService.suggest(param);
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
        modelMap.put("results", ret);
        modelMap.put("radio", radio);
        loadParams(modelMap, paramsVO);
        return new ModelAndView("o2o");
    }

    @RequestMapping(params = "method=listBiz")
    public ModelAndView handleO2OListBizRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, ParamsVO paramsVO) throws ServletException, IOException {
        SearchApiResult results = null;
        String ret;
        String sort = request.getParameter("sort");
        if (StringUtils.isNotBlank(sort)) {
            O2OSearchService.SortBy condition;
            int isort = Integer.valueOf(sort);
            switch (isort) {
                case 0:
                    condition = O2OSearchService.SortBy.DISTANCE;
                    break;
                case 1:
                    condition = O2OSearchService.SortBy.HIGHEST_RATING;
                    break;
                default:
                    condition = O2OSearchService.SortBy.DISTANCE;
            }
            paramsVO.setCondition(condition);
            modelMap.put("sort", sort);
        }
        try {
            O2OSearchService.Param param = new O2OSearchService.Param();
            BeanCopyer.copy(paramsVO, param);
            results = o2oSearchService.queryBiz(param);
        } catch (Exception e) {
        }
        if (results == null) {
            ret = "None";
        } else {
            ret = results.getJson();
        }

        //modelMap.put("keyword", param.getKeyword());
        modelMap.put("results", ret);
        loadParams(modelMap, paramsVO);
        return new ModelAndView("listBiz");
    }


    @RequestMapping(params = "method=listGrp")
    public ModelAndView handleO2OListGrpRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, ParamsVO paramsVO) throws ServletException, IOException {
        SearchApiResult results = null;
        String ret;
        String sort = request.getParameter("sort");
        if (StringUtils.isNotBlank(sort)) {
            O2OSearchService.SortBy condition;
            int isort = Integer.valueOf(sort);
            switch (isort) {
                case 0:
                    condition = O2OSearchService.SortBy.DISTANCE;
                    break;
                case 1:
                    condition = O2OSearchService.SortBy.LOWEST_PRICE;
                    break;
                case 2:
                    condition = O2OSearchService.SortBy.DEFAULT;
                    break;
                default:
                    condition = O2OSearchService.SortBy.DISTANCE;
            }
            paramsVO.setCondition(condition);
            modelMap.put("sort", sort);
        }
        try {
            O2OSearchService.Param param = new O2OSearchService.Param();
            BeanCopyer.copy(paramsVO, param);
            results = o2oSearchService.queryBizAndGroupon(param);
            BeanCopyer.copy(paramsVO, param);

        } catch (Exception e) {
        }
        if (results == null) {
            ret = "None";
        } else {
            ret = results.getJson();
        }
        //modelMap.put("keyword", param.getKeyword());
        modelMap.put("results", ret);
        loadParams(modelMap, paramsVO);
        return new ModelAndView("listGrp");
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
