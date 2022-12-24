package com.example.todolistmvp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todolistmvp.R;
import com.example.todolistmvp.details.TaskAdapter;
import com.example.todolistmvp.model.Task;
import com.example.todolistmvp.model.TaskApiService;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {
    private TaskApiService apiService;
    private TextInputEditText edtTitle;
    private ExtendedFloatingActionButton fabSave;
    private int dayId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
      edtTitle = findViewById(R.id.edtTaskAddTask);
      fabSave = findViewById(R.id.saveTaskAddTaskActivity);
      apiService=new TaskApiService();
        dayId=getIntent().getIntExtra("myDayId",0);
      fabSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              apiService.addTask(dayId, edtTitle.getText().toString(), new TaskApiService.AddingCallBack() {
                  @Override
                  public void onSuccess(Task task) {
                      Intent intent=new Intent();
                      intent.putExtra("addTask",task);
                      setResult(Activity.RESULT_OK,intent);
                      finish();
                  }
              });
          }
      });


    }
}