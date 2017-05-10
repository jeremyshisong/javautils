package com.meizu.browser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shisong on 17/1/4.
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("remote-rpc-client-test.xml");
        KwProcessor kwProcessor = (KwProcessor) ctx.getBean("kwProcessor");
        String pathRead = "/Users/shisong/Downloads/12.22-28TOP2000.xls";
        String pathWrite = "/Users/shisong/Downloads/12.22-28TOP2000_w.xls";
        List<String> words = kwProcessor.read(pathRead);

        if (words == null) {
            return;
        }

//        Set<String> segments = new HashSet();
//
//
//        for(String kw:words){
//            Set segs = kwProcessor.segment(kw);
//            segments.addAll(segs);
//        }

        Map resultMap = new HashMap();

        for (String kw : words) {
            String result = kwProcessor.search(kw);
            String beauty = kwProcessor.beauty(result);
            resultMap.put(kw, beauty);
            System.out.println(result);

        }
        kwProcessor.print(resultMap, pathWrite);
    }
}
