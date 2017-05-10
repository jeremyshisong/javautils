/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.game.controller;

import com.alibaba.fastjson.JSON;
import hello.game.service.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/rest")
public class SearchController {

    private static Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @RequestMapping("/searchName")
    @ResponseBody
    public String searchName(@RequestParam(value = "keyword") String keyword,
                             @RequestParam(value = "start") int start,
                             @RequestParam(value = "limit") int limit, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = JSON.toJSONString(searchService.searchName(keyword, start, limit));
        return result;
    }

    @RequestMapping("/search")
    @ResponseBody
    public String search(@RequestParam(value = "keyword") String keyword,
                         @RequestParam(value = "start") int start,
                         @RequestParam(value = "limit") int limit, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = searchService.search(keyword, start, limit);
        return result;
    }

    @RequestMapping("/searchName2")
    @ResponseBody
    public String searchName2(@RequestParam(value = "keyword") String keyword,
                              @RequestParam(value = "start") int start,
                              @RequestParam(value = "limit") int limit, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return JSON.toJSONString(searchService.searchName2(keyword, start, limit));
    }

    @RequestMapping("/search2")
    @ResponseBody
    public String search2(@RequestParam(value = "keyword") String keyword,
                          @RequestParam(value = "start") int start,
                          @RequestParam(value = "limit") int limit, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return searchService.search2(keyword, start, limit);
    }

}
