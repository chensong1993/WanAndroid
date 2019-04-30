package com.shanghai.templateapp.ui.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.Constants;
import com.shanghai.templateapp.base.BaseFragment;
import com.shanghai.templateapp.base.connectors.news.FriendConnector;
import com.shanghai.templateapp.models.entity.FriendEntity;
import com.shanghai.templateapp.presenters.news.FriendPresenter;
import com.shanghai.templateapp.ui.activity.ArticleDetailActivity;
import com.shanghai.templateapp.ui.adapter.home.FriendAdapter;
import com.shanghai.templateapp.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class FirendFragment extends BaseFragment<FriendPresenter> implements FriendConnector.View {

    @BindView(R.id.rv_friend)
    RecyclerView mRvStar;
    FriendAdapter mFriendAdapter;
    List<FriendEntity> mFriendList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initEventAndData() {
        mFriendList = new ArrayList<>();
        mFriendAdapter = new FriendAdapter(mFriendList);
        mRvStar.setAdapter(mFriendAdapter);
        mRvStar.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, 14, ContextCompat.getColor(getActivity(), R.color.hui)));
        mRvStar.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.getFriendData();
        mFriendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt(Constants.ARTICLE_STAR_ID, mFriendAdapter.getData().get(i).getId());
                bundle.putString(Constants.ARTICLE_STAR_LINK, mFriendAdapter.getData().get(i).getLink());
                intent.putExtra(Constants.ARTICLE_STAR, bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public void friendData(List<FriendEntity> friendEntityList) {
        mFriendList = friendEntityList;
        mFriendAdapter.addData(friendEntityList);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initImmersionBar() {

    }
}
