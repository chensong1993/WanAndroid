package com.shanghai.templateapp.presenters.main;

import android.Manifest;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.connectors.main.MainConnector;
import com.shanghai.templateapp.models.DataManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


public class MainPresenter extends RxPresenter<MainConnector.View> implements MainConnector.Presenter {
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void checkVersion(String currentVersion) {

    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {

    }
}
