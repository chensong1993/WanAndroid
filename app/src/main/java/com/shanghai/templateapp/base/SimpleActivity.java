package com.shanghai.templateapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public abstract class SimpleActivity extends SwipeBackActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        initEventAndData();

        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.black)
                .init();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        App.refWatcher.watch(this);
        //  unregisterReceiver(receiver);
    }


    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
