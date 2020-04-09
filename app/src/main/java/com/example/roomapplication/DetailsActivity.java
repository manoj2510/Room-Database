package com.example.roomapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomapplication.database.Student;
import com.example.roomapplication.database.StudentDatabase;

public class DetailsActivity extends AppCompatActivity {

    private TextView mFirstNameText;
    private EditText mLastNameEditText, mPhoneEditText;
    private Button mUpdateButton, mDeleteButton;
    private StudentDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        setValues();
    }

    private void initView() {
        mFirstNameText = findViewById(R.id.text_view_first_name);
        mLastNameEditText = findViewById(R.id.edit_text_last_name);
        mPhoneEditText = findViewById(R.id.edit_text_phone);
        mUpdateButton = findViewById(R.id.bt_update);
        mDeleteButton = findViewById(R.id.bt_delete);
        mDb = StudentDatabase.getStudentDatabase(getApplicationContext());
    }

    private void setValues() {
        Intent intent = getIntent();
        final Student student = intent.getParcelableExtra("KEY");
        final String firstName = student.getFirstName();
        mFirstNameText.setText(firstName);
        mLastNameEditText.setText(student.getLastName());
        mPhoneEditText.setText(String.valueOf(student.getPhone()));

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lastName = mLastNameEditText.getText().toString();
                String phone = mPhoneEditText.getText().toString().trim();
                Long phoneNumber = Long.parseLong(phone);

                if (lastName.isEmpty()) {
                    Toast.makeText(DetailsActivity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();

                } else if (phone.isEmpty()) {
                    Toast.makeText(DetailsActivity.this, "Enter  Number", Toast.LENGTH_SHORT).show();
                } else {
                    mDb.studentModel().updateStudent(firstName,lastName,phoneNumber);
                    Toast.makeText(DetailsActivity.this, "Value updated", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDb.studentModel().delete(student);
                Toast.makeText(DetailsActivity.this, "Value Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
