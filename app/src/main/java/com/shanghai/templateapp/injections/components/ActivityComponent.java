package com.shanghai.templateapp.injections.components;

import android.app.Activity;

import com.shanghai.templateapp.MainActivity;
import com.shanghai.templateapp.injections.modules.ActivityModule;
import com.shanghai.templateapp.injections.scope.ActivityScope;


import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

   // Activity getActivity();
   // void inject(MainActivity mainActivity);
}
