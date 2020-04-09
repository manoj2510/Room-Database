package com.example.roomapplication.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomapplication.DetailsActivity;
import com.example.roomapplication.R;
import com.example.roomapplication.database.Student;
import com.example.roomapplication.database.StudentDatabase;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private List<Student> mStudentList;
    private Context mContext;
    private StudentDatabase mDb;

    public StudentAdapter(List<Student> mStudentList, Context mContext) {
        this.mStudentList = mStudentList;
        this.mContext = mContext;
        mDb = StudentDatabase.getStudentDatabase(mContext);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_student, parent, false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final Student student = mStudentList.get(position);
        holder.mFirstNameText.setText(student.getFirstName());
        holder.mLastNameText.setText(student.getLastName());
        holder.mPhoneText.setText(String.valueOf(student.getPhone()));
        holder.mStudentLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("KEY", (Parcelable) student);
                intent.putExtra("KEY", (Parcelable) student);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mStudentList==null || mStudentList.size()==0)
        {
            return 0;
        } else
        {
            return mStudentList.size();
        }
    }


}
