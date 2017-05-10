/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package com.chan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chan.constants.TudouVideoMap;
import com.chan.model.Album;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO Comment of BookService
 *
 * @author shisong
 */
@Service("videoService")
public class VideoService {
    private CloseableHttpClient httpClient;
    @Autowired
    private LocalSearchService localSearchService;

    @PostConstruct
    void init() {
        this.httpClient = HttpClients.createDefault();
    }
    public String getLocalResults(String keyword, int limit) {
        return localSearchService.localResults(keyword, limit);
    }

    public String getTudouResults(String keyword0) {
        String keyword = keyword0;
        try {
            keyword = URLEncoder.encode(keyword0, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Album> ret = new ArrayList<Album>();
        HttpGet httpget = new HttpGet("http://co.api.3g.tudou.com/v4_7/search/direct_all?guid=9c553730ef5b6c8c542bfd31b5e25b69&pid=36ee81f002cb577b&pg=1&pz=50&ip=112.91.151.218&keyword=" + keyword);
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            return JSON.toJSONString(ret);
        }
        HttpEntity entity = response.getEntity();
        String result;
        try {
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            return JSON.toJSONString(ret);
        }
        JSONObject tudouObject;
        JSONArray array;
        try {
            tudouObject = JSONObject.parseObject(result);
            array = tudouObject.getJSONArray("results");
        } catch (Exception e) {
            array = new JSONArray();
        }
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            if (object.getInteger("cate_id") == 6) {
                JSONArray tabs = object.getJSONArray("tabs");
                ret = startAlbums(tabs, keyword0);
            } else {
                Album album = generateTudouAlbum(object);
                if (album != null) {
                    ret.add(album);
                }
            }
        }
        return JSON.toJSONString(ret);
    }

    private Album generateTudouAlbum(JSONObject objJSON) {
        Album objAlbum = new Album();
        objAlbum.setTitle(objJSON.getString("title"));
        String notice = objJSON.getString("notice");
        if (notice == null) {
            return null;
        }
        Map<Integer, String> mapCast = buildCastMap(notice);
        for (Integer i : mapCast.keySet()) {
            switch (i.intValue()) {
                case 1:
                    objAlbum.setDirector(mapCast.get(i));
                    break;
                case 2:
                    objAlbum.setActor(mapCast.get(i));
                    break;
                case 3:
                    objAlbum.setMaster(mapCast.get(i));
                    break;
                case 4:
                    objAlbum.setGuest(mapCast.get(i));
                    break;
                case 5:
                    objAlbum.setVoiceActor(mapCast.get(i));
                    break;
            }
        }
        objAlbum.setArea(objJSON.getString("area"));
        objAlbum.setCategory(TudouVideoMap.ALBUMCate2MEIZU.get(objJSON.getString("cate_id")));
        objAlbum.setGenre(objJSON.getString("genre"));
        return objAlbum;
    }

    /* 构建导演、演员、主持人、嘉宾、声优等 */
    private Map<Integer, String> buildCastMap(String str) {

        Map<Integer, String> params = new HashMap<Integer, String>();
        String[] pairs = str.split("：");
        for (int i = 1; i < pairs.length; i++) {
            String pre = pairs[i - 1];
            String key = pre.substring(pre.length() - 2, pre.length());
            if ("持人".equals(key)) {
                key = pre.substring(pre.length() - 3, pre.length());
            }
            String value = pairs[i];
            Integer type = TudouVideoMap.CASTTYPE.get(key);
            if (type != null) {
                if (i >= 1) {
                    value = value.replaceAll("导演|主演|主持人|嘉宾|声优", "");
                }
                params.put(type, value.replace(",", "/"));
            }
        }
        return params;
    }


    private List<Album> startAlbums(JSONArray array, String star) {
        if (array == null) {
            return null;
        }
        List<Album> lstAlbum = new ArrayList<Album>();
        JSONObject objJSON;
        for (int i = 0; i < array.size(); i++) {
            objJSON = array.getJSONObject(i);
            String cateName = objJSON.getString("name");
            /* 不处理热门作品，以免重复 */
            if ("热门作品".equals(cateName)) {
                continue;
            }
            String cateId = TudouVideoMap.STARCATE.get(cateName);

            JSONArray albumsArray = objJSON.getJSONArray("videos");
            if (albumsArray == null) {
                continue;
            }
            for (int j = 0; j < albumsArray.size(); j++) {
                JSONObject albumJSON = albumsArray.getJSONObject(j);
                Album objAlbum = new Album();
                objAlbum.setTitle(albumJSON.getString("name"));
                objAlbum.setActor(star);
                if (StringUtils.isNotBlank(cateId)) {
                    objAlbum.setCategory(cateId);
                }
                lstAlbum.add(objAlbum);
            }
        }
        return lstAlbum;
    }
}
