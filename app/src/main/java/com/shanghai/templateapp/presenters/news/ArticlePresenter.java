package com.shanghai.templateapp.presenters.news;

import android.util.Log;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.base.connectors.news.ArticleConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.ApiResponse;
import com.shanghai.templateapp.models.entity.BannerEntity;
import com.shanghai.templateapp.models.entity.TopNewsEntity;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.models.entity.WanArticleListEntity;
import com.shanghai.templateapp.models.entity.rxbus.LoginEvent;
import com.shanghai.templateapp.models.entity.rxbus.StarInfoEvent;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author chensong
 * @date 2019/4/15 11:41
 */
public class ArticlePresenter extends RxPresenter<ArticleConnector.View> implements ArticleConnector.Presenter {
    private DataManager mDataManager;

    @Inject
    public ArticlePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ArticleConnector.View view) {
        super.attachView(view);
        registerStarInfoEvent();
        registerLoginEvent();
    }

    private void registerStarInfoEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(StarInfoEvent.class)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<StarInfoEvent>(mView) {

                    @Override
                    public void onNext(StarInfoEvent starInfo) {
                        mView.isStarInfo(starInfo.getId(), starInfo.isCollect());
                    }
                }));
    }

    private void registerLoginEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<LoginEvent>(mView) {

                    @Override
                    public void onNext(LoginEvent isLogin) {
                        mView.isLogin(isLogin.isLogin(),isLogin.getUserName());
                    }
                }));
    }

    @Override
    public void getStar(int id) {
        addSubscribe(mDataManager.getStarInArticle(id)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ApiResponse>(mView) {
                    @Override
                    public void onNext(ApiResponse s) {
                        mView.starSucceed();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }

    @Override
    public void getUnStar(int id) {
        addSubscribe(mDataManager.getUncollect(id)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ApiResponse>(mView) {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        mView.unStarSucceed();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.unErr();
                    }
                }));
    }

    @Override
    public void getNewsDetail(int page) {
        addSubscribe(mDataManager.getAtricle(page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<WanArticleListEntity>(mView) {
                    @Override
                    public void onNext(WanArticleListEntity wanArticleEntities) {
                        mView.showNewsDetail(wanArticleEntities.getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.err();
                    }
                })
        );
    }

    @Override
    public void getBanner() {
        addSubscribe(mDataManager.getBanner()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<BannerEntity>>(mView) {
                    @Override
                    public void onNext(List<BannerEntity> bannerEntity) {
                        mView.Banner(bannerEntity);

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.err();
                        super.onError(e);
                    }
                })
        );
    }

    @Override
    public void getTopNews() {
        addSubscribe(mDataManager.getTopNews()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<WanArticleEntity>>(mView) {

                    @Override
                    public void onNext(List<WanArticleEntity> topNewsEntities) {
                        mView.showTopNews(topNewsEntities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }
}
