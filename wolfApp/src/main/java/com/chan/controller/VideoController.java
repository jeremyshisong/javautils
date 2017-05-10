/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package com.chan.controller;

import com.meizu.video.util.StringFilter;
import com.chan.model.SearchModel;
import com.chan.service.VideoService;
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
            return "index";
        }

        String keywords0 = StringFilter.filter(keywords.trim()).replaceAll(" +", "");
        String tudou = videoService.getTudouResults(keywords0);
        String local = videoService.getLocalResults(keywords0, 10);

        searchModel.setTudou(tudou);
        searchModel.setLocal(local);
        searchModel.setKeyword(keywords);
        return "index";
    }
}
