package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAddStudent, btnAddClass, btnShowListStudent, btnShowListClass, btnShowStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddClass = findViewById(R.id.btnAddClass);
        btnShowListStudent = findViewById(R.id.btnShowListStudent);
        btnShowListClass = findViewById(R.id.btnShowListClass);
        btnShowStatistic = findViewById(R.id.btnShowStatistic);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewStudentActivity.class);
                startActivity(intent);
            }
        });

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewClassActivity.class);
                startActivity(intent);
            }
        });

        btnShowListStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListStudentActivity.class);
                startActivity(intent);
            }
        });

        btnShowListClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListClassActivity.class);
                startActivity(intent);
            }
        });

        btnShowStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowStatisticActivity.class);
                startActivity(intent);
            }
        });

    }
}