package com.example.todayinhistory.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todayinhistory.R;
import com.example.todayinhistory.activity.GrilDetailActivity;
import com.example.todayinhistory.adapter.GrilAdapter;
import com.example.todayinhistory.base.BaseFragment;
import com.example.todayinhistory.bean.GrilBean;
import com.example.todayinhistory.common.Constants;
import com.example.todayinhistory.mvp.contact.GrilContact;
import com.example.todayinhistory.mvp.presenter.GrilPresenter;
import com.example.todayinhistory.utils.SnackBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by win7 on 2017/3/15.
 * 描述:妹子列表
 * 作者:小智 win7
 */

public class GrilFragment extends BaseFragment implements GrilContact.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.floatActionBtn)
    FloatingActionButton mFab;

    private GridLayoutManager mLayoutManager;
    private GrilPresenter mPresent;
    private ArrayList<GrilBean> mDatas = new ArrayList<>();
    private GrilAdapter mAdapter;

    @Override
    protected void initEvents() {

        initSwipeRefreshLayout();
        initRecyclerView();
        addListener();
    }

    private void initSwipeRefreshLayout() {
        mSrl.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresent.getGrilList();
            }
        });
        mPresent = new GrilPresenter(this);
        mPresent.getGrilList();
    }

    private void initRecyclerView() {
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new GrilAdapter(getActivity(), mDatas, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addListener() {
        mAdapter.setOnLoadMoreListener(new GrilAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresent.getMoreGril();
            }
        });
        mAdapter.setOnItemClickListener(new GrilAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GrilDetailActivity.class);
                intent.putExtra(Constants.URL_IMG, mDatas.get(position).getUrl());

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "shareView");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gril;
    }

    @Override
    public void showFail(String msg) {
        mAdapter.loadCompleted();
        if (mSrl.isRefreshing()) {
            mSrl.setRefreshing(false);
        }
        SnackBarUtil.showLong(mRootView, "获取数据失败");
    }

    @Override
    public void showContent(List<GrilBean> data) {
        if (mSrl.isRefreshing()) {
            mSrl.setRefreshing(false);
        }
        mDatas.clear();
        mDatas.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreGril(List<GrilBean> data) {
        mAdapter.loadCompleted();
        if (data != null && data.size() > 0) {

            mDatas.addAll(data);
            mAdapter.notifyItemRangeInserted(mDatas.size() - data.size(), data.size());
        } else {
            SnackBarUtil.showLong(mRootView, "没有更多数据了");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresent.detachView();
    }
}
