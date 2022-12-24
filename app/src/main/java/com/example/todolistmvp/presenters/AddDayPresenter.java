package com.example.todolistmvp.presenters;

import com.example.todolistmvp.model.Day;
import com.example.todolistmvp.model.DayApiService;

public class AddDayPresenter implements AddDayContract.Presenter{
    private DayApiService apiService;
    private AddDayContract.View view;

    @Override
    public void onAttach(AddDayContract.View view) {
        this.view=view;
        apiService=new DayApiService();
    }

    @Override
    public void onDetach() {
        this.view=null;
    }

    @Override
    public void posting(String day, String date) {
        apiService=new DayApiService();
        apiService.postDay(day, date, new DayApiService.PostDayCallBack() {
            @Override
            public void onSuccess(Day day) {
                view.postDay(day);
            }
        });

    }
}

