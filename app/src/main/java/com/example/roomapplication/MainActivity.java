package com.example.roomapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomapplication.database.Student;
import com.example.roomapplication.database.StudentDatabase;
import com.example.roomapplication.recycler.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mFirstNameEditText, mLastNameEditText, mPhoneEditText;
    private Button mAddButton;
    private RecyclerView mRecyclerView;
    private StudentDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.student_recycler);
        mFirstNameEditText = findViewById(R.id.et_first_name);
        mLastNameEditText = findViewById(R.id.et_last_name);
        mPhoneEditText = findViewById(R.id.et_phone);
        mAddButton = findViewById(R.id.bt_add);
        mDb =StudentDatabase.getStudentDatabase(getApplicationContext());
        setList();
        setValues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setList();
    }

    private void setMyAdapter(List<Student> studentList) {
        StudentAdapter mStudentAdapter = new StudentAdapter(studentList,this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mStudentAdapter);
    }

    private void setValues() {
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = mFirstNameEditText.getText().toString().trim();
                String lastName = mLastNameEditText.getText().toString().trim();
                String phone = mPhoneEditText.getText().toString();
                long phoneNumber = 0;
                if(!phone.isEmpty()) {
                    phoneNumber= Long.parseLong(phone);
                }

                if (firstName.isEmpty())
                    firstName = "Test Name";
                if (lastName.isEmpty())
                    lastName = "Test Last Name";
                if(phone.isEmpty())
                    phoneNumber = 12345;

                Student student = new Student(firstName,lastName,phoneNumber);
                mDb.studentModel().insertStudent(student);
                Toast.makeText(MainActivity.this, "Value inserted", Toast.LENGTH_SHORT).show();

                mFirstNameEditText.setText("");
                mLastNameEditText.setText("");
                mPhoneEditText.setText("");
                mFirstNameEditText.requestFocus();
                setList();
            }
        });
    }

    private void setList()
    {
        List<Student> studentList = mDb.studentModel().loadAllStudents();
        setMyAdapter(studentList);
    }

    @Override
    protected void onDestroy() {
        StudentDatabase.destroyInstance();
        super.onDestroy();
    }
}
