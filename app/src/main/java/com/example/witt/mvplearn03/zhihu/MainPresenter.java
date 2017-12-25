package com.example.witt.mvplearn03.zhihu;

import android.content.Context;
import android.util.Log;

import com.example.witt.mvplearn03.data.RootEntity;
import com.example.witt.mvplearn03.data.StoriesEntity;
import com.example.witt.mvplearn03.data.source.retrofit2service.ZhiHuService;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by witt on 2017/12/23.
 */

public class MainPresenter implements MainContract.Presenter {

    private String baseUrl = "https://news-at.zhihu.com//api/4/";

    private MainContract.View mMainView;
    private Context mContext;

    protected ZhiHuService service;

    public MainPresenter(Context context) {
        this.mContext = context;
    }

    public void setView(MainContract.View view) {
        this.mMainView = view;
        mMainView.setPresenter(this);
        mMainView.setTitle();
        service = getService();
    }

    public ZhiHuService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(ZhiHuService.class);
        return service;
    }

    @Override
    public void start() {

    }

    @Override
    public RootEntity getLatestNews() {
        return loadData(service.getLatestNews());
    }

    @Override
    public RootEntity getSafety() {
        return loadData(service.getSafety());
    }

    @Override
    public RootEntity getInterest() {
        return loadData(service.getInterest());
    }

    @Override
    public RootEntity getSport() {
        return loadData(service.getSport());
    }

    public RootEntity loadData(Observable<RootEntity> observable) {
        mMainView.showProgressBar(true);
        final RootEntity rootEntity = new RootEntity();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RootEntity, ArrayList<StoriesEntity>>() {
                    @Override
                    public ArrayList<StoriesEntity> call(RootEntity rootEntity) {
                        return rootEntity.getStories();
                    }
                })
                .subscribe(new Subscriber<ArrayList<StoriesEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e,"...");
                        mMainView.showProgressBar(false);
                    }

                    @Override
                    public void onNext(ArrayList<StoriesEntity> storiesEntities) {
                        rootEntity.setStories(storiesEntities);
                        mMainView.refresh(storiesEntities);
                        mMainView.showProgressBar(false);
                    }
                });

        return rootEntity;
    }


}
