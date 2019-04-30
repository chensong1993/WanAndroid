package com.shanghai.templateapp.base.connectors.login;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.models.entity.LoginEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface LoginConnector {

    interface View extends BaseView {
        void showLogin(LoginEntity entities);
    }

    interface Presenter extends BasePresenter<View> {
        void getShowLogin(String name, String password);
    }
}
