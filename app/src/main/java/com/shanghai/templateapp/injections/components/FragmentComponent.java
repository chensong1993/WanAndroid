package com.shanghai.templateapp.injections.components;

import android.app.Activity;

import com.shanghai.templateapp.injections.modules.FragmentModule;
import com.shanghai.templateapp.injections.scope.FragmentScope;
import com.shanghai.templateapp.ui.fragment.HomeFragment;


import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

  //  Activity getActivity();

//    void inject(HomeFragment homeFragment);



}
