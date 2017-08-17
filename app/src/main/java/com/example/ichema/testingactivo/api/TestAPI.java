package com.example.ichema.testingactivo.api;

import com.example.ichema.testingactivo.Caso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by uriel on 17/08/17.
 */

public interface TestAPI {

    @GET("test/get")
    Call<ArrayList<Caso>> get();

}
