package com.shanghai.templateapp.models;

import android.text.TextUtils;
import android.util.Log;

import com.shanghai.templateapp.models.entity.ApiResponse;
import com.shanghai.templateapp.models.http.HttpHelper;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class DataManager implements HttpHelper {

    HttpHelper mHttpHelper;


    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;

    }


    @Override
    public Flowable<ApiResponse<List<String>>> getBanners() {
        return mHttpHelper.getBanners();
    }
}
