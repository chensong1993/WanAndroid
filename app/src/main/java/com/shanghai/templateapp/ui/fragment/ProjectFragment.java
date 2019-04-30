package com.shanghai.templateapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.app.Constants;
import com.shanghai.templateapp.base.BaseFragment;
import com.shanghai.templateapp.base.connectors.news.ProjectConnector;
import com.shanghai.templateapp.models.entity.ProjectEntity;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.presenters.news.ProjectPresenter;
import com.shanghai.templateapp.ui.activity.ArticleDetailActivity;
import com.shanghai.templateapp.ui.adapter.home.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;


public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectConnector.View {

    @BindView(R.id.tab_project)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.rv_project)
    RecyclerView mRvPorject;
    @BindView(R.id.fillStatusBarView)
    TextView mTvStatusBar;
    List<String> titleList;
    ProjectAdapter mProjectAdapter;
    List<ProjectEntity> mProjectList;
    List<WanArticleEntity> mArticleEntityList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getProjectData();
        titleList = new ArrayList<>();
        mProjectList = new ArrayList<>();
        mArticleEntityList = new ArrayList<>();
        mProjectAdapter = new ProjectAdapter(mArticleEntityList);
        mRvPorject.setAdapter(mProjectAdapter);
        mRvPorject.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        mProjectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ARTICLE_STAR_ID, mProjectAdapter.getData().get(i).getId());
                bundle.putString(Constants.ARTICLE_STAR_TITLE, mProjectAdapter.getData().get(i).getTitle());
                bundle.putString(Constants.ARTICLE_STAR_AUTHOR, mProjectAdapter.getData().get(i).getAuthor());
                bundle.putString(Constants.ARTICLE_STAR_LINK, mProjectAdapter.getData().get(i).getLink());
                intent.putExtra(Constants.ARTICLE_STAR, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void projectData(List<ProjectEntity> entityList) {
        mProjectList = entityList;
        for (int i = 0; i < entityList.size(); i++) {
            titleList.add(entityList.get(i).getName());
        }
        mProjectAdapter.setNewData(mProjectList.get(0).getArticles());
        initData();
        Log.i("titleList", titleList.size() + "");
    }


    @Override
    public void stateError() {

    }

    private void initData() {
        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                Log.i("onTabSelected", position + "");
                mArticleEntityList = mProjectList.get(position).getArticles();
                mProjectAdapter.setNewData(mProjectList.get(position).getArticles());
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        mVerticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new QTabView
                        .TabTitle.Builder()
                        .setContent(titleList.get(position))
                        .setTextColor(ContextCompat.getColor(getActivity(),R.color.black), ContextCompat.getColor(getActivity(),R.color.hui1))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
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
        ImmersionBar.with(this)
                .statusBarView(mTvStatusBar)
                .statusBarColor(R.color.black)
                .init();
    }


}
