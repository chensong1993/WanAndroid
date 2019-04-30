package com.shanghai.templateapp.app;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.hjq.toast.ToastUtils;
import com.shanghai.templateapp.injections.components.AppComponent;
import com.shanghai.templateapp.injections.components.DaggerAppComponent;
import com.shanghai.templateapp.injections.modules.AppModule;
import com.shanghai.templateapp.injections.modules.HttpModule;
import com.shanghai.templateapp.models.db.greendao.CollectDbEntityDao;
import com.shanghai.templateapp.models.db.greendao.DaoMaster;
import com.shanghai.templateapp.models.db.greendao.DaoSession;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.mmkv.MMKV;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chensong
 * @date 2019/2/18 10:41
 */
public class App extends MultiDexApplication {
    private static final String TAG = App.class.getName();
    private static App instance = null;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;
    public static MMKV kv;
    public static RefWatcher refWatcher;
    public CollectDbEntityDao mCollectEntityDao;

    public static App getInstance() {
        if (instance == null) {
            synchronized (App.class) {
                if (instance == null) {
                    instance = new App();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        refWatcher = LeakCanary.install(this);
        MMKV.initialize(this);
        kv = MMKV.defaultMMKV();
        ToastUtils.init(this);
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    /**
     *  * 分割 Dex 支持
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    //是否收藏
    public CollectDbEntityDao getCollectEntityDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(instance, "collectDb");
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = master.newSession();
        mCollectEntityDao = session.getCollectDbEntityDao();
        return mCollectEntityDao;
    }
}
