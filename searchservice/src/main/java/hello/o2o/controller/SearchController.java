/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.o2o.controller;

import com.alibaba.fastjson.JSON;
import com.meizu.galaxy2.biz.api.AppSearchService;
import com.meizu.galaxy2.biz.api.GameSearchService;
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
import java.util.List;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
@RequestMapping(value = "/restapi/search")
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    @Qualifier("appSearchService")
    private AppSearchService appSolrSearchService;

    @Autowired
    @Qualifier("gameSearchService")
    private GameSearchService gameSolrSearchService;


    @RequestMapping(value = "/app/association")
    @ResponseBody
    public String appassociation(
            @RequestParam(value = "keyword", required = true) String keyword,
            @RequestParam(value = "start", required = true) int start,
            @RequestParam(value = "limit", required = true) int limit,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> list = appSolrSearchService.searchName(keyword, start, limit);
        return list==null?"null":JSON.toJSONString(list);
    }

    @RequestMapping(value = "/app/search")
    @ResponseBody
    public String appsearch(
            @RequestParam(value = "keyword", required = true) String keyword,
            @RequestParam(value = "start", required = true) int start,
            @RequestParam(value = "limit", required = true) int limit,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String res = appSolrSearchService.search(keyword, "", "", true, "", "", "", "", 1,start, limit);
        return res==null?"null":res;
    }

    @RequestMapping(value = "/game/association")
    @ResponseBody
    public String gameassociation(
            @RequestParam(value = "keyword", required = true) String keyword,
            @RequestParam(value = "start", required = true) int start,
            @RequestParam(value = "limit", required = true) int limit,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> list = gameSolrSearchService.searchName(keyword, start, limit);
        return list==null?"null":JSON.toJSONString(list);
    }

    @RequestMapping(value = "/game/search")
    @ResponseBody
    public String gamesearch(
            @RequestParam(value = "keyword", required = true) String keyword,
            @RequestParam(value = "start", required = true) int start,
            @RequestParam(value = "limit", required = true) int limit,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String res = gameSolrSearchService.search(keyword, "", "", true, "", "", "", "", 0,start, limit);
        return res==null?"null":res;
    }
}
