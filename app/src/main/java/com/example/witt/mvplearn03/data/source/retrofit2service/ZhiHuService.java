package com.example.witt.mvplearn03.data.source.retrofit2service;


import com.example.witt.mvplearn03.data.RootEntity;
import com.example.witt.mvplearn03.data.StoryDetailsEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chengsy on 2016/5/26.
 */
public interface ZhiHuService {

    //今日头条
    @GET("news/latest")
    Observable<RootEntity> getLatestNews();

    //互联网安全
    @GET("theme/10")
    Observable<RootEntity> getSafety();

    //不准无聊
    @GET("theme/11")
    Observable<RootEntity> getInterest();

    //体育日报
    @GET("theme/8")
    Observable<RootEntity> getSport();

    //查看详细信息
    @GET("news/{id}")
    Observable<StoryDetailsEntity> getNewsDetails(@Path("id") int id);
}
