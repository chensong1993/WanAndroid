package com.shanghai.templateapp.presenters.login;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.base.connectors.login.RegisterConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.LoginEntity;
import com.shanghai.templateapp.models.entity.rxbus.UserInfoEvent;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/18 17:35
 */
public class RegisterPresenter extends RxPresenter<RegisterConnector.View> implements RegisterConnector.Presenter {
    DataManager mDataManager;

    @Inject
    public RegisterPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(RegisterConnector.View view) {
        super.attachView(view);
    }

    @Override
    public void getRegisterData(String username, String pwd, String repassword) {
        addSubscribe(mDataManager.getRegister(username, pwd, repassword)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LoginEntity>(mView) {
                    @Override
                    public void onNext(LoginEntity registerEntity) {
                        mView.registerUserData(registerEntity);
                        // RxBus.getDefault().post(new UserInfoEvent(registerEntity.getUsername()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }
}
