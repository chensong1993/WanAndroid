package com.shanghai.templateapp.presenters.collect;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.base.connectors.collect.CollectListConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.ApiResponse;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.models.entity.WanArticleListEntity;
import com.shanghai.templateapp.models.entity.rxbus.CollectEvent;
import com.shanghai.templateapp.models.entity.rxbus.LoginEvent;
import com.shanghai.templateapp.models.entity.rxbus.StarInfoEvent;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/18 14:15
 */
public class CollectListPresenter extends RxPresenter<CollectListConnector.View> implements CollectListConnector.Presenter {

    DataManager mDataManager;

    @Inject
    public CollectListPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(CollectListConnector.View view) {
        super.attachView(view);
        registerEvent();
        registerLoginEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<CollectEvent>(mView) {
                    @Override
                    public void onNext(CollectEvent collect) {
                        mView.isCollect(collect.isCollect());
                    }
                }));
    }


    private void registerLoginEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<LoginEvent>(mView) {

                    @Override
                    public void onNext(LoginEvent isLogin) {
                        mView.isLogin(isLogin.isLogin());
                    }
                }));
    }

    @Override
    public void getCollectList(int page) {
        addSubscribe(mDataManager.getCollect(page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<WanArticleListEntity>(mView) {
                    @Override
                    public void onNext(WanArticleListEntity collectEntities) {
                        mView.collectList(collectEntities.getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }

    @Override
    public void getUnStar(int id) {
        addSubscribe(mDataManager.getUncollect(id)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ApiResponse>(mView) {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        mView.unStarSucceed();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.unErr();
                    }
                }));
    }
}
