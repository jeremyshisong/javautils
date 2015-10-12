import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by shisong on 15/10/12.
 */
public class WorkerTest {
    private Worker worker;
    @Mock
    private DataSource dataSource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        worker = new Worker();
    }

    @Test
    public void testWork() {
        Mockito.when(dataSource.getData()).thenReturn("data source values");
        ReflectionTestUtils.setField(worker, "dataSource", dataSource);
        System.out.println(worker.work());
    }
}