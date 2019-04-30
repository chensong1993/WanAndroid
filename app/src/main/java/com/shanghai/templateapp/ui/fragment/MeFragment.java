package com.shanghai.templateapp.ui.fragment;


import android.content.Intent;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.app.Constants;
import com.shanghai.templateapp.base.SimpleFragment;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.models.entity.rxbus.LoginEvent;
import com.shanghai.templateapp.models.http.cookies.CookiesManager;
import com.shanghai.templateapp.ui.activity.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class MeFragment extends SimpleFragment {

    @BindView(R.id.tv_login_out)
    TextView mTvLoginOut;
    @BindView(R.id.tv_username)
    TextView mTvUserName;
    String username;
    @BindView(R.id.fillStatusBarView)
    TextView mTvStatusBar;
    boolean isLogin=false;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initEventAndData() {
        username = App.kv.decodeString(Constants.USER_NAME);
        if (username == null) {
            mTvUserName.setText("点击登录");
            isLogin=false;
        } else {
            mTvUserName.setText(username);
            isLogin=true;
        }

//        Log.i("onResume", username);
    }

    @OnClick(R.id.tv_username)
    void onLogin() {
        if (!isLogin){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

    }

    @OnClick(R.id.tv_login_out)
    void onLoginOut() {
        isLogin=false;
        App.kv.remove(Constants.USER_NAME);
        CookiesManager.clearAllCookies();
        mTvUserName.setText("点击登录");
        RxBus.getDefault().post(new LoginEvent(false));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.kv.decodeString(Constants.USER_NAME) != null) {
            isLogin=true;
            mTvUserName.setText(App.kv.decodeString(Constants.USER_NAME));
        } else {
            isLogin=false;
            mTvUserName.setText("点击登录");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.refWatcher.watch(this);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
                .keyboardEnable(false)
                .statusBarView(mTvStatusBar)
                .statusBarColor(R.color.black)
                .init();
    }
}
