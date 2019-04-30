package com.shanghai.templateapp.base.connectors.collect;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.models.entity.CollectEntity;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.models.entity.WanArticleListEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/18 14:17
 */
public interface CollectListConnector {

    interface View extends BaseView{
        void collectList(List<WanArticleEntity> collectEntities);
        void isLogin(boolean b);
        void isCollect(boolean c);
        void unStarSucceed();
        void unErr();

    }
    interface Presenter extends BasePresenter<View>{
        void getCollectList(int page);
        void getUnStar(int id);
    }
}
