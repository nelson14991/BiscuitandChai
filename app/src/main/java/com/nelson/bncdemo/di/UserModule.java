package com.nelson.bncdemo.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.nelson.bncdemo.UserApplication;
import com.nelson.bncdemo.data.local.UsersDatabase;
import com.nelson.bncdemo.data.local.dao.UserDao;
import com.nelson.bncdemo.data.remote.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nelson on 8/2/2017.
 */
@Module
public class UserModule {
    private UserApplication app;

    public UserModule(UserApplication app) {
        this.app = app;
    }

    @Provides
    Context getApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    UsersDatabase getDatabase(Context context) {
        UsersDatabase db = Room.databaseBuilder(context.getApplicationContext(),UsersDatabase.class,"users_db").build();
        return db;
    }

    @Singleton @Provides
    ApiService getApiService() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }


    @Provides @Singleton
    UserDao getUserDao(UsersDatabase db){
        return db.userDao();
    }


}
