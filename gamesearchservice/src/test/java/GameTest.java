import com.alibaba.fastjson.JSON;
import com.meizu.galaxy2.biz.api.GameSearchService;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

/**
 * Created by shisong on 17/1/19.
 */
public class GameTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(GameTest.class);
    private GameSearchService gameSearchService;

    @Test
    public void test() {
        gameSearchService = (GameSearchService) ctx.getBean("gameSearchService");
        //String res = gameSearchService.search("欢乐", "", "", true, "", "", "", "", 1, 1, 50);
        //String res = innerSearchService.search("快乐",true,0,50);
        //List<String> res  = gameSearchService.searchName("cf",0,5);
        List<String> res = gameSearchService.searchName("熊出没系列", 0, 5);
        //List<String> res  = gameSolrSearchService.searchApps("快乐",0,50);
        //res =parseGameResults(res);
        //res =parseJSONResults(res);
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void test2() {
        gameSearchService = (GameSearchService) ctx.getBean("gameSearchService");
        GameSearchService.Param param = new GameSearchService.Param();
        param.setKeyword("预约");
        param.setStart(0);
        param.setLimit(10);
        param.setSubscribe(true);
        String res = gameSearchService.search(param);
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void testSearchName() {
        gameSearchService = (GameSearchService) ctx.getBean("gameSearchService");
        GameSearchService.Param param = new GameSearchService.Param();
        param.setKeyword("预约");
        param.setStart(0);
        param.setLimit(10);
        param.setSubscribe(true);
        List<String> res = gameSearchService.searchName(param);
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void testSearchProxyName() {
        gameSearchService = (GameSearchService) ctx.getBean("gameSearchServiceProxy");
        GameSearchService.Param param = new GameSearchService.Param();
        param.setKeyword("大主宰");
        param.setStart(0);
        param.setLimit(10);
        param.setSubscribe(false);
        List<String> res = gameSearchService.searchName(param);
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void testSearchProxy() {
        gameSearchService = (GameSearchService) ctx.getBean("gameSearchServiceProxy");
        GameSearchService.Param param = new GameSearchService.Param();
        param.setKeyword("庞然巨物");
        param.setStart(0);
        param.setLimit(10);
        param.setSubscribe(true);
        String res = gameSearchService.search(param);
        System.out.println(JSON.toJSONString(res));
    }
}