package com.shanghai.templateapp.models.services;

import com.shanghai.templateapp.models.entity.ApiResponse;
import com.shanghai.templateapp.models.entity.UserInfoList;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author chensong
 * @date 2019/4/22 15:11
 */
public interface DecodeService {

    @GET("index.php")
    Flowable<ApiResponse<UserInfoList>> userInfo(@Query("m") String m, @Query("c") String c, @Query("a") String a);

    @POST("index.php?m=zhongyin&c=zhongyin&a=get_params")
    @FormUrlEncoded
    Flowable<String> upUserInfo(@Field("p") String s);
}
