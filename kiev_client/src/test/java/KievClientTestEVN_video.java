/**
 * Project: galaxy-video
 * 
 * File Created at 2015-3-9
 * $Id$
 * 
 */

import com.alibaba.fastjson.JSON;
import com.meizu.video.service.AutoComplete;
import com.meizu.video.service.VideoInfos;
import com.meizu.video.service.VideoSearchService;
import com.meizu.video.service.CacheProxy4Test;
import com.meizu.video.service.impl.VideoSearchServiceProxy;
import com.meizu.video.vo.SearchResult;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**	
 * TODO Comment of KievClientTest
 * 
 * @author shisong
 * 
 */
public class KievClientTestEVN_video {
	private ApplicationContext ctx;
	private AutoComplete videoDropStub;
	private AutoComplete browserDropStub;
	private VideoSearchService videoSearchStub;
	private VideoInfos videoInfosSub;
	private CacheProxy4Test cacheProxy4Test;

	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("remote-rpc-client-test-video.xml");
		videoDropStub = (AutoComplete) ctx.getBean("videoAutoComplete");
		browserDropStub = (AutoComplete) ctx.getBean("browserAutoComplete");
		videoSearchStub = (VideoSearchService) ctx.getBean("videoSearchService");
		videoInfosSub = (VideoInfos) ctx.getBean("videoInfos");
		cacheProxy4Test = (CacheProxy4Test)ctx.getBean("cacheProxy4Test");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testSearchAblums() {
		String cacheValue = cacheProxy4Test.getSearchCacheValue("周星驰" + "#" + 0 + "#" + 8 + "#" + false);
		System.out.println("cacheValue1="+cacheValue);
		SearchResult str = videoSearchStub.searchVideosFromApi("周星驰", 0, 8, false, 1, 20);
		System.out.println(JSON.toJSONString(str));
		cacheValue = cacheProxy4Test.getSearchCacheValue("周星驰" + "#" + 0 + "#" + 8 + "#" + false);
		System.out.println("cacheValue2="+cacheValue);
		/*for(int i=0;i<2;i++) {
		*//*SearchResult str1 = videoSearchStub.searchVideosFromApi("名剑", 0, 8, 1, 2);
		System.out.println(JSON.toJSONString(str1));*//*
			String keyword1 = "哆啦A梦";
			String keyword2 = "A";
			String keyword3 = "A ";
			System.out.println(keyword1+keyword1.length()+keyword2+keyword2.length()+keyword3+keyword3.length());
			SearchResult obj = videoSearchStub.searchVideosFromApi("哆啦A梦", 0, 0, false, null, 0, 30);
			System.out.println(JSON.toJSONString(obj));
		}*/
	}
}
