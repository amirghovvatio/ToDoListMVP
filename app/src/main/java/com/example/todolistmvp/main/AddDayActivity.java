package com.example.todolistmvp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todolistmvp.R;
import com.example.todolistmvp.details.DayAdapter;
import com.example.todolistmvp.presenters.AddDayContract;
import com.example.todolistmvp.model.Day;
import com.example.todolistmvp.presenters.AddDayPresenter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddDayActivity extends AppCompatActivity implements AddDayContract.View {
    TextInputEditText edtDay;
    TextInputEditText edtDate;
    ExtendedFloatingActionButton fab;

    private AddDayContract.Presenter presenter=new AddDayPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        edtDay=findViewById(R.id.edtDayAddDay);
        edtDate=findViewById(R.id.edtDateAddDay);
        fab=findViewById(R.id.saveDayAddDayActivity);
        //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.posting(edtDay.getText().toString(),edtDate.getText().toString());
            }
        });
        //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        presenter.onAttach(this);
        //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    }

        //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    @Override
    public void postDay(Day day) {
        Intent intent=new Intent();
        intent.putExtra("data",day);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
        //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
}