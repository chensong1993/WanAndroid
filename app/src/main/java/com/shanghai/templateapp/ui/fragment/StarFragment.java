package com.shanghai.templateapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.gyf.immersionbar.ImmersionBar;
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
import com.shanghai.templateapp.base.connectors.collect.CollectListConnector;
import com.shanghai.templateapp.base.connectors.news.FriendConnector;
import com.shanghai.templateapp.models.entity.CollectEntity;
import com.shanghai.templateapp.models.entity.FriendEntity;
import com.shanghai.templateapp.models.entity.MultipleItem;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.models.entity.rxbus.StarInfoEvent;
import com.shanghai.templateapp.presenters.collect.CollectListPresenter;
import com.shanghai.templateapp.presenters.news.FriendPresenter;
import com.shanghai.templateapp.ui.activity.ArticleDetailActivity;
import com.shanghai.templateapp.ui.activity.login.LoginActivity;
import com.shanghai.templateapp.ui.adapter.collect.CollectListAdapter;
import com.shanghai.templateapp.ui.adapter.home.ArticleAdapter;
import com.shanghai.templateapp.ui.adapter.home.FriendAdapter;
import com.shanghai.templateapp.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class StarFragment extends BaseFragment<CollectListPresenter> implements CollectListConnector.View {

    @BindView(R.id.rv_friend)
    RecyclerView mRvStar;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    ArticleAdapter mCollectAdapter;
    List<MultipleItem> mCollectList;
    @BindView(R.id.s_refresh)
    SmartRefreshLayout mRefresh;
    int page = 0;
    @BindView(R.id.fillStatusBarView)
    TextView mTvStatusBar;
    int originId;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_star;
    }

    @Override
    protected void initEventAndData() {
        mCollectList = new ArrayList<>();
        if (App.kv.decodeString(Constants.USER_NAME) != null) {
            mRvStar.setVisibility(View.VISIBLE);
            mTvLogin.setVisibility(View.GONE);
            mPresenter.getCollectList(page);
            initData();
            mRefresh.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    mPresenter.getCollectList(0);
                }
            });
            mRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    mPresenter.getCollectList(++page);
                }
            });
        } else {
            mRvStar.setVisibility(View.GONE);
            mTvLogin.setVisibility(View.VISIBLE);
        }
    }

    public void initData() {
        mCollectAdapter = new ArticleAdapter(mCollectList);
        mRvStar.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, 14, ContextCompat.getColor(getActivity(), R.color.hui)));
        mRvStar.setLayoutManager(new LinearLayoutManager(getActivity()));
       // Log.i("initiii", mCollectList.size() + "");
        mCollectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ARTICLE_STAR_ID, mCollectAdapter.getData().get(i).getEntities().getOriginId());
                bundle.putString(Constants.ARTICLE_STAR_TITLE, mCollectAdapter.getData().get(i).getEntities().getTitle());
                bundle.putString(Constants.ARTICLE_STAR_AUTHOR, mCollectAdapter.getData().get(i).getEntities().getAuthor());
                bundle.putString(Constants.ARTICLE_STAR_LINK, mCollectAdapter.getData().get(i).getEntities().getLink());
                bundle.putBoolean(Constants.ARTICLE_STAR_COLLECT, true);
                intent.putExtra(Constants.ARTICLE_STAR, bundle);
                startActivity(intent);
            }
        });
        mCollectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                originId=mCollectAdapter.getData().get(position).getEntities().getOriginId();
                mPresenter.getUnStar(mCollectAdapter.getData().get(position).getEntities().getOriginId());
            }
        });
        mRvStar.setAdapter(mCollectAdapter);
    }

    @OnClick(R.id.tv_login)
    void onLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }


    @Override
    public void collectList(List<WanArticleEntity> collectList) {
         mCollectList = new ArrayList<>();
        for (int i = 0; i < collectList.size(); i++) {
            mCollectList.add(new MultipleItem(MultipleItem.STAR, collectList.get(i)));
        }
        if (page < 1) {
            Log.i("collectList", mCollectList.size() + "");
            mCollectAdapter.setNewData(mCollectList);
        } else {
            if (collectList.size() > 0) {
                mCollectAdapter.addData(mCollectList);
                Log.i("collectList1", " size" + page);
            } else {
                page = 0;
                mRefresh.finishLoadmoreWithNoMoreData();
            }

        }
        mRefresh.finishLoadmore();
        mRefresh.finishRefresh();

    }

    @Override
    public void isLogin(boolean b) {
        Log.i("isLogin", b + "");
        if (b) {
            mPresenter.getCollectList(0);
            mRvStar.setVisibility(View.VISIBLE);
            mTvLogin.setVisibility(View.GONE);
        } else {
            mRvStar.setVisibility(View.GONE);
            mTvLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void isCollect(boolean c) {
        Log.i("isCollect", c + "");
        if (c) {
            mPresenter.getCollectList(0);
        }

    }

    @Override
    public void unStarSucceed() {
        mPresenter.getCollectList(0);
        RxBus.getDefault().post(new StarInfoEvent(originId,false));
    }

    @Override
    public void unErr() {
        ToastUtils.show("取消失败");
    }


    @Override
    public void stateError() {
        super.stateError();
        ToastUtils.show("请求失败");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.refWatcher.watch(this);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void initImmersionBar() {

    }
}
