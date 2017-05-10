/**
 * Created by shisong on 17/1/19.
 */

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
    public static ApplicationContext ctx;

    @BeforeClass
    public static void initApplicationContext() {
        ctx = new ClassPathXmlApplicationContext("classpath*:spring/spring-config.xml");
    }
}