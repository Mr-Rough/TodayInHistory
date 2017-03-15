package com.example.todayinhistory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.todayinhistory.R;
import com.example.todayinhistory.base.BaseFragment;
import com.example.todayinhistory.http.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by win7 on 2017/3/15.
 * 描述:
 * 作者:小智 win7
 */

public class AboutFragment extends BaseFragment {
    @BindView(R.id.iv_pic)
    ImageView mIvPic;

    @Override
    protected void initEvents() {
        ImageLoader.load(getActivity(),mIvPic);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
