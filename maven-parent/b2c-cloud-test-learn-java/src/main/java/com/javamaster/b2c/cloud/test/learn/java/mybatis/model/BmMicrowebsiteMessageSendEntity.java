package com.javamaster.b2c.cloud.test.learn.java.mybatis.model;

import java.util.Date;


/**
 * 消息发送记录表
 *
 * @author yu
 * @email yu@qq.com
 * @date 2019-03-21 08:47:31
 */
public class BmMicrowebsiteMessageSendEntity {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //关联员工表emp_code
    private Integer empCode;
    //消息标题
    private String messageTitle;
    //消息内容
    private String messageContent;
    //是否已读,0:否;1:是
    private Integer alreadyRead;
    //删除标志,0:正常;1:已删除
    private Integer delFlag;
    //创建人编号
    private Integer createCode;
    //创建人名称
    private String createName;
    //创建时间
    private Date createTime;
    //操作人编号
    private Integer opCode;
    //操作人名称
    private String opName;
    //操作时间
    private Date opTime;

    @Override
    public String toString() {
        return "BmMicrowebsiteMessageSendEntity{" +
                "id=" + id +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }

    /**
     * 获取：主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：关联员工表emp_code
     */
    public Integer getEmpCode() {
        return empCode;
    }

    /**
     * 设置：关联员工表emp_code
     */
    public void setEmpCode(Integer empCode) {
        this.empCode = empCode;
    }

    /**
     * 获取：消息标题
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * 设置：消息标题
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    /**
     * 获取：消息内容
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 设置：消息内容
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * 获取：是否已读,0:否;1:是
     */
    public Integer getAlreadyRead() {
        return alreadyRead;
    }

    /**
     * 设置：是否已读,0:否;1:是
     */
    public void setAlreadyRead(Integer alreadyRead) {
        this.alreadyRead = alreadyRead;
    }

    /**
     * 获取：删除标志,0:正常;1:已删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置：删除标志,0:正常;1:已删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取：创建人编号
     */
    public Integer getCreateCode() {
        return createCode;
    }

    /**
     * 设置：创建人编号
     */
    public void setCreateCode(Integer createCode) {
        this.createCode = createCode;
    }

    /**
     * 获取：创建人名称
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置：创建人名称
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：操作人编号
     */
    public Integer getOpCode() {
        return opCode;
    }

    /**
     * 设置：操作人编号
     */
    public void setOpCode(Integer opCode) {
        this.opCode = opCode;
    }

    /**
     * 获取：操作人名称
     */
    public String getOpName() {
        return opName;
    }

    /**
     * 设置：操作人名称
     */
    public void setOpName(String opName) {
        this.opName = opName;
    }

    /**
     * 获取：操作时间
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * 设置：操作时间
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}
