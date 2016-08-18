/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.video.controller;

import hello.video.model.SearchModel;
import hello.video.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
@RequestMapping("/search")
public class VideoController {

    private static Logger logger = Logger.getLogger(VideoController.class);
    @Autowired
    private VideoService videoService;

    public VideoController() {
    }

    @RequestMapping(value = "/index")
    public String handleIndexRequest(HttpServletRequest request,
                                     @RequestParam(value = "keywords", required = false) String keywords, @ModelAttribute("searchModel") SearchModel searchModel) {
        if (StringUtils.isBlank(keywords)) {
            searchModel.setTudou("[]");
            searchModel.setLocal("[]");
            searchModel.setRanking("[]");
            return "index";
        }
        String tudou = videoService.getTudouResults(keywords);
        String local = videoService.getLocalResults(keywords, 10);
        String ranking = videoService.getRankResults(keywords, 10);

        searchModel.setTudou(tudou);
        searchModel.setLocal(local);
        searchModel.setRanking(ranking);
        searchModel.setKeyword(keywords);
        return "index";
    }
}
