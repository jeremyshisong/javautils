/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.game.service;

import com.meizu.galaxy2.biz.api.InAppSearchService;
import com.meizu.galaxy2.biz.api.SearchApiResult;
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
    private InAppSearchService inAppSearchService;

    public String search(String keyword, int limit) {
        InAppSearchService.Param param = new InAppSearchService.Param();
        param.setKeyword(keyword);
        param.setLimit(limit);
        SearchApiResult res = inAppSearchService.searchVideo(param);
        if (res != null) {
            return res.getJson();
        } else {
            return "";
        }
    }

}
