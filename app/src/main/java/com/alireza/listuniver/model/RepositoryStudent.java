package com.alireza.listuniver.model;

import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RepositoryStudent {
    private ApiService apiService;
    private StudentDao studentDao;

    public RepositoryStudent(ApiService apiService, StudentDao studentDao) {
        this.apiService = apiService;
        this.studentDao = studentDao;
    }

    public Completable refreshList(){
        return apiService.getStudent().doOnSuccess(students -> {studentDao.addStudent(students);}).ignoreElement();
    }
    public LiveData<List<Student>> studentList(){
        return studentDao.getStudentList();
    }

    public Single<Student> save(String firstName,String lastName,String course,String score){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("first_name",firstName);
        jsonObject.addProperty("last_name",lastName);
        jsonObject.addProperty("course",course);
        jsonObject.addProperty("score",Integer.parseInt(score));
        return apiService.addStudent(jsonObject).doOnSuccess(student -> studentDao.saveDaoStudent(student));
    }
    }

