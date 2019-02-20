package com.shanghai.templateapp.models.http;

import com.shanghai.templateapp.models.entity.ApiResponse;


import java.util.List;

import io.reactivex.Flowable;
public interface HttpHelper {

    Flowable<ApiResponse<List<String>>> getBanners();

}
