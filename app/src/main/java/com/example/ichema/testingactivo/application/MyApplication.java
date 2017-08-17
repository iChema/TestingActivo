package com.example.ichema.testingactivo.application;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uriel on 17/08/17.
 */

public class MyApplication extends Application {
    private Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.1.66:3000/";


    @Override
    public void onCreate() {
        super.onCreate();

        //Instancia de gson utilizada por Retrofit para usarse en otra sección de la app.
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setDateFormat("d/M/yyyy").create();

        //Instancia de retrofit, utilizada en la app.
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public Retrofit getRetrofitInstance(){
        return retrofit;
    }

}
