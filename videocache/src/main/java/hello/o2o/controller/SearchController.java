/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.o2o.controller;

import com.meizu.video.service.CacheProxy4Test;
import hello.o2o.constants.ParamsMap;
import hello.o2o.model.Param;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/cache.do")
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    @Qualifier("cacheProxy4Test")
    private CacheProxy4Test cacheProxy4Test;
    private static String separator="#";

    public SearchController() {
    }

    @RequestMapping
    public ModelAndView handleCacheSearchRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Param paramVO) throws ServletException, IOException {
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
                   String ret= cacheProxy4Test.getSearchCacheValue(keyword.trim()+separator+type+separator+support+separator+needVIP);
                   results=ret;
                }
            } else {
                if (StringUtils.isNotBlank(brief)) {
                    String complete = cacheProxy4Test.getSuggestCacheValue(brief);
                    results = complete;
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
