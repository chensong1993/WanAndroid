package com.shanghai.templateapp.presenters.collect;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.base.connectors.collect.CollectConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.ApiResponse;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/22 16:25
 */
public class CollectPresenter extends RxPresenter<CollectConnector.View> implements CollectConnector.Presenter {

    DataManager mDataManager;

    @Inject
    public CollectPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(CollectConnector.View view) {
        super.attachView(view);
    }

    @Override
    public void getStar(int id) {
        addSubscribe(mDataManager.getStarInArticle(id)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ApiResponse>(mView) {
                    @Override
                    public void onNext(ApiResponse s) {
                        mView.starSucceed();
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
