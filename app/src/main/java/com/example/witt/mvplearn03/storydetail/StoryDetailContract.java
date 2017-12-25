package com.example.witt.mvplearn03.storydetail;


import com.example.witt.mvplearn03.base.BasePresenter;
import com.example.witt.mvplearn03.base.BaseView;
import com.example.witt.mvplearn03.data.StoryDetailsEntity;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface StoryDetailContract {

    interface Presenter extends BasePresenter {
        StoryDetailsEntity getNewsDetail(int id);
    }

    interface View extends BaseView<Presenter> {
        void loadWebView(String s);
    }
}
