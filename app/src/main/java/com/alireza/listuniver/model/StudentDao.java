package com.alireza.listuniver.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {


    @Query("SELECT * FROM tblMain ORDER BY id DESC")
    LiveData<List<Student>> getStudentList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStudent(List<Student> students);

    @Insert()
    void saveDaoStudent(Student student);
}
