package com.shanghai.templateapp.ui.activity.login;


import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.app.Constants;
import com.shanghai.templateapp.base.BaseActivity;
import com.shanghai.templateapp.base.SimpleActivity;
import com.shanghai.templateapp.base.connectors.login.LoginConnector;
import com.shanghai.templateapp.models.entity.LoginEntity;
import com.shanghai.templateapp.models.http.cookies.CookiesManager;
import com.shanghai.templateapp.presenters.login.LoginPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @note 登录主界面
 * @anthor Song Chen
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginConnector.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_forgetpwd)
    TextView mTvForgetPwd;
    Animation mShakeAnim;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_view);
    }

    @OnClick(R.id.tv_login)
    void onLogin() {
        String phone = mEtPhone.getText().toString();
        String pwd = mEtPwd.getText().toString();
        Log.i("onLogin", phone + pwd);
        mPresenter.getShowLogin(phone, pwd);
    }

    @OnClick(R.id.tv_forgetpwd)
    void onForgetPwd() {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    @OnClick(R.id.tv_register)
    void onRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void showLogin(LoginEntity entities) {
        App.kv.encode(Constants.USER_NAME, entities.getUsername());
        ToastUtils.show("登录成功");
        finish();
    }

    @Override
    public void stateError() {
        super.stateError();
        mTvLogin.startAnimation(mShakeAnim);
        ToastUtils.show("账号密码有误");
        CookiesManager.clearAllCookies();
        App.kv.remove(Constants.USER_NAME);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.refWatcher.watch(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
