package com.example.todayinhistory.mvp.presenter;


import com.example.todayinhistory.bean.SimpleHistory;
import com.example.todayinhistory.http.HttpResponse;
import com.example.todayinhistory.http.RetrofitHelper;
import com.example.todayinhistory.mvp.contact.TodayInHistoryContact;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RaphetS on 2016/10/15.
 */

public class TodayInHistoryPresenter implements TodayInHistoryContact.Present {
    private TodayInHistoryContact.View mView;

    public TodayInHistoryPresenter(TodayInHistoryContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void getData(int month, int day) {
        RetrofitHelper.getInstance()
                .getHistoryList(month,day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse<SimpleHistory>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null) {
                            mView.showFail(e.toString());
                        }
                    }

                    @Override
                    public void onNext(HttpResponse<SimpleHistory> httpResponse) {
                        if (mView!=null){
                             if (httpResponse.getError_code()==0){
                                 mView.showContent(httpResponse.getResult());
                             }else {
                                 mView.showFail(httpResponse.getReason());
                             }
                        }
                    }
                });
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
