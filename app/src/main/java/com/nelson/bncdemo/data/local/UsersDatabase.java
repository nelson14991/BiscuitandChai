package com.nelson.bncdemo.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.nelson.bncdemo.data.local.model.User;
import com.nelson.bncdemo.data.local.dao.UserDao;

/**
 * Created by Nelson on 8/4/2017.
 */
@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
