/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.game.service;

import com.alibaba.fastjson.JSON;
import com.meizu.galaxy2.biz.api.InAppSearchService;
import com.meizu.galaxy2.biz.api.IntegrateSearchService;
import com.meizu.galaxy2.biz.api.SearchApiResult;
import com.meizu.galaxy2.biz.api.VideoDesktopSearchService;
import com.meizu.video.service.AutoComplete;
import com.meizu.video.service.VideoSearchService;
import com.meizu.video.vo.Complete;
import com.meizu.video.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO Comment of BookService
 *
 * @author shisong
 */
@Service("searchService")
public class SearchService {
    @Autowired
    private VideoSearchService videoSearchService;

    @Autowired
    private AutoComplete autoComplete;

    @Autowired
    private VideoDesktopSearchService desktopSearchService;

    @Autowired
    private InAppSearchService inAppSearchService;


    public String dropTudou(String keyword) {
        Complete res = autoComplete.getTudouDrop(keyword);
        if (res != null) {
            return JSON.toJSONString(res.getResults());
        } else {
            return "";
        }
    }

    public String dropYouku(String keyword) {
        Complete res = autoComplete.getYoukuDrop(keyword);
        if (res != null) {
            return JSON.toJSONString(res.getResults());
        } else {
            return "";
        }
    }


    public String searchTudou(String keyword, int limit) {
        SearchResult res = videoSearchService.searchVideosFromApi(keyword, 0, 8, true, "", 0, limit);
        if (res != null) {
            return JSON.toJSONString(res);
        } else {
            return "";
        }
    }

    public String searchYouku(String keyword, int limit) {
        SearchResult res = videoSearchService.searchVideosFromApi(keyword, 0, 16, true, "", 0, limit);
        if (res != null) {
            return JSON.toJSONString(res);
        } else {
            return "";
        }
    }

    public String searchDesktop(String keyword, int limit) {
        IntegrateSearchService.Param param = new IntegrateSearchService.Param();
        param.setKeyword(keyword);
        param.setLimit(limit);
        SearchApiResult res = desktopSearchService.search(param);
        if (res != null) {
            return JSON.toJSONString(res);
        } else {
            return "";
        }
    }

    public String searchInApp(String keyword, int limit) {
        InAppSearchService.Param param = new InAppSearchService.Param();
        param.setKeyword(keyword);
        param.setLimit(limit);
        SearchApiResult res = inAppSearchService.searchVideo(param);
        if (res != null) {
            return JSON.toJSONString(res);
        } else {
            return "";
        }
    }
}
