package com.example.roomapplication.recycler;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomapplication.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    TextView mFirstNameText, mLastNameText, mPhoneText;
    LinearLayout mStudentLinearLayout;


    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        mFirstNameText = itemView.findViewById(R.id.tv_first_name);
        mLastNameText = itemView.findViewById(R.id.tv_last_name);
        mPhoneText = itemView.findViewById(R.id.tv_phone);
        mStudentLinearLayout = itemView.findViewById(R.id.ll_student);
    }
}
