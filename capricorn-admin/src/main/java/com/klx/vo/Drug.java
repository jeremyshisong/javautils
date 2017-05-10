package com.klx.vo;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by shisong on 16/11/30.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class Drug implements Serializable {
    private Integer id;
    private String attentionItem;
    private String badReaction;
    private String cityCode;
    private String commonName;
    private Double costPrice;
    private String durgCharacters;
    private String durgComponent;
    private String durgToboo;
    private String functionsIndicated;
    private String cateCode;
    private String goodsCode;
    private String goodsName;
    private String goodsImage;
    private String goodsStatus;
    private Integer goodsType;
    private String isPrescriptionDrugs;
    private String manufacturerCode;
    private Double memberPrice;
    private Double posMemberPrice;
    private Double retailPrice;
    private String standard;
    private String storeCode;
    private String usagetxt;
    private Integer imageStatus;

    private static String image_url_prefix = "http://highlegopic.img-cn-beijing.aliyuncs.com/product/";
    private static String split = "/";
    private static String image_url_surfix = "a1.jpg?x-oss-process=style/256f";
    private static String defual_image = "http://highlegopic.img-cn-beijing.aliyuncs.com/product/0000000/0000000a1.jpg?x-oss-process=style/256f";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttentionItem() {
        return attentionItem;
    }

    public void setAttentionItem(String attentionItem) {
        this.attentionItem = attentionItem;
    }

    public String getBadReaction() {
        return badReaction;
    }

    public void setBadReaction(String badReaction) {
        this.badReaction = badReaction;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public String getDurgCharacters() {
        return durgCharacters;
    }

    public void setDurgCharacters(String durgCharacters) {
        this.durgCharacters = durgCharacters;
    }

    public String getDurgComponent() {
        return durgComponent;
    }

    public void setDurgComponent(String durgComponent) {
        this.durgComponent = durgComponent;
    }

    public String getDurgToboo() {
        return durgToboo;
    }

    public void setDurgToboo(String durgToboo) {
        this.durgToboo = durgToboo;
    }

    public String getFunctionsIndicated() {
        return functionsIndicated;
    }

    public void setFunctionsIndicated(String functionsIndicated) {
        this.functionsIndicated = functionsIndicated;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        if (StringUtils.isNotBlank(this.goodsImage)) {
            return this.goodsImage;
        } else {
            if (this.imageStatus != null && this.imageStatus == 1) {
                return image_url_prefix + this.goodsCode + split + this.goodsCode + image_url_surfix;
            } else {
                return defual_image;
            }
        }
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getIsPrescriptionDrugs() {
        return isPrescriptionDrugs;
    }

    public void setIsPrescriptionDrugs(String isPrescriptionDrugs) {
        this.isPrescriptionDrugs = isPrescriptionDrugs;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Double getPosMemberPrice() {
        return posMemberPrice;
    }

    public void setPosMemberPrice(Double posMemberPrice) {
        this.posMemberPrice = posMemberPrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getUsagetxt() {
        return usagetxt;
    }

    public void setUsagetxt(String usagetxt) {
        this.usagetxt = usagetxt;
    }

    @JsonIgnore
    public Integer getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(Integer imageStatus) {
        this.imageStatus = imageStatus;
    }
}
