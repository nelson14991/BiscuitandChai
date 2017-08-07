package com.nelson.bncdemo;


import com.nelson.bncdemo.di.DaggerUserComponent;
import com.nelson.bncdemo.di.UserComponent;
import com.nelson.bncdemo.di.UserModule;

/**
 * Created by Nelson on 8/4/2017.
 */

public class UserApplication extends android.app.Application {
    private UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        userComponent = createUserComponent();
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    private UserComponent createUserComponent() {
        return DaggerUserComponent.builder()
                .userModule(new UserModule(this))
                .build();
    }
}
