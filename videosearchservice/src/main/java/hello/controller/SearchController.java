/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.controller;

import com.alibaba.fastjson.JSON;
import com.meizu.video.service.AutoComplete;
import com.meizu.video.service.VideoSearchService;
import com.meizu.video.vo.Complete;
import com.meizu.video.vo.SearchResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
@RequestMapping(value = "/restapi/video")
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    private VideoSearchService videoSearchService;

    @Autowired
    @Qualifier("videoAutoComplete")
    private AutoComplete videoAutoComplete;


    @RequestMapping(value = "/suggest")
    @ResponseBody
    public String appassociation(
            @RequestParam(value = "keyword", required = true) String keyword,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Complete complete = videoAutoComplete.getDrop(keyword);
        return complete==null?"null":JSON.toJSONString(complete);
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public String appsearch(
            @RequestParam(value = "keyword", required = true) String keyword,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SearchResult ret= videoSearchService.searchVideosFromApi(keyword, 0, 15, true, null, 0, 50);
        return ret==null?"null":JSON.toJSONString(ret);
    }
}
