package com.shanghai.templateapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.base.SimpleFragment;
import com.shanghai.templateapp.ui.adapter.home.HomeViewPagerAdapter;
import com.shanghai.templateapp.ui.fragment.home.ArticleFragment;
import com.shanghai.templateapp.ui.fragment.home.FirendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends SimpleFragment {

    @BindView(R.id.tab_home)
    SmartTabLayout mSmartTabLayout;
    @BindView(R.id.vp_home)
    ViewPager mViewPager;
    @BindView(R.id.fillStatusBarView)
    TextView mTvStatusBar;
    HomeViewPagerAdapter mPagerAdapter;
    List<Fragment> mFragmentList=new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home2;
    }



    @Override
    protected void initEventAndData() {
        mFragmentList.clear();
        mFragmentList.add(new ArticleFragment());
        mFragmentList.add(new StarFragment());
        mPagerAdapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager(), mFragmentList);
         mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mPagerAdapter);
        mSmartTabLayout.setViewPager(mViewPager);
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarView(mTvStatusBar)
                .statusBarColor(R.color.black)
                .init();
    }
}
