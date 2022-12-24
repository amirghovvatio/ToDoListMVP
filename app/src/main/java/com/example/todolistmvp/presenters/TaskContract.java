package com.example.todolistmvp.presenters;

import com.example.todolistmvp.BasePresenter;
import com.example.todolistmvp.BaseView;
import com.example.todolistmvp.model.Day;

import java.util.List;

public interface TaskContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
