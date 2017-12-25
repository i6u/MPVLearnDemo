package com.example.witt.mvplearn03.storydetail;


import com.example.witt.mvplearn03.data.StoryDetailsEntity;
import com.example.witt.mvplearn03.data.source.retrofit2service.ZhiHuService;
import com.example.witt.mvplearn03.util.HtmlUtils;
import com.orhanobut.logger.Logger;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class StoryDetailPresenter implements StoryDetailContract.Presenter {

    private String baseUrl = "https://news-at.zhihu.com/api/4/";

    private StoryDetailContract.View mDetailView;

    //要靠他来获取消息
    protected ZhiHuService service;

    public StoryDetailPresenter(StoryDetailContract.View view) {
        this.mDetailView = view;
        mDetailView.setPresenter(this);
        service = getService();
    }

    @Override
    public void start() {

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
    public StoryDetailsEntity getNewsDetail(int id) {
        service.getNewsDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<StoryDetailsEntity, String>() {
                    @Override
                    public String call(StoryDetailsEntity storyDetailsEntity) {
                        return  HtmlUtils.structHtml(storyDetailsEntity);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "...");
                    }

                    @Override
                    public void onNext(String s) {
                        mDetailView.loadWebView(s);
                    }
                });
        return null;
    }
}
