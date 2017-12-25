package com.example.witt.mvplearn03.zhihu;

import com.example.witt.mvplearn03.base.BasePresenter;
import com.example.witt.mvplearn03.base.BaseView;
import com.example.witt.mvplearn03.data.RootEntity;
import com.example.witt.mvplearn03.data.StoriesEntity;

import java.util.List;

/**
 * Created by witt on 2017/12/23.
 */

public interface MainContract {
    interface Presenter extends BasePresenter {
        RootEntity getLatestNews();

        RootEntity getSafety();

        RootEntity getInterest();

        RootEntity getSport();
    }

    interface View extends BaseView<Presenter> {
        void setTitle();

        void refresh(List<StoriesEntity> list);

        boolean showProgressBar(boolean show);
    }

}
