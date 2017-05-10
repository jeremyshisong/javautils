package com.meizu.browser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shisong on 17/1/4.
 */
public interface KwProcessor {
    List read(String path);

    String search(String string);

    Set segment(String name);

    String beauty(String string);

    void print(Map map, String path);
}
