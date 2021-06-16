package com.example.quanlyhocsinh.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.model.StatisticClass;

import java.util.List;

public class StatisticAdapter extends BaseAdapter {

    private Activity activity;
    private List<StatisticClass> statisticClassList;

    public StatisticAdapter(Activity activity, List<StatisticClass> statisticClassList) {
        this.activity = activity;
        this.statisticClassList = statisticClassList;
    }

    @Override
    public int getCount() {
        return statisticClassList.size();
    }

    @Override
    public Object getItem(int position) {
        return statisticClassList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StatisticClass statisticClass = statisticClassList.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.statistic_item, null);

        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        TextView tvStudentCount = convertView.findViewById(R.id.tvNumberOfStudent);

        tvId.setText("ID: " + statisticClass.getId());
        tvName.setText("Class Name: " + statisticClass.getName());
        tvDescription.setText("Description: " + statisticClass.getDescription());
        tvStudentCount.setText("Number of Students: " + statisticClass.getNumber_student());

        return  convertView;
    }
}
