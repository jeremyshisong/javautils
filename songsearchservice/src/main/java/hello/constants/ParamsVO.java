package hello.constants;

import com.meizu.galaxy2.biz.api.O2OSearchService;

import java.io.Serializable;

/**
 * Created by shisong on 16/1/11.
 */
public class ParamsVO implements Serializable {
    private static final long serialVersionUID = 94593202347325L;

    private String searchId;
    private String imei;

    private int page;
    private int limit;
    private String keyword;
    private String bref;
    private String suggestCity;
    private String geoloc;

    /**
     * 不同的排序条件，默认为default
     */
    private O2OSearchService.SortBy condition = O2OSearchService.SortBy.DEFAULT;

    /**
     * 商圈过滤条件
     */
    private String areaFilter;

    /**
     * 城市
     */
    private String city;

    // 辅助数据
    private String clientIp;
    private String networkType;
    private String version;
    private String device;
    private String os;


    public String getSearchId() {
        return searchId;
    }


    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }


    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public String getKeyword() {
        return keyword;
    }

    public String getGeoloc() {
        return geoloc;
    }

    public void setGeoloc(String geoloc) {
        this.geoloc = geoloc;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public O2OSearchService.SortBy getCondition() {
        return condition;
    }


    public void setCondition(O2OSearchService.SortBy condition) {
        this.condition = condition;
    }


    public String getAreaFilter() {
        return areaFilter;
    }


    public void setAreaFilter(String areaFilter) {
        this.areaFilter = areaFilter;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getImei() {
        return imei;
    }


    public void setImei(String imei) {
        this.imei = imei;
    }


    public String getClientIp() {
        return clientIp;
    }


    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }


    public String getNetworkType() {
        return networkType;
    }


    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    public String getDevice() {
        return device;
    }


    public void setDevice(String device) {
        this.device = device;
    }


    public String getOs() {
        return os;
    }


    public void setOs(String os) {
        this.os = os;
    }

    public String getBref() {
        return bref;
    }

    public void setBref(String bref) {
        this.bref = bref;
    }

    public String getSuggestCity() {
        return suggestCity;
    }

    public void setSuggestCity(String suggestCity) {
        this.suggestCity = suggestCity;
    }
}
