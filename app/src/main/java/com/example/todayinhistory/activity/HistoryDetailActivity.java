package com.example.todayinhistory.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.todayinhistory.R;
import com.example.todayinhistory.base.BaseActivity;
import com.example.todayinhistory.base.HistoryLikeBean;
import com.example.todayinhistory.bean.Histroy;
import com.example.todayinhistory.bean.Picture;
import com.example.todayinhistory.common.Constants;
import com.example.todayinhistory.db.RealmHelper;
import com.example.todayinhistory.http.ImageLoader;
import com.example.todayinhistory.mvp.contact.HistoryDetailContact;
import com.example.todayinhistory.mvp.presenter.HistoryDetailPresenter;
import com.example.todayinhistory.utils.SnackBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryDetailActivity extends BaseActivity implements HistoryDetailContact.View {

    @BindView(R.id.iv_pic)
    ImageView mIvPic;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collToolBar)
    CollapsingToolbarLayout mCollToolBar;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.fab_like)
    FloatingActionButton mFab;
    @BindView(R.id.rl_container)
    RelativeLayout mAdContainer;

    private String mEid;
    private HistoryDetailPresenter mPresent;
    private HistoryLikeBean mLikeBean;
    private String mDate;
    private RealmHelper mRealmHelper;
    private String TAG = "TAG";

    @Override
    public void showFail(String msg) {
        SnackBarUtil.showLong(getWindow().getDecorView(), msg);
    }

    @Override
    public void showData(Histroy<Picture> history) {
        mCollToolBar.setTitle(history.getTitle());
        mTvContent.setText(history.getContent());
        if (Integer.parseInt(history.getPicNo()) > 0) {
            mLikeBean.setImg(history.getPicUrl().get(0).getUrl());
            ImageLoader.load(HistoryDetailActivity.this, history.getPicUrl().get(0).getUrl(), mIvPic);
        } else {
            ImageLoader.load(HistoryDetailActivity.this, mIvPic);
        }

        /**
         *  设置收藏数据
         */
        mLikeBean.seteId(history.getE_id());
        mLikeBean.setDate(mDate);
        mLikeBean.setTitle(history.getTitle());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();

        setToolbar();
    }

    private void init() {
        mEid = getIntent().getStringExtra(Constants.EID);
        mDate = getIntent().getStringExtra(Constants.DATE);

        mLikeBean = new HistoryLikeBean();
        mRealmHelper = new RealmHelper(this);

        initFabLike();

        mPresent = new HistoryDetailPresenter(this);

        mPresent.getHistoryData(mEid);
    }

    private void initFabLike() {
        if (mRealmHelper.queryHistoryLike(mEid)) {
            mFab.setSelected(true);
        } else {
            mFab.setSelected(false);
        }
    }

    private void setToolbar() {
        mCollToolBar.setExpandedTitleColor(Color.WHITE);
        mCollToolBar.setCollapsedTitleTextColor(Color.WHITE);

        setToolbar(mToolbar, "");
    }

    /**
     * 收藏
     */
    public void onLike(View view) {
        if (mFab.isSelected()) {
            mFab.setSelected(false);
            mRealmHelper.deleteHistoryLike(mEid);
        } else {
            mFab.setSelected(true);
            mRealmHelper.insertHistoryLike(mLikeBean);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.detachView();
    }
}
