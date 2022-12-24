package com.example.todolistmvp.model;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface TaskRetrofitService {

    @POST("api/tasks/add")
    Call<Task> addingTask(@Body JsonObject jsonObject);

    @POST("api/tasks/getAll")
    Call<List<Task>> gettingAllTask(@Body JsonObject object);

    @POST("api/tasks/get")
    Call<List<Task>> getingTask(@Body JsonObject object);

    @HTTP(method = "PUT",path = "api/tasks/setDone",hasBody = true)
    Call<Boolean> doDoneTask(@Body JsonObject object);

    @HTTP(method = "DELETE",path = "api/tasks/delete",hasBody = true)
    Call<Boolean> deletingTask(@Body JsonObject object);



}
