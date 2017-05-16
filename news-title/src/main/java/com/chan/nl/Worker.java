package com.chan.nl;

import com.alibaba.fastjson.JSON;
import com.chan.nl.common.TrieTree;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Worker
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        Reader reader = new Reader("/Users/shisong/Documents/work/book/bookNames.txt");
        Process process = new Process();
        Writer writer = new Writer("/Users/shisong/Documents/work/book/bookNames_pre.txt");

        List<String> names = reader.readLines();
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        if (names != null && names.size() > 0) {
            for (String name : names) {
                List strings = process.split(name);
                process.putMap(map, strings);
            }
        }


        //writer.write(map);

        TrieTree tree = process.loadTrieTree(map);

        //Map<String, Integer> pres = tree.prefixs(5);

        Map<String, Integer> indexTags = tree.indexTags("世界500强企业管人管事制度大全");
        Map<String, Integer> queryTag = tree.queryTag("世界500强企业管人管事制度大全");

        System.out.println(JSON.toJSONString(indexTags));
        writer.write(queryTag);
    }
}
