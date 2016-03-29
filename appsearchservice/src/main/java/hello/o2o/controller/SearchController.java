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
import com.meizu.galaxy2.biz.api.AppSearchService;
import com.meizu.galaxy2.biz.api.GameSearchService;
import hello.o2o.constants.InfoFields;
import hello.o2o.constants.ParamsMap;
import hello.o2o.model.Param;
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
    private GameSearchService gameSearchService;

    @Autowired
    private AppSearchService appSearchService;

    @RequestMapping(params = "method=app")
    public ModelAndView handleAppSearchRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Param paramVO) throws ServletException, IOException {
        String keyword = paramVO.getKeyword();
        String brief = paramVO.getBrief();
        String radio = request.getParameter("radiobutton");
        String results = null;
        try {
            if ("business".equals(radio)) {
                if (StringUtils.isNotBlank(keyword)) {
                    results = appSearchService.search(keyword, "", "", true, "", "", "", "", 1,0, 50);
                }
            } else {
                if (StringUtils.isNotBlank(brief)) {
                    List<String> listNames = appSearchService.searchName(brief,0,20);
                    results = JSON.toJSONString(listNames);
                }
            }
        } catch (Exception e) {
        }
        if (results == null) {
            results = "None";
        }

        if (radio == null) {
            radio = "business";
        }

        modelMap.put("keyword", paramVO.getKeyword());
        modelMap.put("results", results);
        modelMap.put("brief", brief);
        modelMap.put("radio", radio);
        loadParams(modelMap, paramVO);
        return new ModelAndView("app");
    }

    @RequestMapping(params = "method=game")
    public ModelAndView handleGameSearchRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Param paramVO) throws ServletException, IOException {
        String keyword = paramVO.getKeyword();
        String brief = paramVO.getBrief();
        String radio = request.getParameter("radiobutton");
        String results = null;
        try {
            if ("business".equals(radio)) {
                if (StringUtils.isNotBlank(keyword)) {
                    results = gameSearchService.search(keyword, "", "", true, "", "", "", "", 0,0, 50);
                }
            } else {
                if (StringUtils.isNotBlank(brief)) {
                    List<String> listNames = gameSearchService.searchName(brief,0,20);
                    results = JSON.toJSONString(listNames);
                }
            }
        } catch (Exception e) {
        }
        if (results == null) {
            results = "None";
        }

        if (radio == null) {
            radio = "business";
        }

        modelMap.put("keyword", paramVO.getKeyword());
        modelMap.put("results", results);
        modelMap.put("brief", brief);
        modelMap.put("radio", radio);
        loadParams(modelMap, paramVO);
        return new ModelAndView("game");
    }

    private void loadParams(ModelMap modelMap, Param param) {
        Field[] fields = Param.class.getDeclaredFields();
        String fieldName = "";
        String fieldValue = "";
        for (Field field : fields) {
            field.setAccessible(true);
            fieldName = field.getName();
            try {
                fieldValue = (String) field.get(param);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
            }
            if (StringUtils.isNotBlank(fieldValue)) {
                modelMap.put(fieldName, fieldValue);

            } else {
                modelMap.put(fieldName, ParamsMap.params.get(fieldName));
            }
        }
    }

    private String getResJSON(String res){
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
