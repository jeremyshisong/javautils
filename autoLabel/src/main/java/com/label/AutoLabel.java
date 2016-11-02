package com.label;

import csvreader.CsvReader;
import csvreader.CsvWriter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class AutoLabel {
    public static void main(String[] args) throws Exception {
        AutoLabel autoLabel = new AutoLabel();
        autoLabel.readExcel();
    }

    private List<String> readExcel() throws IOException {
        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r = new CsvReader("/Users/shisong/Documents/work/browser/应用名称列表.csv", ',', Charset.forName("GBK"));
        CsvWriter w =new CsvWriter("/Users/shisong/Documents/work/browser/2.csv",',',Charset.forName("GBK"));
        //读取表头
        r.readHeaders();
        //逐条读取记录，直至读完
        Search search = new Search();
        List<String> records = new ArrayList<String>();
        while (r.readRecord()) {
            String index = r.get("排名");
            String name = r.get("应用名称");
            if (!StringUtils.isEmpty(name)) {
                String appId = search.getAppId(name);
                String releaseDate = search.getAppReleaseDate(appId);
                String[] contents = {index,name,releaseDate};
                System.out.println(name + "," + releaseDate);
                w.writeRecord(contents);
            }
        }
        r.close();
        w.close();
        System.out.println("job done!!!");
        return records;
    }
}
