package com.shanghai.templateapp.base.connectors.main;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;
public interface MainConnector {

    interface View extends BaseView{

        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }

    interface Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);

    }
}
