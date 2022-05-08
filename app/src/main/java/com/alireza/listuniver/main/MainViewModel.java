package com.alireza.listuniver.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alireza.listuniver.model.RepositoryStudent;
import com.alireza.listuniver.model.Student;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private RepositoryStudent repositoryStudent;
    private Disposable disposable;
    private MutableLiveData<String> Error=new MutableLiveData<>();
    private MutableLiveData<Boolean> progressBar=new MutableLiveData<>();
    public MainViewModel(RepositoryStudent repositoryStudent) {
        this.repositoryStudent = repositoryStudent;
        progressBar.setValue(true);
        repositoryStudent.refreshList().subscribeOn(Schedulers.io()).doFinally(()-> progressBar.postValue(false)).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable=d;
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Error.postValue("خطای نامشخص");
            }
        });
    }

    public LiveData<List<Student>> getStudentList() {
        return repositoryStudent.studentList();
    }

    public LiveData<String> getError() {
        return Error;
    }

    public LiveData<Boolean> getProgressBar() {
        return progressBar;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
