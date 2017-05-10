package com.klx;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ltao on 2015/1/8.
 */
public class BaseDaoTest {
    public static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        BaseDaoTest.applicationContext = applicationContext;
    }

    @BeforeClass
    public static void initSpringContext() {
        applicationContext = new ClassPathXmlApplicationContext("app-task-spring-context-test.xml");
    }

}