package com.shanghai.templateapp.models.http;

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


import org.json.JSONArray;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.Path;

public interface HttpHelper {


    Flowable<ApiResponse<WanArticleListEntity>> getAtricle(int page);

    Flowable<ApiResponse<List<BannerEntity>>> getBanner();

    Flowable<ApiResponse<List<FriendEntity>>> getFriend();

    Flowable<ApiResponse<List<WanArticleEntity>>> getTopNews();

    Flowable<ApiResponse<LoginEntity>> getLogin(String name, String password);

    Flowable<ApiResponse<WanArticleListEntity>> getCollect(int page);

    Flowable<ApiResponse<LoginEntity>> getRegister(String name, String password, String repassword);

    Flowable<ApiResponse<UserInfoList>> getUserInfo(String name, String password, String repassword);

    Flowable<String> upUserInfo(String s);

    Flowable<ApiResponse> getStarInArticle(int id);

    Flowable<ApiResponse> getUncollect(int id);

    Flowable<ApiResponse<List<ProjectEntity>>> getProjectList();
}
