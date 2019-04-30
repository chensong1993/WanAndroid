package com.shanghai.templateapp.base.connectors.news;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.models.entity.FriendEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/16 13:26
 */
public interface FriendConnector {
    interface View extends BaseView{
        void friendData(List<FriendEntity> friendEntityList);
    }

    interface Presenter extends BasePresenter<View>{
        void getFriendData();
    }
}
