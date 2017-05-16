package com.chan.nl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shisong on 16/11/2.
 */
public class Reader {
    private String filePath;
    private BufferedReader in;

    public Reader(String filePath) {
        this.filePath = filePath;
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
            in = new BufferedReader(fileReader);
        } catch (Exception e) {
            in = null;
        }
    }

    List<String> readLines() {
        List<String> ret = new ArrayList<String>();
        if (in == null) {
            return ret;
        }

        String line = "";
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                return ret;
            }
        }
        while (line != null) {
            ret.add(line);
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return ret;
                }
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ret;
        }
        return ret;
    }

}
