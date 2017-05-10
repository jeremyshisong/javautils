/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package hello.game.service;

import com.meizu.galaxy2.biz.api.GameSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO Comment of BookService
 *
 * @author shisong
 */
@Service("searchService")
public class SearchService {
    @Autowired
    private GameSearchService gameSearchService;

    public List<String> searchName(String keyword, int start, int limit) {
        return gameSearchService.searchName(keyword, start, limit);
    }

    public String search(String keyWord, int start, int limit) {
        return gameSearchService.search(keyWord, "", "", true, "", "",
                "", "", 0, start, limit);
    }


    public List<String> searchName2(String keyword, int start, int limit) {
        GameSearchService.Param param = new GameSearchService.Param();

        param.setKeyword(keyword);
        param.setStart(start);
        param.setLimit(limit);

        return gameSearchService.searchName(param);
    }

    public String search2(String keyword, int start, int limit) {
        GameSearchService.Param param = new GameSearchService.Param();
        param.setKeyword(keyword);
        param.setStart(start);
        param.setLimit(limit);
        return gameSearchService.search(param);
    }

}
