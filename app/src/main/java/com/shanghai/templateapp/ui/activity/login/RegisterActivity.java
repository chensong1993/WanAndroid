package com.shanghai.templateapp.ui.activity.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.app.Constants;
import com.shanghai.templateapp.base.BaseActivity;
import com.shanghai.templateapp.base.connectors.login.RegisterConnector;
import com.shanghai.templateapp.models.entity.LoginEntity;
import com.shanghai.templateapp.presenters.login.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterConnector.View {

    @BindView(R.id.et_user_name)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_repassword)
    EditText mEtRepassword;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    String username, password, repassword;
    Animation mShakeAnim;
    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        mShakeAnim= AnimationUtils.loadAnimation(this,R.anim.shake_view);
    }
    @OnClick(R.id.tv_register)
    void OnRegister(){
        username = mEtUserName.getText().toString();
        password = mEtPassword.getText().toString();
        repassword = mEtRepassword.getText().toString();
        mPresenter.getRegisterData(username, password, repassword);
    }
    @Override
    public void registerUserData(LoginEntity loginEntity) {
        ToastUtils.show("注册成功");
        finish();
    }

    @Override
    public void stateError() {
        super.stateError();
        mTvRegister.startAnimation(mShakeAnim);
        ToastUtils.show("注册失败");
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
