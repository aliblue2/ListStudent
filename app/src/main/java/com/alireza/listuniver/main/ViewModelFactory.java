package com.alireza.listuniver.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.alireza.listuniver.addStudent.ViewModelAddStudent;
import com.alireza.listuniver.model.RepositoryStudent;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private RepositoryStudent repositoryStudent;

    public ViewModelFactory(RepositoryStudent repositoryStudent) {
        this.repositoryStudent = repositoryStudent;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(repositoryStudent);
        }else if (modelClass.isAssignableFrom(ViewModelAddStudent.class)){
            return (T) new ViewModelAddStudent(repositoryStudent);
        }else
            throw new IllegalArgumentException("ViewModel is not valid");
    }
}
