/**
 * Project: galaxy-video
 *
 * File Created at 2015-3-9
 * $Id$
 *
 */

import com.alibaba.fastjson.JSON;
import com.meizu.app.solr.service.ISolrSearchService;
import com.meizu.galaxy2.api.Result;
import com.meizu.galaxy2.api.SearchFacade;
import com.meizu.galaxy2.biz.api.AppSearchService;
import com.meizu.galaxy2.biz.api.GameSearchService;
import org.apache.solr.common.params.CommonParams;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO Comment of KievClientTest
 *
 * @author shisong
 *
 */
public class KievClient_solr {
    private ApplicationContext ctx;
    //private SearchService searchAppStub;
    private SearchFacade searchFacade;

    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("remote-rpc-client-test-solr.xml");
        //searchAppStub = (SearchService) ctx.getBean("searchService");
        searchFacade = (SearchFacade) ctx.getBean("searchFacade");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
    }

    @Test
    public void testSearchAblums() {
        Map<String,String> otherParams = new HashMap<String, String>();
        otherParams.put(CommonParams.START,""+0);
        otherParams.put(CommonParams.ROWS,""+1);
        Result ret = searchFacade.searchDoc("gametest2","(id: 225011)",otherParams);
        System.out.println(JSON.toJSONString(ret));

    }
}
