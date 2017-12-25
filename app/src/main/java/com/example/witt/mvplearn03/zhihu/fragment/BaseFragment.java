package com.example.witt.mvplearn03.zhihu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.witt.mvplearn03.R;
import com.example.witt.mvplearn03.data.StoriesEntity;
import com.example.witt.mvplearn03.storydetail.StoryDetailActivity;
import com.example.witt.mvplearn03.storydetail.StoryDetailFragment;
import com.example.witt.mvplearn03.zhihu.MainContract;
import com.example.witt.mvplearn03.zhihu.ZhiHuNewsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by witt on 2017/12/23.
 */

public class BaseFragment extends Fragment implements MainContract.View {

    @BindView(R.id.lv_news)
    ListView mListView;
    @BindView(R.id.pb_main)
    ProgressBar mProgressBar;

    protected MainContract.Presenter mPresenter;
    protected ActionBar mActionBar;
    private ZhiHuNewsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ZhiHuNewsAdapter(getContext());
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            setTitle();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle(R.string.app_name);
        }
    }

    @Override
    public void refresh(final List<StoriesEntity> list) {
        mAdapter.setNewsList(list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                intent.putExtra(StoryDetailFragment.STORY_ID, list.get(position).getId());
                intent.putExtra(StoryDetailFragment.STORY_TITLE, list.get(position).getTitle());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean showProgressBar(boolean show) {
        mProgressBar.setVisibility(show?View.VISIBLE:View.GONE);
        return show;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
