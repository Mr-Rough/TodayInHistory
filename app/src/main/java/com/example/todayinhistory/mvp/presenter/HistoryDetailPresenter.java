package com.example.todayinhistory.mvp.presenter;

import com.example.todayinhistory.bean.Histroy;
import com.example.todayinhistory.bean.Picture;
import com.example.todayinhistory.http.HttpResponse;
import com.example.todayinhistory.http.RetrofitHelper;
import com.example.todayinhistory.mvp.contact.HistoryDetailContact;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class HistoryDetailPresenter implements HistoryDetailContact.Present {

    private HistoryDetailContact.View mView;

    public HistoryDetailPresenter(HistoryDetailContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getHistoryData(String eId) {
        RetrofitHelper.getInstance().getHistoryDetail(eId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse<Histroy<Picture>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null) {
                            mView.showFail("获取数据失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResponse<Histroy<Picture>> httpResponse) {
                        if (mView != null) {
                            if (httpResponse.getError_code() == 0) {
                                List<Histroy<Picture>> histroyList = httpResponse.getResult();
                                if (histroyList.size() > 0) {
                                    mView.showData(histroyList.get(0));
                                } else {
                                    mView.showFail("暂无数据");
                                }
                            } else {
                                mView.showFail(httpResponse.getReason());
                            }


                        }
                    }
                });
    }
}
