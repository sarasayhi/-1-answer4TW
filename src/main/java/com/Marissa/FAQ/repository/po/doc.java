package com.Marissa.FAQ.repository.po;

import java.io.Serializable;
import java.util.Date;

public class doc implements Serializable{
    private int id;
    private String name;
    private String content;
    private String tags;
    private int collectCnt;
    private Date createTime;
    private Date updateTime;
    private int userId;

    public doc() {}

    public doc(int id, String name, String content, String tags, int collectCnt, Date createTime, Date updateTime, int userId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.tags = tags;
        this.collectCnt = collectCnt;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCollectCnt() {
        return collectCnt;
    }

    public void setCollectCnt(int collectCnt) {
        this.collectCnt = collectCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
