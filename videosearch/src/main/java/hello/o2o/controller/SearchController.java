/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.o2o.controller;

import com.alibaba.fastjson.JSON;
import com.meizu.video.service.AutoComplete;
import com.meizu.video.service.VideoSearchService;
import com.meizu.video.vo.Complete;
import com.meizu.video.vo.SearchResult;
import hello.o2o.constants.ParamsMap;
import hello.o2o.model.Param;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    private VideoSearchService videoSearchService;

    @Autowired
    @Qualifier("videoAutoComplete")
    private AutoComplete videoAutoComplete;

    public SearchController() {
    }

    @RequestMapping(params = "method=video")
    public ModelAndView handleAppSearchRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Param paramVO) throws ServletException, IOException {
        String keyword = paramVO.getKeyword();
        int type = StringUtils.isBlank(paramVO.getType())?0:Integer.valueOf(paramVO.getType());
        int support = StringUtils.isBlank(paramVO.getSupport())?0:Integer.valueOf(paramVO.getSupport());
        boolean needVIP = "true".equals(paramVO.getNeedVIP())?true:false;
        String brief = paramVO.getBrief();
        String radio = request.getParameter("radiobutton");
        String results = null;
        try {
            if ("business".equals(radio)) {
                if (StringUtils.isNotBlank(keyword)) {
                   SearchResult ret= videoSearchService.searchVideosFromApi(keyword, type, support, needVIP, null, 0, 30);
                   results=JSON.toJSONString(ret);
                }
            } else {
                if (StringUtils.isNotBlank(brief)) {
                    Complete complete = videoAutoComplete.getDrop(brief);
                    results = JSON.toJSONString(complete);
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
        return new ModelAndView("video");
    }

    @RequestMapping(value = "/tudou")
    @ResponseBody
    public String handleTudouSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Param paramVO) throws ServletException, IOException {
        return "haha";
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
}
