package com.example.todolistmvp.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistmvp.R;
import com.example.todolistmvp.model.Day;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolding> {
    private List<Day> days;
    private MyEventListener eventListener;
    public DayAdapter(List<Day> days,MyEventListener eventListener){
        this.days=days;
        this.eventListener=eventListener;
    }
    @NonNull
    @Override
    public DayViewHolding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.day_list,parent,false);
        return new DayViewHolding(view);
    }
    public void addDay(Day day){
        days.add(0,day);
        notifyItemInserted(0);
    }
    public void deleteDay(int i){
        days.remove(i);
        notifyItemRemoved(i);

    }
    @Override
    public void onBindViewHolder(@NonNull DayViewHolding holder, int position) {
        holder.bindDay(days.get(position));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class DayViewHolding extends RecyclerView.ViewHolder {
        private CheckBox checkBoxDay;
        private TextView tvDate;
        private ImageView imgDeleteDay;
        public DayViewHolding(@NonNull View itemView) {
            super(itemView);
            checkBoxDay=itemView.findViewById(R.id.chekcBoxDayList);
            tvDate=itemView.findViewById(R.id.tvDateDayList);
            imgDeleteDay=itemView.findViewById(R.id.deleteDayList);
        }
        public void bindDay(Day day){
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    eventListener.onLongClicked(day.getDayId());
                    return false;
                }
            });
            checkBoxDay.setText(day.getDayName());
            tvDate.setText(day.getDate());
            imgDeleteDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListener.onDelete(day.getDayId(),getAdapterPosition());
                }
            });

        }
    }
   public interface MyEventListener{
        void onDelete(int dayID,int adapterPos);
        void onLongClicked(int i);
    }
}
