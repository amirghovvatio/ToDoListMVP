package com.example.todolistmvp.presenters;

import com.example.todolistmvp.model.Day;
import com.example.todolistmvp.model.DayApiService;

import java.util.List;

public class DayPresenter implements DayContract.Presenter{
    private DayApiService apiService;
    private DayContract.View view;

    @Override
    public void onAttach(DayContract.View view) {
        this.view=view;
        apiService=new DayApiService();
        apiService.getAllDay(days -> {
            if (!days.isEmpty()){
                view.showList(days);
                view.showEmptyState(false);
            }
            else {view.showEmptyState(true);}

        });



    }



    @Override
    public void onDetach() {
        this.view=null;
    }




}

