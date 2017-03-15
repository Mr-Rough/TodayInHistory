package com.example.todayinhistory.mvp.contact;

import com.example.todayinhistory.base.BasePresent;
import com.example.todayinhistory.base.BaseView;
import com.example.todayinhistory.bean.GrilBean;

import java.util.List;

/**
 * Created by win7 on 2017/3/15.
 * 描述:
 * 作者:小智 win7
 */

public class GrilContact {
    public interface View extends BaseView {
        void showContent(List<GrilBean> data);

        void showMoreGril(List<GrilBean> data);
    }

    public interface Present extends BasePresent {
        void getGrilList();

        void getMoreGril();
    }
}
