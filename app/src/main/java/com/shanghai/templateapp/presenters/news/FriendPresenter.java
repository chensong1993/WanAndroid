package com.shanghai.templateapp.presenters.news;

import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.connectors.news.FriendConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.FriendEntity;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import nucleus5.presenter.Presenter;

/**
 * @author chensong
 * @date 2019/4/16 13:25
 */
public class FriendPresenter extends RxPresenter<FriendConnector.View> implements FriendConnector.Presenter {

    private DataManager dataManager;

    @Inject
    public FriendPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(FriendConnector.View view) {
        super.attachView(view);
    }

    @Override
    public void getFriendData() {
        addSubscribe(dataManager.getFriend()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<FriendEntity>>(mView) {
                    @Override
                    public void onNext(List<FriendEntity> friendEntityList) {
                        mView.friendData(friendEntityList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }
}
