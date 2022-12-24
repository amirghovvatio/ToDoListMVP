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
import com.example.todolistmvp.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolding> {
    List<Task> taskList;
    private TaskEventListener eventListener;
    public TaskAdapter(List<Task> taskList,TaskEventListener eventListener){
        this.taskList=taskList;
        this.eventListener=eventListener;
    }
    @NonNull
    @Override
    public TaskViewHolding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list,parent,false);
        return new TaskViewHolding(view);
    }
    public void deleteTask(int taskId){
        this.taskList.remove(taskId);
        notifyItemRemoved(taskId);
    }
    public void addTask(Task task){
        this.taskList.add(0,task);
        notifyItemInserted(0);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolding holder, int position) {
holder.bindTask(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolding extends RecyclerView.ViewHolder {
        private CheckBox chkTask;
        private ImageView deleteTask;
        public TaskViewHolding(@NonNull View itemView) {
            super(itemView);
            chkTask=itemView.findViewById(R.id.checkBoxTaskList);
            deleteTask=itemView.findViewById(R.id.deleteTaskList);
        }
        public void bindTask(Task task){
            chkTask.setText(task.getTitle());
            deleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListener.onDelete(task.getTaskID(),getAdapterPosition());
                }
            });

        }
    }
    public interface TaskEventListener{
        void onDelete(int taskId,int adapterPos);
    }

}
