package com.chan.nl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by shisong on 16/11/2.
 */
public class Writer {
    private String filePath;
    private BufferedWriter out;

    public Writer(String filePath) {
        this.filePath = filePath;
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath);
            out = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            out = null;
        }
    }

    public void print(Map<String, Integer> map) {
        if (map == null || map.size() == 0) {
            return;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    public void write(Map<String, Integer> map) {
        if (map == null || map.size() == 0) {
            return;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            try {
                out.write(entry.getKey() + ":" + entry.getValue());
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return;
                }
            }
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }
}
