package com.nelson.bncdemo.di;


import com.nelson.bncdemo.UserListActivity;
import com.nelson.bncdemo.viewmodel.UserViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nelson on 8/2/2017.
 */
@Singleton
@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(UserViewModel userViewModel);

}
