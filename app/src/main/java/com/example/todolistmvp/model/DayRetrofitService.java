package com.example.todolistmvp.model;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DayRetrofitService {
    @POST("api/day/add")
    Call<Day> addingDay(@Body JsonObject object);

    @HTTP(method = "DELETE",path = "api/day/delete",hasBody = true )
    Call<Boolean> delItem(@Body JsonObject object);

    @POST("api/day/get")
    Call<List<Day>> getDay(@Body JsonObject object);


    @POST("api/day/getAll")
    Call<List<Day>> getAllDay();
    //==============================================================================================


}
