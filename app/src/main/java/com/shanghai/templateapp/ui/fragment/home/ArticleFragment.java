package com.shanghai.templateapp.ui.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.app.Constants;
import com.shanghai.templateapp.base.BaseFragment;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.base.connectors.news.ArticleConnector;
import com.shanghai.templateapp.models.db.entity.CollectDbEntity;
import com.shanghai.templateapp.models.entity.BannerEntity;
import com.shanghai.templateapp.models.entity.CollectEntity;
import com.shanghai.templateapp.models.entity.MultipleItem;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.models.entity.rxbus.CollectEvent;
import com.shanghai.templateapp.presenters.news.ArticlePresenter;
import com.shanghai.templateapp.ui.activity.ArticleDetailActivity;
import com.shanghai.templateapp.ui.activity.login.LoginActivity;
import com.shanghai.templateapp.ui.adapter.home.ArticleAdapter;
import com.shanghai.templateapp.util.GlideImage;
import com.shanghai.templateapp.util.RecycleViewDivider;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;


public class ArticleFragment extends BaseFragment<ArticlePresenter> implements ArticleConnector.View {

    ArticleAdapter articleAdapter, topNewsAdapter;
    @BindView(R.id.rv_article)
    RecyclerView mRvArticle;
    @BindView(R.id.sRefresh)
    SmartRefreshLayout mRefreshLayout;
    List<MultipleItem> ArticleList;
    List<MultipleItem> TopNewsList;
    //    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.ns_scroll)
    NestedScrollView mScrollView;
    @BindView(R.id.rv_top)
    RecyclerView mRvTop;
    int page = 0;
    boolean isCollect, mIsLogin;
    String userName;
    CollectDbEntity mCollectEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        userName = App.kv.decodeString(Constants.USER_NAME, null);
        ArticleList = new ArrayList<>();
        TopNewsList = new ArrayList<>();
        mPresenter.getNewsDetail(0);
        mPresenter.getBanner();
        mPresenter.getTopNews();
        LayoutInflater bannerLayout = LayoutInflater.from(getActivity());
        View view = bannerLayout.inflate(R.layout.item_banner, null);
        mBanner = view.findViewById(R.id.banner);
        articleAdapter = new ArticleAdapter(ArticleList);
        topNewsAdapter = new ArticleAdapter(TopNewsList);
        mRvTop.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTop.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        mRvTop.setNestedScrollingEnabled(false);
        mRvArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvArticle.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        mRvArticle.setNestedScrollingEnabled(false);
        mBanner.setImageLoader(new GlideImage());
        RefreshData();
        MoreData();
        articleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ARTICLE_STAR_ID, articleAdapter.getData().get(i).getEntities().getId());
                int id = articleAdapter.getData().get(i).getEntities().getId();
                bundle.putBoolean(Constants.ARTICLE_STAR_COLLECT, isCollectDb(id));
                bundle.putString(Constants.ARTICLE_STAR_TITLE, articleAdapter.getData().get(i).getEntities().getTitle());
                bundle.putString(Constants.ARTICLE_STAR_AUTHOR, articleAdapter.getData().get(i).getEntities().getAuthor());
                bundle.putString(Constants.ARTICLE_STAR_LINK, articleAdapter.getData().get(i).getEntities().getLink());
                //  Log.i("onItemClick", articleAdapter.getData().get(i).getEntities().isCollect() + "  " + articleAdapter.getData().get(i).getEntities().getId());
                intent.putExtra(Constants.ARTICLE_STAR, bundle);
                startActivity(new Intent(intent));
            }
        });

        topNewsAdapter.addHeaderView(view);
        topNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                int id = topNewsAdapter.getData().get(i).getEntities().getId();
                bundle.putBoolean(Constants.ARTICLE_STAR_COLLECT, isCollectDb(id));
                bundle.putInt(Constants.ARTICLE_STAR_ID, topNewsAdapter.getData().get(i).getEntities().getId());
                bundle.putString(Constants.ARTICLE_STAR_TITLE, topNewsAdapter.getData().get(i).getEntities().getTitle());
                bundle.putString(Constants.ARTICLE_STAR_AUTHOR, topNewsAdapter.getData().get(i).getEntities().getAuthor());
                bundle.putString(Constants.ARTICLE_STAR_LINK, topNewsAdapter.getData().get(i).getEntities().getLink());
                intent.putExtra(Constants.ARTICLE_STAR, bundle);

                startActivity(new Intent(intent));
            }
        });
        mRvTop.setAdapter(topNewsAdapter);
        mRvArticle.setAdapter(articleAdapter);
        //        articleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
//                //判断是否登录
//                if (userName != null) {
//                    mTvCollect = view.findViewById(R.id.tv_collect);
//                    //是收藏还是取消收藏
//                    //还缺少一步判断网络是否可用
//                    if (isCollect) {
//                        articleId = articleAdapter.getData().get(i).getEntities().getId();
//                        mPresenter.getUnStar(articleAdapter.getData().get(i).getEntities().getId());
//                    } else {
//                        articleId = articleAdapter.getData().get(i).getEntities().getId();
//                        mPresenter.getStar(articleAdapter.getData().get(i).getEntities().getId());
//                    }
//                } else {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                }
//            }
//        });
//        topNewsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
//                mTvCollect = view.findViewById(R.id.tv_collect);
//                //判断是否登录
//                //还缺少一步判断网络是否可用
//                if (userName != null) {
//                    //是收藏还是取消收藏
//                    if (isCollect) {
//                        articleId = topNewsAdapter.getData().get(i).getEntities().getId();
//                        mPresenter.getUnStar(topNewsAdapter.getData().get(i).getEntities().getId());
//                    } else {
//                        articleId = topNewsAdapter.getData().get(i).getEntities().getId();
//                        mPresenter.getStar(topNewsAdapter.getData().get(i).getEntities().getId());
//                    }
//                } else {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                }
//            }
//        });

    }

    @Override
    public void isStarInfo(int id, boolean isStar) {
        if (isStar) {
            Log.i("isStarInfo1", id + "");
            mCollectEntity = new CollectDbEntity(null, userName, id, isStar);
            App.getInstance().getCollectEntityDao().insert(mCollectEntity);
        } else {
            for (Iterator iterator = App.getInstance().getCollectEntityDao().queryBuilder().list().iterator(); iterator.hasNext(); ) {
                CollectDbEntity entity = (CollectDbEntity) iterator.next();
                if (userName.equals(entity.getUserId()) && id == entity.getStarId()) {
                    App.getInstance().getCollectEntityDao().deleteByKey(entity.get_id());
                    Log.i("isStarInfo", entity.get_id() + "  " + entity.getStarId());
                }
            }
        }

    }

    @Override
    public void isLogin(boolean login, String name) {
        mIsLogin = login;
        userName = name;

    }

    public boolean isCollectDb(int id) {
        boolean collect = false;
        for (Iterator iterator = App.getInstance().getCollectEntityDao().queryBuilder().list().iterator(); iterator.hasNext(); ) {
            CollectDbEntity entity = (CollectDbEntity) iterator.next();
            if ((entity.getUserId()).equals(userName) && id == entity.getStarId()) {
                collect = true;
            }
        }
        return collect;
    }

    @Override
    public void starSucceed() {
    }

    @Override
    public void stateError() {
        super.stateError();
        ToastUtils.show("收藏失败");
    }

    @Override
    public void unStarSucceed() {

    }

    @Override
    public void unErr() {
        ToastUtils.show("取消失败");
    }

    public void RefreshData() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getNewsDetail(0);
                mPresenter.getTopNews();
                mPresenter.getBanner();
            }
        });
    }

    public void MoreData() {
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getNewsDetail(++page);
                Log.i("onLoadmore", page + "");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onStart", "onStart: ");
//        if (articleId > 0) {
//            if (isCollect) {
//                mTvCollect.setText("取消收藏");
//            } else {
//                mTvCollect.setText("收藏");
//            }
//        }

        // mBanner.startAutoPlay();
    }

    @Override
    public void showNewsDetail(List<WanArticleEntity> newsDetail) {
        ArticleList = new ArrayList<>();
        for (int i = 0; i < newsDetail.size(); i++) {
            ArticleList.add(new MultipleItem(MultipleItem.ARTICLE, newsDetail.get(i)));
        }
        if (page < 1) {
            articleAdapter.setNewData(ArticleList);
        } else {
            if (newsDetail.size() > 0) {
                articleAdapter.addData(ArticleList);
            } else {
                page = 0;
                mRefreshLayout.finishLoadmoreWithNoMoreData();
            }

        }

        mRefreshLayout.finishLoadmore();
        mRefreshLayout.finishRefresh();

    }

    @Override
    public void showTopNews(List<WanArticleEntity> topNewsEntity) {
        TopNewsList = new ArrayList<>();
        for (int i = 0; i < topNewsEntity.size(); i++) {
            TopNewsList.add(new MultipleItem(MultipleItem.ARTICLE, topNewsEntity.get(i)));
        }
        // TopNewsList = topNewsEntity;
        topNewsAdapter.setNewData(TopNewsList);
    }


    @Override
    public void Banner(List<BannerEntity> bannerEntities) {

        List<String> mImageUrls = new ArrayList<>();
        List<String> mBannerTitles = new ArrayList<>();
        for (BannerEntity it : bannerEntities) {
            mImageUrls.add(it.getImagePath());
            mBannerTitles.add(it.getTitle());
        }
        mBanner.setImages(mImageUrls);
        mBanner.setBannerTitles(mBannerTitles);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ARTICLE_STAR_ID, bannerEntities.get(i).getId());
                bundle.putString(Constants.ARTICLE_STAR_TITLE, bannerEntities.get(i).getTitle());
                bundle.putString(Constants.ARTICLE_STAR_LINK, bannerEntities.get(i).getUrl());
                intent.putExtra(Constants.ARTICLE_STAR, bundle);
                startActivity(new Intent(intent));

            }
        });
        mBanner.start();

    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
        //  mBanner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void err() {
        mRefreshLayout.finishLoadmore();
        mRefreshLayout.finishLoadmore();
        Log.i("showNewsDetail", "errrr");
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initImmersionBar() {

    }
}
