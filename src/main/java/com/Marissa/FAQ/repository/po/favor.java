package com.Marissa.FAQ.repository.po;

import java.io.Serializable;

public class favor implements Serializable{
    private int id;
    private int docId;
    private int userId;

    public favor() {}

    public favor(int id, int docId, int userId) {
        this.id = id;
        this.docId = docId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
