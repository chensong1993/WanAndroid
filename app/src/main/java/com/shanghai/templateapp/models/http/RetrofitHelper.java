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
import com.shanghai.templateapp.models.services.DecodeService;
import com.shanghai.templateapp.models.services.NewsService;

import org.json.JSONArray;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitHelper implements HttpHelper {


    private NewsService mNewsApiService;
    private DecodeService mDecodeService;

    @Inject
    public RetrofitHelper(NewsService mNewsApiService, DecodeService mDecodeService) {
        this.mNewsApiService = mNewsApiService;
        this.mDecodeService = mDecodeService;
    }

    @Override
    public Flowable<ApiResponse<WanArticleListEntity>> getAtricle(int page) {
        return mNewsApiService.article(page);
    }

    @Override
    public Flowable<ApiResponse<List<BannerEntity>>> getBanner() {
        return mNewsApiService.banners();
    }

    @Override
    public Flowable<ApiResponse<List<FriendEntity>>> getFriend() {
        return mNewsApiService.friend();
    }

    @Override
    public Flowable<ApiResponse<List<WanArticleEntity>>> getTopNews() {
        return mNewsApiService.topNews();
    }

    @Override
    public Flowable<ApiResponse<LoginEntity>> getLogin(String name, String password) {
        return mNewsApiService.login(name,password);
    }

    @Override
    public Flowable<ApiResponse<WanArticleListEntity>> getCollect(int page) {
        return mNewsApiService.collect(page);
    }

    @Override
    public Flowable<ApiResponse<LoginEntity>> getRegister(String name, String password, String repassword) {
        return mNewsApiService.register(name,password,repassword);
    }

    @Override
    public Flowable<ApiResponse<UserInfoList>> getUserInfo(String name, String password, String repassword) {
        return mDecodeService.userInfo(name,password,repassword);
    }

    @Override
    public Flowable<String> upUserInfo(String s) {
        return mDecodeService.upUserInfo(s);
    }

    @Override
    public Flowable<ApiResponse> getStarInArticle(int id) {
        return mNewsApiService.starInArticle(id);
    }

    @Override
    public Flowable<ApiResponse> getUncollect(int id) {
        return mNewsApiService.uncollect(id);
    }

    @Override
    public Flowable<ApiResponse<List<ProjectEntity>>> getProjectList() {
        return mNewsApiService.projectList();
    }


}
