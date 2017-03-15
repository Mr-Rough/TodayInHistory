package com.example.todayinhistory.mvp.contact;


import com.example.todayinhistory.base.BasePresent;
import com.example.todayinhistory.bean.SimpleHistory;

import java.util.List;

/**
 * Created by RaphetS on 2016/10/15.
 */

public class TodayInHistoryContact {
    public interface View {
        void showProgressDialog();

        void dismissProgressDialog();

        void showContent(List<SimpleHistory> result);

        void showFail(String error);
    }

    public interface Present extends BasePresent {
        void getData(int month, int day);
        //void getCurrentDate();
    }
}
