/**
 * Project: galaxy-video
 * <p/>
 * File Created at 2015-1-5
 * $Id$
 */
package hello.video.model;

import java.io.Serializable;

/**
 * 专辑对象
 *
 * @author shisong
 */
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    /* 专辑ID */
    private String id;
    /* 专辑名称 */
    private String title;
    /* 导演 */
    private String director;
    /* 演员 */
    private String actor;
    /* 主持人 */
    private String master;
    /* 嘉宾 */
    private String guest;
    /* 声优 */
    private String voiceActor;
    /* 地区 */
    private String area;
    /* 专辑照片 */
    private String imgeUrl;
    /* 剧集数 */
    private String count;
    /* 专辑类型，如电影、电视剧 */
    private String category;
    /* 展示方式 */
    private String layout;
    /* 专辑题材，如战争，科幻 */
    private String genre;
    /* 专辑状态，是否下架 */
    private String status;
    // 付费方式，0：免费；1：单片；2：套餐
    private Integer feeType;
    /* 來源方id */
    private String cpid;
    /* 是否支持下载 */
    private boolean download;

    private double score;

    /**
     *
     */
    public Album() {
        super();
        this.download = true;
    }

    /**
     * @param id
     * @param title
     * @param director
     * @param actor
     * @param master
     * @param guest
     * @param area
     * @param imgeUrl
     * @param count
     * @param category
     * @param layout
     * @param genre
     * @param status
     * @param cpid
     */



    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the imgeUrl
     */
    public String getImgeUrl() {
        return imgeUrl;
    }

    /**
     * @param imgeUrl the imgeUrl to set
     */
    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the layout
     */
    public String getLayout() {
        return layout;
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(String layout) {
        this.layout = layout;
    }

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * @return the actor
     */
    public String getActor() {
        return actor;
    }

    /**
     * @param actor the actor to set
     */
    public void setActor(String actor) {
        this.actor = actor;
    }

    /**
     * @return the master
     */
    public String getMaster() {
        return master;
    }

    /**
     * @param master the master to set
     */
    public void setMaster(String master) {
        this.master = master;
    }

    /**
     * @return the guest
     */
    public String getGuest() {
        return guest;
    }

    /**
     * @param guest the guest to set
     */
    public void setGuest(String guest) {
        this.guest = guest;
    }

    /**
     * @return the voiceActor
     */
    public String getVoiceActor() {
        return voiceActor;
    }

    /**
     * @param voiceActor the voiceActor to set
     */
    public void setVoiceActor(String voiceActor) {
        this.voiceActor = voiceActor;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the feeType
     */
    public Integer getFeeType() {
        return feeType;
    }

    /**
     * @param feeType the feeType to set
     */
    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    /**
     * @return the cpid
     */
    public String getCpid() {
        return cpid;
    }

    /**
     * @param cpid the cpid to set
     */
    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    /**
     * @return the isDownload
     */
    public boolean isDownload() {
        return download;
    }

    /**
     * @param isDownload the isDownload to set
     */
    public void setDownload(boolean isDownload) {
        this.download = isDownload;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
