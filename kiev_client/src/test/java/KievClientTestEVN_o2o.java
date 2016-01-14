/**
 * Project: galaxy-video
 * 
 * File Created at 2015-3-9
 * $Id$
 * 
 */

import com.alibaba.fastjson.JSON;
import com.meizu.galaxy2.biz.api.AppSearchService;
import com.meizu.galaxy2.biz.api.GameSearchService;
import com.meizu.galaxy2.biz.api.O2OSearchService;
import org.junit.Assert;
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
public class KievClientTestEVN_o2o {
	private ApplicationContext ctx;
	private O2OSearchService o2oSearchService;

	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("remote-rpc-client-test-o2o.xml");
		//searchAppStub = (SearchService) ctx.getBean("searchService");
		o2oSearchService = (O2OSearchService) ctx.getBean("o2oSearchService");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testSearchAblums() {
		O2OSearchService.Param param = new O2OSearchService.Param();
		param.setKeyword("都市花园连锁酒店");
		param.setCity("苏州");
		param.setLongitude(109.03989);
		param.setLatitude(34.251904);
		param.setAreaFilter("苏州工业园区");
		param.setPage(10);
		O2OSearchService.Result result = o2oSearchService.searchMoreHotel(param);
		System.out.println(JSON.toJSONString(result));
		Assert.assertNotNull(result);

	}
}
