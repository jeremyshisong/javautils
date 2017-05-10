package com.meizu.browser.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meizu.browser.KwProcessor;
import com.meizu.galaxy2.biz.api.BrowserSearchService;
import com.meizu.galaxy2.biz.api.SearchApiResult;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by shisong on 17/1/4.
 */
@Service("kwProcessor")
public class KwProcessorImpl implements KwProcessor {
    @Autowired
    private BrowserSearchService browserSearchService;

    private static String city = "北京";
    private static String imei = "1234";

    public List read(String path) {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell;
        List ret = new ArrayList();
        try {
            book = Workbook.getWorkbook(new File(path));
            sheet = book.getSheet(0);
            i = 1;
            while (true) {
                cell = sheet.getCell(0, i);
                if ("".equals(cell.getContents()) == true)
                    break;
                ret.add(cell.getContents());
                i++;
            }
            book.close();
        } catch (Exception e) {
        }
        return ret;
    }

    public String search(String string) {
        BrowserSearchService.Param param = new BrowserSearchService.Param();
        param.setKeyword(string);
        param.setImei(imei);
        param.setCity(city);
        SearchApiResult result = browserSearchService.associate(param);
        if (result != null) {
            return result.getJson();
        } else {
            return null;
        }
    }

    public Set segment(String name) {
        Set<String> set = new HashSet<String>();
        if (name != null && !"".equals(name)) {
            //构建IK分词器，不使用smart分词模式
            org.apache.lucene.analysis.Analyzer analyzer = new IKAnalyzer(false);
            //获取Lucene的TokenStream对象
            TokenStream ts = null;
            try {
                ts = analyzer.tokenStream("myField", new StringReader(name));
                //获取词元文本属性
                CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);

                //重置TokenStream（重置StringReader）
                ts.reset();
                //迭代获取分词结果
                while (ts.incrementToken()) {
                    //保留长度大于1的分词结果
                    String retTmp = term.toString();
                    if (retTmp != null && term.length() > 1) {
                        set.add(retTmp);
                    }
                }
                //关闭TokenStream（关闭StringReader）
                ts.end();   // Perform end-of-stream operations, e.g. set the final offset.
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //释放TokenStream的所有资源
                if (ts != null) {
                    try {
                        ts.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return set;
    }


    public String beauty(String string) {
        JSONObject object;
        boolean hasValue = false;
        try {
            object = JSONObject.parseObject(string);
            JSONArray datas = object.getJSONArray("datas");
            for (int i = 0; i < datas.size(); i++) {
                JSONObject obj = datas.getJSONObject(i);
                JSONArray items = obj.getJSONArray("items");
                if (items.size() > 0) {
                    hasValue = true;
                    break;
                }
            }
        } catch (Exception e) {
            hasValue = false;
        }
        if (hasValue) {
            return string;
        } else {
            return "";
        }
    }

    public void print(Map map, String path) {
        WritableWorkbook wwb = null;
        try {
            //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
            wwb = Workbook.createWorkbook(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (wwb != null) {
            //创建一个可写入的工作表
            //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            //下面开始添加单元格
            int row = 0;
            for (Object key : map.keySet()) {
                //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
                Label label1 = new Label(0, row, String.valueOf(key));
                Label label2 = new Label(1, row, String.valueOf(map.get(key)));
                try {
                    //将生成的单元格添加到工作表中
                    ws.addCell(label1);
                    ws.addCell(label2);
                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
                row++;
            }

            try {
                //从内存中写入文件中
                wwb.write();
                //关闭资源，释放内存
                wwb.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }
}
