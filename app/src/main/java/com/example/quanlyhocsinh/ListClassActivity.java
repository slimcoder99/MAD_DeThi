package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quanlyhocsinh.adapter.ListClassAdapter;
import com.example.quanlyhocsinh.database.DatabaseHelper;
import com.example.quanlyhocsinh.model.Class;

import java.util.ArrayList;
import java.util.List;

public class ListClassActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listClass;
    private int studentId;
    List<Class> classLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);

        listClass = findViewById(R.id.listClass);

        classLists = new ArrayList<>();

        studentId = getIntent().getIntExtra("studentId", -1);

        loadAllClass();
    }

    private void loadAllClass() {
        DatabaseHelper db = new DatabaseHelper(this);
        classLists = db.getAllClass();

        ListClassAdapter adapter = new ListClassAdapter(this, classLists);
        listClass.setAdapter(adapter);
        listClass.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Class newClass = classLists.get(position);

        if(studentId == -1) {
            Intent intent = new Intent(ListClassActivity.this, ListStudentActivity.class);
            intent.putExtra("classId", newClass.getId());
            startActivity(intent);
        } else {
            Intent intent = new Intent(ListClassActivity.this, AddNewStudentClassActivity.class);
            intent.putExtra("studentId", studentId);
            intent.putExtra("classId", newClass.getId());
            startActivity(intent);
        }
    }
}