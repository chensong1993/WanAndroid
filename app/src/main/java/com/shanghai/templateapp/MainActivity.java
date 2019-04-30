package com.shanghai.templateapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.base.BaseActivity;
import com.shanghai.templateapp.base.SimpleActivity;
import com.shanghai.templateapp.ui.adapter.main.MainViewPagerAdapter;
import com.shanghai.templateapp.widget.navigation.BottomNavigation;
import com.shanghai.templateapp.widget.navigation.BottomNavigationAdapter;
import com.shanghai.templateapp.widget.navigation.BottomNavigationViewPager;

import butterknife.BindView;

public class MainActivity extends SimpleActivity {

    @BindView(R.id.view_pager)
    BottomNavigationViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigation bottomNavigation;
    @BindView(R.id.ll_dow)
    LinearLayout mLinearLayout;
    long mBackTime;

    private MainViewPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }

    @Override
    protected void initEventAndData() {
        setSwipeBackEnable(false);
        // 隐藏导航栏Items
        BottomNavigationAdapter navigationAdapter = new BottomNavigationAdapter(this, R.menu.menu_bottom_navigation);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // 隐藏导航栏标题
        bottomNavigation.setTitleState(BottomNavigation.TitleState.ALWAYS_SHOW);
        // 导航点击事件
        bottomNavigation.setOnTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);

                return true;
            }
        });

        viewPager.setOffscreenPageLimit(3);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mBackTime > 2000) {
                Toast.makeText(mContext, "再按一次退出", Toast.LENGTH_SHORT).show();
                mBackTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.refWatcher.watch(this);
    }
}
