package com.example.todolistmvp.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.TextView;

import com.example.todolistmvp.R;
import com.example.todolistmvp.details.TaskAdapter;
import com.example.todolistmvp.model.Task;
import com.example.todolistmvp.model.TaskApiService;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

public class TaskActivity extends AppCompatActivity implements TaskAdapter.TaskEventListener{
    private TaskAdapter adapter;
    private RecyclerView recyclerView;
    private TaskApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        ExtendedFloatingActionButton fab=findViewById(R.id.addTaskBtn);

        recyclerView=findViewById(R.id.rvTaskActivity);

        apiService=new TaskApiService();
        int f=getIntent().getIntExtra("dayId",0);
        apiService.getAllTask(f, new TaskApiService.GettingAllCallBack() {
            @Override
            public void onSuccess(List<Task> taskList) {
                adapter=new TaskAdapter(taskList,TaskActivity.this);
                Log.i("TAG", "onSuccess: "+taskList);
                recyclerView.setLayoutManager(new LinearLayoutManager(TaskActivity.this,RecyclerView.VERTICAL,false));
                recyclerView.setAdapter(adapter);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TaskActivity.this,AddTaskActivity.class);
                intent.putExtra("myDayId",f);
                startActivityIfNeeded(intent,777);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==777&&resultCode==Activity.RESULT_OK&&data!=null){
            Task task=data.getParcelableExtra("addTask");
            adapter.addTask(task);
            recyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void onDelete(int taskId,int pos) {
        apiService.deleteTask(taskId);
        adapter.deleteTask(pos);
    }
}