package com.alireza.listuniver.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.listuniver.addStudent.AddNewStudent;
import com.alireza.listuniver.model.ApiProvider;
import com.alireza.listuniver.model.AppDataBase;
import com.alireza.listuniver.R;
import com.alireza.listuniver.model.RepositoryStudent;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View btnAddStudent=findViewById(R.id.btnAddStudentMain);
        btnAddStudent.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, AddNewStudent.class);
            startActivity(intent);
        });
        MainViewModel mainViewModel=new ViewModelProvider(this,new ViewModelFactory(new RepositoryStudent(ApiProvider.getApiService(), AppDataBase.getInstance(getApplicationContext()).studentDao())))
                .get(MainViewModel.class);
        mainViewModel.getStudentList().observe(this,students -> {
            RecyclerView recyclerView=findViewById(R.id.rvMain);
            recyclerView.setAdapter(new StudentAdapter(students));
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        });
        mainViewModel.getProgressBar().observe(this,visible->{
            findViewById(R.id.progressBarMain).setVisibility(visible?View.VISIBLE:View.GONE);
        });
    }
}