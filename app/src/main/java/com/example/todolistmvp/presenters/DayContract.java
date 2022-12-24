package com.example.todolistmvp.presenters;

import android.accessibilityservice.GestureDescription;

import com.example.todolistmvp.BasePresenter;
import com.example.todolistmvp.BaseView;
import com.example.todolistmvp.model.Day;

import java.util.List;

public interface DayContract {
    interface View extends BaseView{
        void showList(List<Day> days);
        void showEmptyState(boolean visible);

    }

    interface Presenter extends BasePresenter<View>{

    }

}
