package com.example.todayinhistory.mvp.contact;


import com.example.todayinhistory.base.BasePresent;
import com.example.todayinhistory.base.BaseView;
import com.example.todayinhistory.bean.Histroy;
import com.example.todayinhistory.bean.Picture;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class HistoryDetailContact {
    public interface View extends BaseView {
       void showData(Histroy<Picture> result);
    }

    public interface Present extends BasePresent {
        void getHistoryData(String eId);
    }
}
