package com.Marissa.FAQ.repository.po;

import java.io.Serializable;
import java.util.Date;

public class comment implements Serializable {
    private int id;
    private String comment;
    private int userId;
    private int docId;
    private Date createDate;

    public comment() {}

    public comment(int id, String comment, int userId, int docId, Date createDate) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.docId = docId;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
