package com.shanghai.templateapp.base.connectors.collect;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;

/**
 * @author chensong
 * @date 2019/4/22 16:22
 */
public interface CollectConnector {
    interface View extends BaseView {
        void starSucceed();
        void unStarSucceed();
        void unErr();
    }

    interface Presenter extends BasePresenter<View> {
        void getStar(int id);
        void getUnStar(int id);
    }
}
