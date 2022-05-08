package com.alireza.listuniver.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Student.class},exportSchema = false,version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context) {
        if (appDataBase==null)
            appDataBase= Room.databaseBuilder(context,AppDataBase.class,"db_app").build();
        return appDataBase;
    }

    public abstract StudentDao studentDao();
}
