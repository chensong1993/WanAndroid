package com.shanghai.templateapp.models.http;

import com.shanghai.templateapp.models.entity.ApiResponse;
import com.shanghai.templateapp.models.services.NewsService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitHelper implements HttpHelper {


    private NewsService mNewsApiService;

    @Inject
    public RetrofitHelper( NewsService mNewsApiService) {

        this.mNewsApiService = mNewsApiService;

    }
    @Override
    public Flowable<ApiResponse<List<String>>> getBanners() {
        return mNewsApiService.banners();
    }



}
