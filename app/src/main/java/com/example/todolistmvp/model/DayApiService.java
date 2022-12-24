package com.example.todolistmvp.model;

import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DayApiService {
    Retrofit dayRetrofit=new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://amir.vipmive.com/")
            .build();
    DayRetrofitService service=dayRetrofit.create(DayRetrofitService.class);
    public void getAllDay(GetAllCallBack callBack){
        service.getAllDay().enqueue(new Callback<List<Day>>() {
            @Override
            public void onResponse(Call<List<Day>> call, Response<List<Day>> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Day>> call, Throwable t) {

            }
        });
    }
    public interface GetAllCallBack {
        void onSuccess(List<Day> days);

    }
    public void postDay(String day,String date,PostDayCallBack callBack){
        JsonObject object=new JsonObject();
        object.addProperty("dayName",day);
        object.addProperty("date",date);
        service.addingDay(object).enqueue(new Callback<Day>() {
            @Override
            public void onResponse(Call<Day> call, Response<Day> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Day> call, Throwable t) {

            }
        });
    }
    public interface PostDayCallBack{
        void onSuccess(Day day);
    }

    public void deleteItem(int i){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("id",i);
      service.delItem(jsonObject).enqueue(new Callback<Boolean>() {
          @Override
          public void onResponse(Call<Boolean> call, Response<Boolean> response) {

          }

          @Override
          public void onFailure(Call<Boolean> call, Throwable t) {

          }
      });

    }
   public void getDay(GetCallingBack callBack){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("id",0);
        service.getDay(jsonObject).enqueue(new Callback<List<Day>>() {
            @Override
            public void onResponse(Call<List<Day>> call, Response<List<Day>> response) {
                callBack.getSingleDay(response.body());
            }

            @Override
            public void onFailure(Call<List<Day>> call, Throwable t) {

            }
        });
   }
   public interface GetCallingBack{
        void getSingleDay(List<Day> days);
   }

}
