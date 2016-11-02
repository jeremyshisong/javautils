package hello.video.rank.service;

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
 * ReorderService
 */
@Service("reorderService")
public class ReorderService {

    @Autowired
    private SearchFacade searchFacade;


    private static String LOCAL = "local";
    private static String RANK = "rank";

    private Rank rank = new Rank();
    private static String bizVideoId = "video";
    private static int videoRuleId = 381;

    private static String BLANK = " ";
    private static String SPLIT = ":";

    @PostConstruct
    public void init() {
        try {
            Properties properties = new Properties();
            properties.load(ReorderService.class.getClassLoader().getResourceAsStream("video_train_model2.properties"));
            String model = properties.getProperty("model");
            rank.setModelString(model.toString());
            rank.loadModel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String localResults(String keywords, int LIMIT) {
        Map<String, String> videoParams = new HashMap<String, String>();
        videoParams.put("start", "" + 0);
        videoParams.put("rows", "" + LIMIT);
        Result result;
        String json;
        try {
            result = searchFacade.search(bizVideoId, videoRuleId, keywords, videoParams);
            json = result.getJson();
            json = json.replaceAll(" ", ",");
        } catch (Exception e) {
            return "";
        }

        return json;
    }


    public String rankResults(String keywords, int LIMIT) {
        Map<String, String> videoParams = new HashMap<String, String>();
        videoParams.put("start", "" + 0);
        videoParams.put("rows", "" + LIMIT);
        Result result;
        String ranked;
        try {
            result = searchFacade.search(bizVideoId, videoRuleId, keywords, videoParams);
            String json = result.getJson();
            json = json.replaceAll(" ", ",");
            ranked = reordering(json, keywords);
        } catch (Exception e) {
            return "";
        }

        return ranked;
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
        return parseRankAlbum(ret.getJson());
    }


    private String parseRankAlbum(String json) {
        if (StringUtils.isBlank(json)) {
            return json;
        }
        JSONArray array;
        try {
            array = JSONArray.parseArray(json);
        } catch (Exception e) {
            return json;
        }
        JSONArray ret = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            Map<String, Object> tmp = new LinkedHashMap();
            tmp.put("id", object.getString("id"));
            tmp.put("name", object.getString("name"));
            tmp.put("director", object.getString("director"));
            tmp.put("actor", object.getString("actor"));
            tmp.put("master", object.getString("master"));
            tmp.put("guest", object.getString("guest"));
            tmp.put("playCount", object.getInteger("playCount"));
            tmp.put("hots", object.getInteger("hots"));
            tmp.put("mzcid", object.getInteger("mzcid"));
            tmp.put("score", object.getDouble("score"));
            tmp.put("voiceActor", object.getString("voiceActor"));

            ret.add(tmp);
        }

        return ret.toJSONString();
    }

    private void parseIdAndFeas(JSONArray array, List<String> ids, List<String> feas) {
        String id;
        String feas1;
        String feas2;
        String feas3;
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            id = o.getString("id");
            ids.add(id);
            feas1 = o.getString("playCount");
            feas2 = o.getString("hots");
            feas3 = o.getString("score");
            String line = "1" + SPLIT + feas1 + BLANK + "2" + SPLIT + feas2+ BLANK + "3" + SPLIT + feas3;
            feas.add(line);
        }
    }

}
