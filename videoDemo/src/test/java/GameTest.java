import com.alibaba.fastjson.JSON;
import com.meizu.galaxy2.biz.api.GameSearchService;
import com.meizu.galaxy2.biz.api.InAppSearchService;
import com.meizu.galaxy2.biz.api.SearchApiResult;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

/**
 * Created by shisong on 17/1/19.
 */
public class GameTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(GameTest.class);
    private InAppSearchService inAppSearchService;

    @Test
    public void test() {
        inAppSearchService = (InAppSearchService) ctx.getBean("inAppSearchService");
        InAppSearchService.Param param = new InAppSearchService.Param();
        param.setKeyword("火影忍者");
        param.setLimit(10);
        SearchApiResult res = inAppSearchService.searchVideo(param);
        System.out.println(JSON.toJSONString(res));
    }
}