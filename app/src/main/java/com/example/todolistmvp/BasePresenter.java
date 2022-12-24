package com.example.todolistmvp;

public interface BasePresenter <T extends BaseView>{
    void onAttach(T view);
    void onDetach();
}
