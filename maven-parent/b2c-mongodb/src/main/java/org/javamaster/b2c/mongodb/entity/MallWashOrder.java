package org.javamaster.b2c.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yudong
 * @date 2020/7/1
 */
@Data
@Document
public class MallWashOrder implements Serializable {
    private static final long serialVersionUID = -1284489681856971099L;
    @MongoId
    private String orderCode;
    private String orderStatus;
    private Integer orderType;
    private String activityCode;
    private String activityName;
    private String sourceName;
    private String sourceCode;
    private String sourceType;
    private String washCenterCode;
    private String washCenterName;
    private Integer payTotal;
    private Date createTime;
    private Date payTime;
    private Date collectTime;
    private Date receiveTime;
    private Date finishReceiveTime;
    private Date cancelTime;
    private Date refundTime;
    private MallWashCollectOrder mallWashCollectOrder;
}
