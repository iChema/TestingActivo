package com.example.ichema.testingactivo.api;

import com.example.ichema.testingactivo.Caso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by uriel on 17/08/17.
 */

public interface TestAPI {

    @GET("test/get")
    Call<ArrayList<Caso>> get();

    @POST("test")
    Call<ArrayList<Caso>> test();

    @GET("test/all")
    Call<ArrayList<Caso>> testAll();

    @GET("test/getpruebas")
    Call<ArrayList<Caso>> getPruebas();

}
