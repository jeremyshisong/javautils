package com.meizu.browser;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    private static void readExcel(String filePath) {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell;
        try {
            book = Workbook.getWorkbook(new File(filePath));
            sheet = book.getSheet(0);
            i = 1;
            while (true) {
                cell = sheet.getCell(1, i);
                if ("".equals(cell.getContents()) == true)
                    break;
                Map<String, String> result = getExtract(cell.getContents());
                print(result);
                i++;
            }
            book.close();
        } catch (Exception e) {
        }
    }

    private static void print(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return;
        } else {
            String ret = map.get("name") + "," + map.get("core") + "," + map.get("side");
            System.out.println(ret);
        }
    }

    private static Map<String, String> getExtract(String name) {
        String key_name = "name";
        String key_core = "core";
        String key_side = "side";
        Map<String, String> map = new HashMap<String, String>();
        map.put(key_name, name);
        map.put(key_core, StringFilter.filter(name));
        map.put(key_side, "");

        if (StringUtils.isBlank(name)) {
            return map;
        }

        String[] splits = name.split(" ");

        if (splits != null && splits.length > 1) {
            String core = splits[0];
            String side = "";
            for (int i = 1; i < splits.length; i++) {
                side += splits[i];
            }
            core = StringFilter.filter(core);
            map.put(key_core, core);
            map.put(key_side, side);
        }
        return map;
    }

    public static void main(String[] args) {
        readExcel("/Users/shisong/Documents/video_name.xls");
    }
}
