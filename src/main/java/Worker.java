/**
 * Created by shisong on 15/10/12.
 */

class DataSource {
    public String getData() {
        return "your get this data";
    }
}

public class Worker {
    private DataSource dataSource;

    public String work() {
        return dataSource.getData();
    }
}
