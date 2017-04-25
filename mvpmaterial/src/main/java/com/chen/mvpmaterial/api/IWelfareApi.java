package com.chen.mvpmaterial.api;


import com.chen.mvpmaterial.bean.WelfarePhotoList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

import static com.chen.mvpmaterial.api.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by long on 2016/10/10.
 */

public interface IWelfareApi {

    /**
     * 获取福利图片
     * eg: http://gank.io/api/data/福利/10/1
     *
     * @param page 页码
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("/api/data/福利/10/{page}")
    Observable<WelfarePhotoList> getWelfarePhoto(@Path("page") int page);


}
