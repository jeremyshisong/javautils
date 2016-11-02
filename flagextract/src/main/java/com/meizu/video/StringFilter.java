/**
 * Project: galaxy-video
 * <p/>
 * File Created at 2015-3-21
 * $Id$
 */
package com.meizu.video;

import org.apache.commons.lang3.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO Comment of StringFilter
 *
 * @author shisong
 */
public class StringFilter {
    /* 去掉特殊字符 */
    public static String filter(String str) {
        if (StringUtils.isBlank(str))
            return "";
        String regEx = "[《》～·`~!@#$%^&*()=+|{}':;',\"\\[\\].<>/?~！@#￥%……&*（）——|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
