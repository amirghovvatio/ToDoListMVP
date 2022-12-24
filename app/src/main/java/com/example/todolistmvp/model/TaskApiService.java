package com.example.todolistmvp.model;

import com.example.todolistmvp.main.AddTaskActivity;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskApiService {
    Retrofit taskRetrofit=new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://amir.vipmive.com/")
            .build();
    TaskRetrofitService service=taskRetrofit.create(TaskRetrofitService.class);

    public void addTask(int dayId, String title, AddingCallBack callBack){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("dayId",dayId);
        jsonObject.addProperty("title",title);
        service.addingTask(jsonObject).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });

    }
    public interface AddingCallBack{
        void onSuccess(Task task);
    }

    public void getAllTask(int dayId,GettingAllCallBack callBack){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("id",dayId);
        service.gettingAllTask(jsonObject).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }
    public interface GettingAllCallBack{
        void onSuccess(List<Task> taskList);
    }

    public void getTask(int taskId,GetCallBack callBack){
        JsonObject object=new JsonObject();
        object.addProperty("id",taskId);
        service.getingTask(object).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }
    public interface GetCallBack{
        void onSuccess(List<Task> taskList);
    }

    public void deleteTask(int taskId){
        JsonObject object=new JsonObject();
        object.addProperty("id",taskId);
        service.deletingTask(object).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

}
