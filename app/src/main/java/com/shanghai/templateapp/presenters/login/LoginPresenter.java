package com.shanghai.templateapp.presenters.login;

import android.util.Log;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.base.connectors.login.LoginConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.LoginEntity;
import com.shanghai.templateapp.models.entity.rxbus.LoginEvent;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 */
public class LoginPresenter extends RxPresenter<LoginConnector.View> implements LoginConnector.Presenter {

    DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LoginConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getShowLogin(String name, String password) {
        addSubscribe(dataManager.getLogin(name, password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LoginEntity>(mView) {
                    @Override
                    public void onNext(LoginEntity entities) {
                        Log.i("onNext", entities.getUsername() + entities.getPassword());
                        mView.showLogin(entities);
                        RxBus.getDefault().post(new LoginEvent(true, entities.getUsername()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                        RxBus.getDefault().post(new LoginEvent(false, null));
                    }
                }));
    }
}
