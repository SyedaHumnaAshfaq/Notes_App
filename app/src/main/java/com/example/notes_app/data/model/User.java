package com.example.notes_app.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id
    private Long id;

    private String email;
    private String password;

    @Generated(hash = 1471192367)
    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
