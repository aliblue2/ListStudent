package com.alireza.listuniver.addStudent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alireza.listuniver.R;
import com.alireza.listuniver.main.MainViewModel;
import com.alireza.listuniver.main.ViewModelFactory;
import com.alireza.listuniver.model.ApiProvider;
import com.alireza.listuniver.model.AppDataBase;
import com.alireza.listuniver.model.RepositoryStudent;
import com.alireza.listuniver.model.Student;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddNewStudent extends AppCompatActivity {
    private Disposable disposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_layout);
        ViewModelAddStudent viewModelAddStudent=new ViewModelProvider(this,new ViewModelFactory(new RepositoryStudent(ApiProvider.getApiService()
                ,AppDataBase.getInstance(getApplicationContext()).studentDao())))
                .get(ViewModelAddStudent.class);
        TextInputEditText etFirstName=findViewById(R.id.etFirstName);
        TextInputEditText etLastName=findViewById(R.id.etLastName);
        TextInputEditText etCourse=findViewById(R.id.etCourse);
        TextInputEditText etScore=findViewById(R.id.etScore);
        Button saveBtnAdd=findViewById(R.id.saveBtnAdd);
        saveBtnAdd.setOnClickListener(view -> {
            if (etFirstName.length()>0&&
                    etLastName.length()>0&&
                    etCourse.length()>0&&etScore.length()>0)
                viewModelAddStudent.save(etFirstName.getText().toString(),etLastName.getText().toString(),etCourse.getText().toString(),etScore.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onComplete() {
                        finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getApplicationContext(),"خطای ارتباط با سرور",Toast.LENGTH_SHORT).show();
                    }
                });
        });
        viewModelAddStudent.getProgressAdd().observe(this,visible->{
            findViewById(R.id.progress_circular_add).setVisibility(visible?View.VISIBLE:View.GONE);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null)
            disposable.dispose();
    }
}
