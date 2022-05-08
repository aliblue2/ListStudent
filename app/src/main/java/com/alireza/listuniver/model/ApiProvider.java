package com.alireza.listuniver.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    private static  ApiService apiService;
    public static ApiService getApiService() {
        if (apiService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://expertdevelopers.ir/api/v1/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            apiService=retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
