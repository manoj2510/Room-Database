package com.example.roomapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface StudentDao {

    @Query("select * from Student")
    List<Student> loadAllStudents();

    @Insert(onConflict = IGNORE)
    void insertStudent(Student student);

    @Delete
    void delete(Student student);

    @Query("update Student set lastName = :lastName, phone = :phone WHERE firstName = :firstName")
    void updateStudent(String firstName, String lastName, long phone);
}
