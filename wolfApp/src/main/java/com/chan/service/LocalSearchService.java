package com.chan.service;

import com.alibaba.fastjson.JSON;
import com.meizu.video.service.VideoSearchService;
import com.meizu.video.vo.Album;
import com.meizu.video.vo.ResultCollection;
import com.meizu.video.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LocalSearchService
 */
@Service("reorderService")
public class LocalSearchService {
    @Autowired
    private VideoSearchService videoSearchService;


    public String localResults(String keywords, int LIMIT) {
        Map<String, String> videoParams = new HashMap<String, String>();
        videoParams.put("start", "" + 0);
        videoParams.put("rows", "" + LIMIT);
        SearchResult result;
        String json;
        try {
            result = videoSearchService.searchVideosFromApi(keywords, 0, 16, true, "172.17.188.142", 1, LIMIT);
            ResultCollection value = result.getValue();
            List<Album> albums = null;
            if (value != null) {
                albums = value.getAlbum();
                rmAlbumVideos(albums);
            }
            if (albums != null && albums.size() > 0) {
                json = JSON.toJSONString(albums);
                json = json.replaceAll(" ", ",");
            } else {
                json = "";
            }
        } catch (Exception e) {
            return "";
        }
        return json;
    }

    private void rmAlbumVideos(List<Album> albums) {
        if (albums != null && albums.size() > 0) {
            for (Album album : albums) {
                album.setVideos(null);
            }
        }

    }
}
