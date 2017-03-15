package com.example.todayinhistory.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.todayinhistory.R;
import com.example.todayinhistory.base.BaseActivity;
import com.example.todayinhistory.common.Constants;
import com.example.todayinhistory.http.ImageLoader;
import com.example.todayinhistory.utils.Tool;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 妹子详情界面
 */
public class GrilDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_gril)
    ImageView mIvGril;
    @BindView(R.id.ll_container)
    LinearLayout mLLContainer;
    @BindView(R.id.rl_container)
    RelativeLayout mAdContainer;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;

    private String mImgUrl;
    private Bitmap mBitmap;
    private PhotoViewAttacher mAttacher;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(mToolbar, "妹纸");

        initData();
    }

    private void initData() {
        mImgUrl = getIntent().getStringExtra(Constants.URL_IMG);
        ImageLoader.load(this, mImgUrl, new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mBitmap = resource;
                mIvGril.setImageBitmap(mBitmap);
                mAttacher = new PhotoViewAttacher(mIvGril);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gril_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                Tool.saveBitmapToFile(GrilDetailActivity.this, mImgUrl, mBitmap, mLLContainer, false);
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gril_detail;
    }
}
