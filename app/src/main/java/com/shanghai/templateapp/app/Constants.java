package com.shanghai.templateapp.app;

import android.os.Environment;

import java.io.File;

/**
 * @author chensong
 * @date 2019/4/15 10:45
 */
public class Constants {
    //================= PATH ====================
    //okhttp缓存路径
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=360";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "chinaipo" + File.separator + "StockNews";

    //================= URL====================
    public static final String MAIN_URL = "https://www.wanandroid.com/";

    public static final String USER_NAME = "username";

    public static final String USER_PASSWORD = "user_password";

    public static final String ARTICLE_STAR= "article_star";

    public static final String ARTICLE_STAR_ID = "article_star_id";

    public static final String ARTICLE_STAR_TITLE = "aritcle_star_title";

    public static final String ARTICLE_STAR_AUTHOR = "article_star_author";

    public static final String ARTICLE_STAR_LINK = "article_star_link";

    public static final String ARTICLE_STAR_COLLECT = "article_star_collect";
}
