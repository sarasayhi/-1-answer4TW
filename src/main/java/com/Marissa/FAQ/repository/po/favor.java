package com.Marissa.FAQ.repository.po;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "favor")
public class favor implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "docId")
    private int docId;
    @Column(name = "userId")
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
