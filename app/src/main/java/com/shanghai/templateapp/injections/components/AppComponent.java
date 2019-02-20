package com.shanghai.templateapp.injections.components;

import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.injections.modules.AppModule;
import com.shanghai.templateapp.injections.modules.HttpModule;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();

    DataManager getDataManager();

    RetrofitHelper retrofitHelper();



}
