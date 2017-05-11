package com.meizu.nlp.classification.indexing;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shisong on 17/4/25.
 */
public class TestFileIndexWriter {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
            "classpath:classify-spring-context.xml");

    @Test
    public void testDao() {
        FileIndexWriter fileIndexWriter = ctx.getBean("fileIndexWriter", FileIndexWriter.class);
        fileIndexWriter.writeIndex();
    }

}
