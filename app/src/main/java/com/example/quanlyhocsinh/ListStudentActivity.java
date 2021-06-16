package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlyhocsinh.adapter.ListStudentAdapter;
import com.example.quanlyhocsinh.database.DatabaseHelper;
import com.example.quanlyhocsinh.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText etSearch;
    private Spinner listYearStudy;
    private Button btnSearch;
    private String searchText = "";
    private String yearStudy = "";
    private List<Student> listStudent;
    private ListStudentAdapter adapter;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        listView = findViewById(R.id.listStudent);

        etSearch = findViewById(R.id.etSearchName);
        listYearStudy = findViewById(R.id.pickerYearStudy);
        btnSearch = findViewById(R.id.btnSearch);

        listStudent = new ArrayList<>();

        String[] yearStudyItems = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, yearStudyItems);

        listYearStudy.setAdapter(arrayAdapter);
        listYearStudy.setOnItemSelectedListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText = etSearch.getText().toString();
                filterStudents(searchText);
            }
        });

        classId = getIntent().getIntExtra("classId", -1);

        if(classId == -1) {
            loadStudentList();
        } else {
            loadStudentFromClass();
        }
    }

    private void loadStudentFromClass() {
        DatabaseHelper db = new DatabaseHelper(this);

        List<Student> list = db.getAllStudentJoinedClassId(classId);

        adapter = new ListStudentAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void loadStudentList() {
        DatabaseHelper db = new DatabaseHelper(this);

        listStudent = db.getAllStudents();

        adapter = new ListStudentAdapter(this, listStudent);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void filterStudents(String searchText) {
        List<Student> listFilteredStudent = new ArrayList<>();

        if(searchText.equals("")) {
            listFilteredStudent = listStudent;

            adapter.filterList(listFilteredStudent);
        } else {
            for(Student student: listStudent) {
                if(student.getName().equals(searchText) && student.getYearStudy().equals(yearStudy)) {
                    listFilteredStudent.add(student);
                }
            }

            adapter.filterList(listFilteredStudent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                yearStudy = "1";
                break;
            case 1:
                yearStudy = "2";
                break;
            case 2:
                yearStudy = "3";
                break;
            case 3:
                yearStudy = "4";
                break;
            default:
                yearStudy = "1";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        yearStudy = "1";
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = listStudent.get(position);

        Intent intent = new Intent(ListStudentActivity.this, ListClassActivity.class);
        intent.putExtra("studentId", student.getId());
        startActivity(intent);
    }

}