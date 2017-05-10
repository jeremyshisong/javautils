/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.game.controller;

import hello.game.service.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/dropTudou", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String dropTudou(@RequestParam(value = "keyword") String keyword) {
        String result = searchService.dropTudou(keyword);
        return result;
    }

    @RequestMapping(value = "/dropYouku", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String dropYouku(@RequestParam(value = "keyword") String keyword) {
        String result = searchService.dropYouku(keyword);
        return result;
    }

    @RequestMapping(value = "/searchTudou", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchTudou(@RequestParam(value = "keyword") String keyword) {
        String result = searchService.searchTudou(keyword, 50);
        return result;
    }

    @RequestMapping(value = "/searchYouku", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchYouku(@RequestParam(value = "keyword") String keyword) {
        String result = searchService.searchYouku(keyword, 50);
        return result;
    }


    @RequestMapping(value = "/searchDesktop", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchDesktop(@RequestParam(value = "keyword") String keyword) {
        String result = searchService.searchDesktop(keyword, 50);
        return result;
    }

    @RequestMapping(value = "/searchInApp", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchInApp(@RequestParam(value = "keyword") String keyword) {
        String result = searchService.searchInApp(keyword, 50);
        return result;
    }
}
