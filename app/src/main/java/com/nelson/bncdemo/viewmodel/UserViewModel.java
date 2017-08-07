package com.nelson.bncdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.nelson.bncdemo.UserApplication;
import com.nelson.bncdemo.data.local.dao.UserDao;
import com.nelson.bncdemo.data.local.model.User;
import com.nelson.bncdemo.data.remote.ApiService;
import com.nelson.bncdemo.di.DaggerUserComponent;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nelson on 8/4/2017.
 */

public class UserViewModel extends AndroidViewModel {

    @Inject
    UserDao userDao;
    @Inject
    ApiService apiService;


    public List<User> users;

    @Inject
    public UserViewModel(Application application) {
        super(application);
        ((UserApplication)application).getUserComponent().inject(this);
        loadDataFromNetwork();

    }

    public void setUsersFromDb() {
        userDao.getUsers().observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<User>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<User> user) {
                users = user;
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void setUser(Flowable<List<User>> listFlowable){
    }

    public List<User> getUsers(){
        return loadDataFromNetwork();
    }


    private List<User> loadDataFromNetwork(){
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("responsee",response.body().toString());
                users = response.body();
               // saveData(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
        return users;

    }
    private void saveData(List<User> users){



    }
}
