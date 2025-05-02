package com.example.notes_app;

import android.app.Application;

import com.example.notes_app.data.dao.DaoMaster;
import com.example.notes_app.data.dao.DaoSession;
import com.example.notes_app.data.dao.UserDao;
import com.example.notes_app.data.model.User;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"notes_db");
        Database db = helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();
        insertDummyUser();
    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }
    private void insertDummyUser() {
        UserDao userDao = daoSession.getUserDao();

        // Check if user already exists to avoid duplication
        if (userDao.queryBuilder().where(UserDao.Properties.Email.eq("test@example.com")).unique() == null) {
            User user = new User(null, "test@example.com", "1234");
            userDao.insert(user);
        }
    }
}
