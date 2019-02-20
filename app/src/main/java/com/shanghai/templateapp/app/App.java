package com.shanghai.templateapp.app;

import android.app.Activity;
import android.app.Application;

import com.shanghai.templateapp.injections.components.AppComponent;
import com.shanghai.templateapp.injections.components.DaggerAppComponent;
import com.shanghai.templateapp.injections.modules.AppModule;
import com.shanghai.templateapp.injections.modules.HttpModule;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chensong
 * @date 2019/2/18 10:41
 */
public class App extends Application {
    private static final String TAG = App.class.getName();
    private static App instance = null;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;

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
}
