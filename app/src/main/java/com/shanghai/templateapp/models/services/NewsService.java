package com.shanghai.templateapp.models.services;

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
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {


    @GET("banner/json")
    Flowable<ApiResponse<List<BannerEntity>>> banners();

    @GET("article/list/{page}/json")
    Flowable<ApiResponse<WanArticleListEntity>> article(@Path("page") int page);

    @GET("friend/json")
    Flowable<ApiResponse<List<FriendEntity>>> friend();

    @GET("article/top/json")
    Flowable<ApiResponse<List<WanArticleEntity>>> topNews();

    @POST("user/login")
    @FormUrlEncoded
    Flowable<ApiResponse<LoginEntity>> login(@Field("username") String name, @Field("password") String password);

    @POST("user/register")
    @FormUrlEncoded
    Flowable<ApiResponse<LoginEntity>> register(@Field("username") String name, @Field("password") String password, @Field("repassword") String repassword);

    @GET("lg/collect/list/{page}/json")
    Flowable<ApiResponse<WanArticleListEntity>> collect(@Path("page") int page);

    @POST("lg/collect/{id}/json")
    Flowable<ApiResponse> starInArticle(@Path("id") int id);

    @POST("lg/uncollect_originId/{id}/json")
    Flowable<ApiResponse> uncollect(@Path("id") int id);

    @GET("navi/json")
    Flowable<ApiResponse<List<ProjectEntity>>> projectList();
}
