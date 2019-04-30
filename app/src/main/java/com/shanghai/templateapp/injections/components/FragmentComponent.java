package com.shanghai.templateapp.injections.components;

import android.app.Activity;

import com.shanghai.templateapp.injections.modules.FragmentModule;
import com.shanghai.templateapp.injections.scope.FragmentScope;
import com.shanghai.templateapp.ui.fragment.HomeFragment;
import com.shanghai.templateapp.ui.fragment.MeFragment;
import com.shanghai.templateapp.ui.fragment.ProjectFragment;
import com.shanghai.templateapp.ui.fragment.StarFragment;
import com.shanghai.templateapp.ui.fragment.home.ArticleFragment;
import com.shanghai.templateapp.ui.fragment.home.FirendFragment;


import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(HomeFragment homeFragment);

    void inject(ArticleFragment articleFragment);

    void inject(StarFragment starFragment);

    void inject(FirendFragment firendFragment);

    void inject(MeFragment meFragment);

    void inject(ProjectFragment projectFragment);
}
