package org.javamaster.b2c.mongodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author yudong
 * @date 2020/7/1
 */
@Document
public class MallWashCollectOrder implements Serializable {
    private static final long serialVersionUID = -1284489681856971099L;
    private String collectCode;
    private String collectStatus;
    private Integer clothesNum;
    private List<MallWashClothes> mallWashClothesList;

    public String getCollectCode() {
        return collectCode;
    }

    public void setCollectCode(String collectCode) {
        this.collectCode = collectCode;
    }

    public String getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(String collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Integer getClothesNum() {
        return clothesNum;
    }

    public void setClothesNum(Integer clothesNum) {
        this.clothesNum = clothesNum;
    }

    public List<MallWashClothes> getMallWashClothesList() {
        return mallWashClothesList;
    }

    public void setMallWashClothesList(List<MallWashClothes> mallWashClothesList) {
        this.mallWashClothesList = mallWashClothesList;
    }
}
