package com.javamaster.b2c.cloud.test.learn.java.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created on 2018/10/25.<br/>
 *
 * @author yudong
 */
public class SaveSuiteReqVO {

    private String opName;
    private String opCode;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opTime;
    @NotBlank
    private String suiteName;
    @NotBlank
    private String suiteCode;
    @Range(min = 1)
    private Integer marketPrice;
    @Range(max = 1)
    private Integer showDetail;
    @Range(max = 1)
    private Integer status;
    @Valid
    @NotNull
    private FileBean file;
    @Valid
    @Size(min = 1)
    private List<ProductsBean> products;

    private int pageSize;
    private int pageNo;

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getSuiteCode() {
        return suiteCode;
    }

    public void setSuiteCode(String suiteCode) {
        this.suiteCode = suiteCode;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getShowDetail() {
        return showDetail;
    }

    public void setShowDetail(int showDetail) {
        this.showDetail = showDetail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static class FileBean {

        @NotBlank
        @JsonProperty("pic_mainPic_1")
        private String mainPicture;

        @JsonProperty("pic_goodsInfo_specificsPic_1")
        private String picGoodsInfoSpecificsPic1;
        @JsonProperty("pic_goodsInfo_specificsPic_2")
        private String picGoodsInfoSpecificsPic2;
        @JsonProperty("pic_goodsInfo_specificsPic_3")
        private String picGoodsInfoSpecificsPic3;
        @JsonProperty("pic_goodsInfo_specificsPic_4")
        private String picGoodsInfoSpecificsPic4;
        @JsonProperty("pic_goodsInfo_specificsPic_5")
        private String picGoodsInfoSpecificsPic5;
        @JsonProperty("pic_goodsInfo_specificsPic_6")
        private String picGoodsInfoSpecificsPic6;

        @JsonProperty("pic_goodsInfo_detailPic_1")
        private String picGoodsInfoDetailPic1;
        @JsonProperty("pic_goodsInfo_detailPic_2")
        private String picGoodsInfoDetailPic2;
        @JsonProperty("pic_goodsInfo_detailPic_3")
        private String picGoodsInfoDetailPic3;
        @JsonProperty("pic_goodsInfo_detailPic_4")
        private String picGoodsInfoDetailPic4;
        @JsonProperty("pic_goodsInfo_detailPic_5")
        private String picGoodsInfoDetailPic5;
        @JsonProperty("pic_goodsInfo_detailPic_6")
        private String picGoodsInfoDetailPic6;

        public String getMainPicture() {
            return mainPicture;
        }

        public void setMainPicture(String mainPicture) {
            this.mainPicture = mainPicture;
        }

        public String getPicGoodsInfoSpecificsPic1() {
            return picGoodsInfoSpecificsPic1;
        }

        public void setPicGoodsInfoSpecificsPic1(String picGoodsInfoSpecificsPic1) {
            this.picGoodsInfoSpecificsPic1 = picGoodsInfoSpecificsPic1;
        }

        public String getPicGoodsInfoSpecificsPic2() {
            return picGoodsInfoSpecificsPic2;
        }

        public void setPicGoodsInfoSpecificsPic2(String picGoodsInfoSpecificsPic2) {
            this.picGoodsInfoSpecificsPic2 = picGoodsInfoSpecificsPic2;
        }

        public String getPicGoodsInfoSpecificsPic3() {
            return picGoodsInfoSpecificsPic3;
        }

        public void setPicGoodsInfoSpecificsPic3(String picGoodsInfoSpecificsPic3) {
            this.picGoodsInfoSpecificsPic3 = picGoodsInfoSpecificsPic3;
        }

        public String getPicGoodsInfoSpecificsPic4() {
            return picGoodsInfoSpecificsPic4;
        }

        public void setPicGoodsInfoSpecificsPic4(String picGoodsInfoSpecificsPic4) {
            this.picGoodsInfoSpecificsPic4 = picGoodsInfoSpecificsPic4;
        }

        public String getPicGoodsInfoSpecificsPic5() {
            return picGoodsInfoSpecificsPic5;
        }

        public void setPicGoodsInfoSpecificsPic5(String picGoodsInfoSpecificsPic5) {
            this.picGoodsInfoSpecificsPic5 = picGoodsInfoSpecificsPic5;
        }

        public String getPicGoodsInfoSpecificsPic6() {
            return picGoodsInfoSpecificsPic6;
        }

        public void setPicGoodsInfoSpecificsPic6(String picGoodsInfoSpecificsPic6) {
            this.picGoodsInfoSpecificsPic6 = picGoodsInfoSpecificsPic6;
        }

        public String getPicGoodsInfoDetailPic1() {
            return picGoodsInfoDetailPic1;
        }

        public void setPicGoodsInfoDetailPic1(String picGoodsInfoDetailPic1) {
            this.picGoodsInfoDetailPic1 = picGoodsInfoDetailPic1;
        }

        public String getPicGoodsInfoDetailPic2() {
            return picGoodsInfoDetailPic2;
        }

        public void setPicGoodsInfoDetailPic2(String picGoodsInfoDetailPic2) {
            this.picGoodsInfoDetailPic2 = picGoodsInfoDetailPic2;
        }

        public String getPicGoodsInfoDetailPic3() {
            return picGoodsInfoDetailPic3;
        }

        public void setPicGoodsInfoDetailPic3(String picGoodsInfoDetailPic3) {
            this.picGoodsInfoDetailPic3 = picGoodsInfoDetailPic3;
        }

        public String getPicGoodsInfoDetailPic4() {
            return picGoodsInfoDetailPic4;
        }

        public void setPicGoodsInfoDetailPic4(String picGoodsInfoDetailPic4) {
            this.picGoodsInfoDetailPic4 = picGoodsInfoDetailPic4;
        }

        public String getPicGoodsInfoDetailPic5() {
            return picGoodsInfoDetailPic5;
        }

        public void setPicGoodsInfoDetailPic5(String picGoodsInfoDetailPic5) {
            this.picGoodsInfoDetailPic5 = picGoodsInfoDetailPic5;
        }

        public String getPicGoodsInfoDetailPic6() {
            return picGoodsInfoDetailPic6;
        }

        public void setPicGoodsInfoDetailPic6(String picGoodsInfoDetailPic6) {
            this.picGoodsInfoDetailPic6 = picGoodsInfoDetailPic6;
        }
    }

    public static class ProductsBean {

        @NotBlank
        private String productSku;
        @NotBlank
        private String productName;
        @Size(min = 1)
        private int marketPrice;
        @Size(min = 1)
        private int num;

        public String getProductSku() {
            return productSku;
        }

        public void setProductSku(String productSku) {
            this.productSku = productSku;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
