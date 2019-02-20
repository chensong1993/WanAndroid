package com.shanghai.templateapp.models.services;

import com.shanghai.templateapp.models.entity.ApiResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
public interface NewsService {


    @GET("banners/")
    Flowable<ApiResponse<List<String>>> banners();
}
