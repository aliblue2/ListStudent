package com.alireza.listuniver.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.listuniver.R;
import com.alireza.listuniver.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bindStudent(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView tvFirstChar;
        TextView tvFullName;
        TextView tvCourse;
        TextView tvPoint;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstChar=itemView.findViewById(R.id.tvFirstChar);
            tvFullName=itemView.findViewById(R.id.tvFullName);
            tvCourse=itemView.findViewById(R.id.tvCourse);
            tvPoint=itemView.findViewById(R.id.tvPoint);

        }

        public void bindStudent(Student student){
            tvFullName.setText(student.getFirst_name()+" "+student.getLast_name());
            tvPoint.setText(String.valueOf(student.getScore()));
            tvCourse.setText(student.getCourse());
            tvFirstChar.setText(student.getLast_name().substring(0,1));
        }
    }
}
