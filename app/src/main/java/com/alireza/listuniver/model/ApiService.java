package com.alireza.listuniver.model;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("experts/student")
    Single<List<Student>> getStudent();

    @POST("experts/student")
    Single<Student> addStudent(@Body JsonObject object);
}
