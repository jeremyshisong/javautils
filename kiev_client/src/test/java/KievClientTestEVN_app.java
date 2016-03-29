/**
 * Project: galaxy-video
 * 
 * File Created at 2015-3-9
 * $Id$
 * 
 */

import com.alibaba.fastjson.JSON;
import com.meizu.app.solr.service.ISolrSearchService;
import com.meizu.galaxy2.biz.api.AppSearchService;
import com.meizu.galaxy2.biz.api.GameSearchService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * TODO Comment of KievClientTest
 * 
 * @author shisong
 * 
 */
public class KievClientTestEVN_app {
	private ApplicationContext ctx;
	//private SearchService searchAppStub;
	private GameSearchService gameSearchService;

	private AppSearchService appSearchService;

	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("remote-rpc-client-test-app.xml");
		//searchAppStub = (SearchService) ctx.getBean("searchService");
		gameSearchService = (GameSearchService) ctx.getBean("gameSearchService");
		appSearchService=(AppSearchService)ctx.getBean("appSearchService");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testSearchAblums() {
		//List<String> str1 = gameSolrSearchServiceStub.searchName("微", 0, 10);
		//String  str1=gameSearchService.search("微信", "", "", true, "", "", "", "", 1,1, 50);
		List<String> str1=appSearchService.searchName("微", 0, 5);
		String str2=appSearchService.search("淘宝", "", "", true, "", "", "", "", 1,1, 50);
		//String str1=searchAppStub.query("xxx", "", "", true, "", "", "", "", 1, 15);
		System.out.println(JSON.toJSONString(str1));
		System.out.println(str2);

	}
	@Test
	public void testgameSearchService() {
		String ret= gameSearchService.search("赛车", "", "", true, "", "", "", "", 1, 0, 50);
		System.out.println(JSON.toJSONString(ret));
		//String res2= searchAppStub.searchPrefix("微",false,0,10);
		//System.out.println(JSON.toJSONString(res2));
	}
}
