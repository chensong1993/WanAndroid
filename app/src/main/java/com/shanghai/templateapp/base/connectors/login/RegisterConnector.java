package com.shanghai.templateapp.base.connectors.login;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.models.entity.LoginEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/18 17:37
 */
public interface RegisterConnector {
    interface View extends BaseView {
        /**
         * 注册信息
         */
        void registerUserData(LoginEntity loginEntity);
    }

    interface Presenter extends BasePresenter<View> {
        void getRegisterData(String username, String pwd, String repassword);
    }
}
