package com.meizu.nlp.classification;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shisong on 17/4/25.
 */
public class TestClassification {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
            "classpath:classify-spring-context.xml");

    @Test
    public void testClassify() throws Exception {
        Classify classify = ctx.getBean("classify", Classify.class);
        long start = System.currentTimeMillis();
        String result = classify.classify("手机");
        System.out.println("####classify cost####=" + (System.currentTimeMillis() - start));
        System.out.println("####result####=" + result);
    }

    @Test
    public void testVerify() throws Exception {
        Classify classify = ctx.getBean("classify", Classify.class);
        classify.verify();
    }
}
