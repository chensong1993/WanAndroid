package com.shanghai.templateapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


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

    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
        //  initEventAndData();
    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        //  unregisterReceiver(receiver);
    }



    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
