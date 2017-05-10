import hello.demo.service.ClassifyService;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by shisong on 17/1/19.
 */
public class DemoTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(DemoTest.class);
    private ClassifyService classifyService;

    @Test
    public void testVerify() throws Exception {
        classifyService = ctx.getBean("classifyService", ClassifyService.class);
        classifyService.verify();
    }

    @Test
    public void testClassify() throws Exception {
        classifyService = ctx.getBean("classifyService", ClassifyService.class);
        String ret = classifyService.classify("手机贴膜");
        System.out.println(ret);
    }

    @Test
    public void testTreeHtml() throws Exception {
        classifyService = ctx.getBean("classifyService", ClassifyService.class);
        String ret = classifyService.treeHtml();
        System.out.println(ret);
    }
}