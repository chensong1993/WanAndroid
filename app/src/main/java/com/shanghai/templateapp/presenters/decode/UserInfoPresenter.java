package com.shanghai.templateapp.presenters.decode;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.connectors.decode.UserInfoConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.UserInfoList;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/19 14:58
 */
public class UserInfoPresenter extends RxPresenter<UserInfoConnector.View> implements UserInfoConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserInfoConnector.View view) {
        super.attachView(view);
    }

    @Override
    public void getUserInfo() {
        addSubscribe(dataManager.getUserInfo("zhongyin","zhongyinv2","index")
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<UserInfoList>(mView) {
                    @Override
                    public void onNext(UserInfoList infoEntities) {
                        mView.setUserInfo(infoEntities.getLists());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }

    @Override
    public void getUpUsetInfo(String jsonArray) {
        addSubscribe(dataManager.upUserInfo(jsonArray)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<String>(mView) {
                    @Override
                    public void onNext(String jsonArrays) {
                        mView.upUserInfo(jsonArrays);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }


}
