package com.shanghai.templateapp.injections.components;

import android.app.Activity;

import com.shanghai.templateapp.MainActivity;
import com.shanghai.templateapp.injections.modules.ActivityModule;
import com.shanghai.templateapp.injections.scope.ActivityScope;
import com.shanghai.templateapp.ui.activity.ArticleDetailActivity;
import com.shanghai.templateapp.ui.activity.decode.UserInfoActivity;
import com.shanghai.templateapp.ui.activity.login.LoginActivity;
import com.shanghai.templateapp.ui.activity.login.RegisterActivity;


import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(UserInfoActivity userInfoActivity);
    void inject(ArticleDetailActivity detailActivity);
}
