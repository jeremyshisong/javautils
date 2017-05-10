/**
 * Project: galaxy-video
 * <p/>
 * File Created at 2015-1-7
 * $Id$
 */
package hello.chan.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO Comment of TudouVideoMap
 *
 * @author shisong
 */
public final class TudouVideoMap {
    /* 土豆专辑id和魅族本地频道id映射 */
    public static final Map<String, String> ALBUMCate2MEIZU = new HashMap<String, String>();

    static {
        ALBUMCate2MEIZU.put("0", "0");
        ALBUMCate2MEIZU.put("2", "1");
        ALBUMCate2MEIZU.put("1", "2");
        ALBUMCate2MEIZU.put("3", "3");
        ALBUMCate2MEIZU.put("5", "4");
        ALBUMCate2MEIZU.put("8", "10");
        ALBUMCate2MEIZU.put("9", "11");
        ALBUMCate2MEIZU.put("17", "11");
    }

    /* 魅族本地频道id和土豆专辑视频id映射 */
    public static final Map<String, Integer> CASTTYPE = new HashMap<String, Integer>();

    static {
        CASTTYPE.put("导演", CastEnum.DIRECTOR.getValue());
        CASTTYPE.put("主演", CastEnum.ACTOR.getValue());
        CASTTYPE.put("主持人", CastEnum.MASTER.getValue());
        CASTTYPE.put("嘉宾", CastEnum.GUEST.getValue());
        CASTTYPE.put("声优", CastEnum.VOICE.getValue());
    }

    public static final Map<String, String> STARCATE = new HashMap<String, String>();

    static {
        STARCATE.put("电影", "1");
        STARCATE.put("电视剧", "2");
        STARCATE.put("综艺", "3");
        STARCATE.put("动漫", "4");
        STARCATE.put("教育", "10");
        STARCATE.put("纪录片", "11");
    }
}
