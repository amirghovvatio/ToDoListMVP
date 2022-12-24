package com.example.todolistmvp.presenters;

import com.example.todolistmvp.BasePresenter;
import com.example.todolistmvp.BaseView;
import com.example.todolistmvp.model.Day;

public interface AddDayContract {
    interface View extends BaseView{
        void postDay(Day day);
    }
    interface Presenter extends BasePresenter<View> {
        void posting(String day,String date);
    }
}
