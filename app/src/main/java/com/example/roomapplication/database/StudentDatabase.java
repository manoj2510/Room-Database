package com.example.roomapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase mStudentDatabase = null;

    public abstract StudentDao studentModel();

    public static StudentDatabase getStudentDatabase(Context context)
    {
        if(mStudentDatabase == null)
        {
            mStudentDatabase = Room.databaseBuilder(context,StudentDatabase.class,"student-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return mStudentDatabase;
    }

    public static void destroyInstance()
    {
        mStudentDatabase = null;
    }
}
