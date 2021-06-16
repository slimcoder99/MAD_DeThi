package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.quanlyhocsinh.adapter.StatisticAdapter;
import com.example.quanlyhocsinh.database.DatabaseHelper;
import com.example.quanlyhocsinh.model.StatisticClass;

import java.util.ArrayList;
import java.util.List;

public class ShowStatisticActivity extends AppCompatActivity {

    private ListView listView;
    private List<StatisticClass> statisticClassList;
    private StatisticAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_statistic);

        listView = findViewById(R.id.listview);

        statisticClassList = new ArrayList<>();

        getStatistic();
    }

    private void getStatistic() {
        DatabaseHelper db = new DatabaseHelper(this);

        statisticClassList = db.getStatisticClass();

        adapter = new StatisticAdapter(this, statisticClassList);
        listView.setAdapter(adapter);
    }

}