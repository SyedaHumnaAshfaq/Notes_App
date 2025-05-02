package com.example.notes_app.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class Note {
    @Id
    private Long Id;

    private Long userId;

    private String content;
    private Date timestamp;

    private String title;

    @Generated(hash = 1180323667)
    public Note(Long Id, Long userId, String content, Date timestamp,
            String title) {
        this.Id = Id;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
        this.title = title;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
