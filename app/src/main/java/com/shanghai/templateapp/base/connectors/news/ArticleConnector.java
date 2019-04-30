package com.shanghai.templateapp.base.connectors.news;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.models.entity.BannerEntity;
import com.shanghai.templateapp.models.entity.TopNewsEntity;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.models.entity.WanArticleListEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/15 11:37
 */
public interface ArticleConnector {
    interface View extends BaseView {
        void showNewsDetail(List<WanArticleEntity> newsDetail);

        void showTopNews(List<WanArticleEntity> topNewsEntity);

        void Banner(List<BannerEntity> bannerEntities);

        void isStarInfo(int id, boolean isStar);

        void isLogin(boolean login,String userName);

        void starSucceed();

        void unStarSucceed();

        void unErr();

        void err();
    }

    interface Presenter extends BasePresenter<View> {
        void getNewsDetail(int page);

        void getBanner();

        void getTopNews();

        void getStar(int id);

        void getUnStar(int id);
    }
}
