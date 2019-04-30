package com.shanghai.templateapp.models;

import android.text.TextUtils;
import android.util.Log;

import com.shanghai.templateapp.models.entity.ApiResponse;
import com.shanghai.templateapp.models.entity.BannerEntity;
import com.shanghai.templateapp.models.entity.CollectEntity;
import com.shanghai.templateapp.models.entity.FriendEntity;
import com.shanghai.templateapp.models.entity.LoginEntity;
import com.shanghai.templateapp.models.entity.ProjectEntity;
import com.shanghai.templateapp.models.entity.TopNewsEntity;
import com.shanghai.templateapp.models.entity.UserInfoEntity;
import com.shanghai.templateapp.models.entity.UserInfoList;
import com.shanghai.templateapp.models.entity.WanArticleEntity;
import com.shanghai.templateapp.models.entity.WanArticleListEntity;
import com.shanghai.templateapp.models.http.HttpHelper;


import org.json.JSONArray;

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
    public Flowable<ApiResponse<WanArticleListEntity>> getAtricle(int page) {
        return mHttpHelper.getAtricle(page);
    }

    @Override
    public Flowable<ApiResponse<List<BannerEntity>>> getBanner() {
        return mHttpHelper.getBanner();
    }

    @Override
    public Flowable<ApiResponse<List<FriendEntity>>> getFriend() {
        return mHttpHelper.getFriend();
    }

    @Override
    public Flowable<ApiResponse<List<WanArticleEntity>>> getTopNews() {
        return mHttpHelper.getTopNews();
    }

    @Override
    public Flowable<ApiResponse<LoginEntity>> getLogin(String name, String password) {
        return mHttpHelper.getLogin(name, password);
    }

    @Override
    public Flowable<ApiResponse<WanArticleListEntity>> getCollect(int page) {
        return mHttpHelper.getCollect(page);
    }

    @Override
    public Flowable<ApiResponse<LoginEntity>> getRegister(String name, String password, String repassword) {
        return mHttpHelper.getRegister(name, password, repassword);
    }

    @Override
    public Flowable<ApiResponse<UserInfoList>> getUserInfo(String name, String password, String repassword) {
        return mHttpHelper.getUserInfo(name,password,repassword);
    }

    @Override
    public Flowable<String> upUserInfo(String s) {
        return mHttpHelper.upUserInfo(s);
    }

    @Override
    public Flowable<ApiResponse> getStarInArticle(int id) {
        return mHttpHelper.getStarInArticle(id);
    }

    @Override
    public Flowable<ApiResponse> getUncollect(int id) {
        return mHttpHelper.getUncollect(id);
    }

    @Override
    public Flowable<ApiResponse<List<ProjectEntity>>> getProjectList() {
        return mHttpHelper.getProjectList();
    }


}
