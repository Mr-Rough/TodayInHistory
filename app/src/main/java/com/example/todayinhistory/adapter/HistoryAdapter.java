package com.example.todayinhistory.adapter;

import android.content.Context;

import com.example.todayinhistory.R;
import com.example.todayinhistory.base.BaseAdapter;
import com.example.todayinhistory.base.BaseViewHolder;
import com.example.todayinhistory.bean.SimpleHistory;

import java.util.List;

/**
 * Created by win7 on 2017/3/15.
 * 描述:
 * 作者:小智 win7
 */

public class HistoryAdapter extends BaseAdapter<SimpleHistory> {
    public HistoryAdapter(Context mContext, List<SimpleHistory> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, SimpleHistory t) {
        holder.setText(R.id.tv_time,t.getDate())
                .setText(R.id.tv_title,t.getTitle());

    }
}
