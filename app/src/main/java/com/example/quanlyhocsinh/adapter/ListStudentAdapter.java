package com.example.quanlyhocsinh.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends BaseAdapter {

    private List<Student> listStudent;
    private Activity activity;

    public ListStudentAdapter(Activity activity, List<Student> listStudent) {
        this.activity = activity;
        this.listStudent = listStudent;
    }

    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return listStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = listStudent.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();

        convertView = inflater.inflate(R.layout.student_item, null);

        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvBirthday = (TextView) convertView.findViewById(R.id.tvBirthday);
        TextView tvHometown = (TextView) convertView.findViewById(R.id.tvHometown);
        TextView tvYearStudy = (TextView) convertView.findViewById(R.id.tvYearStudy);

        tvId.setText("" + student.getId());
        tvName.setText(student.getName());
        tvBirthday.setText(student.getBirthday());
        tvHometown.setText("" + student.getHometown());
        tvYearStudy.setText("" + student.getYearStudy());

        return convertView;
    }

    public void filterList(List<Student> filteredList) {
        this.listStudent = filteredList;
        notifyDataSetChanged();
    }

}
