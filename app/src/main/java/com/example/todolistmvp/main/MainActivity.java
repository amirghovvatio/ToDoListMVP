package com.example.todolistmvp.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.todolistmvp.R;
import com.example.todolistmvp.details.DayAdapter;
import com.example.todolistmvp.model.Day;
import com.example.todolistmvp.model.DayApiService;
import com.example.todolistmvp.model.Task;
import com.example.todolistmvp.presenters.DayContract;
import com.example.todolistmvp.presenters.DayPresenter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DayContract.View,DayAdapter.MyEventListener{
    private RecyclerView recyclerView;
    private DayAdapter adapter;
    private DayContract.Presenter presenter=new DayPresenter();
    private DayApiService dayApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExtendedFloatingActionButton fabAddDay=findViewById(R.id.addDayBtn);
        recyclerView=findViewById(R.id.rvDayList);
        dayApiService=new DayApiService();
        fabAddDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddDayActivity.class);
                startActivityIfNeeded(intent,100);
            }
        });


        presenter.onAttach(this);

    }

    @Override
    public void showList(List<Day> days) {
        adapter=new DayAdapter(days,this);

       recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showEmptyState(boolean visible) {
        LinearLayout emptyState=findViewById(R.id.emptyStateMainActivity);
        emptyState.setVisibility(visible ? View.VISIBLE :View.GONE);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode== Activity.RESULT_OK&&data!=null){
            Day day=data.getParcelableExtra("data");
            adapter.addDay(day);
            recyclerView.smoothScrollToPosition(0);
        }
    }



    @Override
    public void onDelete(int dayID,int pos) {
        dayApiService.deleteItem(dayID);
        adapter.deleteDay(pos);


    }

    @Override
    public void onLongClicked(int i) {
        Intent intent=new Intent(MainActivity.this,TaskActivity.class);
        intent.putExtra("dayId",i);
        startActivity(intent);
    }
}