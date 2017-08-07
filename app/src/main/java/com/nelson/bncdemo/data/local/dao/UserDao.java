package com.nelson.bncdemo.data.local.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.nelson.bncdemo.data.local.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


/**
 * Created by Nelson on 8/2/2017.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> saveUsers(List<User> userEntities);
    @Query("SELECT * FROM User")
    Flowable<List<User>> getUsers();
    @Query("SELECT * FROM User WHERE id =:userid")
    Flowable<User> getUserDetails(int userid);

}
