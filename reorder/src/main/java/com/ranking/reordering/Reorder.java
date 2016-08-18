package com.ranking.reordering;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meizu.galaxy2.api.Result;
import com.meizu.galaxy2.api.SearchFacade;
import com.meizu.galaxy2.rank.alg.Rank;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Reorder
 */
@Service("reorder")
public class Reorder {

    @Autowired
    private SearchFacade searchFacade;

    private Rank rank = new Rank();
    private static String bizVideoId = "video";
    private static int videoRuleId = 381;

    private static String BLANK = " ";
    private static String SPLIT = ":";

    @PostConstruct
    public void init() {
        try {
            Properties properties = new Properties();
            properties.load(Reorder.class.getClassLoader().getResourceAsStream("video_train_model.properties"));
            String model = properties.getProperty("model");
            rank.setModelString(model.toString());
            rank.loadModel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Result videoCallBack(String keywords, int LIMIT) {
        Map<String, String> videoParams = new HashMap<String, String>();
        videoParams.put("start", "" + 0);
        videoParams.put("rows", "" + LIMIT);
        Result ret;
        try {
            ret = searchFacade.search(bizVideoId, videoRuleId, keywords, videoParams);

            String json = ret.getJson();
            json = json.replaceAll(" ", ",");
            System.out.println(json);
            String ranked = reordering(json, keywords);
            System.out.println(ranked);
            ret.setJson(ranked);
        } catch (Exception e) {
            return new Result();
        }

        return ret;
    }

    private String reordering(String json, String keywords) {
        if (StringUtils.isBlank(json)) {
            return json;
        }
        JSONArray array;
        try {
            array = JSONArray.parseArray(json);
        } catch (Exception e) {
            return json;
        }
        List<String> ids = new ArrayList<String>(array.size());
        List<String> feas = new ArrayList<String>(array.size());
        parseIdAndFeas(array, ids, feas);
        System.out.println("before reordering");
        System.out.println(JSON.toJSONString(ids));
        List<String> rankedIds = rank.itemsReRank(keywords, ids, feas);
        System.out.println("after reordering");
        System.out.println(JSON.toJSONString(rankedIds));
        Result ret = searchFacade.searchDocsByIds(bizVideoId, rankedIds, "id");
        return ret.getJson();
    }

    private void parseIdAndFeas(JSONArray array, List<String> ids, List<String> feas) {
        String id;
        String feas1;
        String feas2;
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            id = o.getString("id");
            ids.add(id);
            feas1 = o.getString("playCount");
            feas2 = o.getString("hots");
            String line = "1" + SPLIT + feas1 + BLANK + "2" + SPLIT + feas2;
            feas.add(line);
        }
    }

}
