package com.example.notes_app.data.repository;
import android.app.Application;

import com.example.notes_app.App;
import com.example.notes_app.data.dao.UserDao;
import com.example.notes_app.data.model.User;

public class LoginRepository {
    private final UserDao userDao;
    public LoginRepository(Application application) {
        userDao = App.getDaoSession().getUserDao();
    }
    public boolean isValidLoginCredentials(String email, String password){
       User user = userDao.queryBuilder().where(UserDao.Properties.Email.eq(email) , UserDao.Properties.Password.eq(password)).unique();
       return user!=null;
    }
}
