package com.nelson.bncdemo.data.remote;

import com.nelson.bncdemo.data.local.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nelson on 8/4/2017.
 */

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();
}
