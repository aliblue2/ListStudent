package com.alireza.listuniver.addStudent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alireza.listuniver.model.RepositoryStudent;

import io.reactivex.Completable;

public class ViewModelAddStudent extends ViewModel {
    private RepositoryStudent repositoryStudent;
    private MutableLiveData<Boolean> progressAdd=new MutableLiveData<>();
    public ViewModelAddStudent(RepositoryStudent repositoryStudent) {
        this.repositoryStudent = repositoryStudent;
        progressAdd.setValue(false);
    }
    public Completable save(String firstName,String lastName,String course,String score){
        return repositoryStudent.save(firstName,lastName,course,score).doFinally( ()-> progressAdd.postValue(true)).ignoreElement();
    }

    public LiveData<Boolean> getProgressAdd() {
        return progressAdd;
    }
}
