package com.shanghai.templateapp.base.connectors.decode;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.models.entity.UserInfoEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/19 14:59
 */
public interface UserInfoConnector {
    interface View extends BaseView {
        void setUserInfo(List<UserInfoEntity> userInfoEntities);
        void upUserInfo(String jsonArrays);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserInfo();
        void getUpUsetInfo(String jsonArray);
    }
}
