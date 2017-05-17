package com.chan.nl.crawl.analyse;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by shisong on 17/5/15.
 */
public interface PageAnalyse {
    List<String> analysis(Document document);
}
