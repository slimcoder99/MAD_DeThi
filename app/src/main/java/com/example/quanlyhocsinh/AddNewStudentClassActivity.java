package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyhocsinh.database.DatabaseHelper;

public class AddNewStudentClassActivity extends AppCompatActivity {

    private TextView tvStudentId, tvClassId;
    private Spinner pickerSemester, pickerCredits;
    private Button btnAddNew;
    private int studentId, classId;
    private int semester, credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student_class);

        tvStudentId = findViewById(R.id.tvStudentId);
        tvClassId = findViewById(R.id.tvClassId);
        pickerSemester = findViewById(R.id.pickerSemester);
        pickerCredits = findViewById(R.id.pickerCredits);

        btnAddNew = findViewById(R.id.btnAddNew);

        String[] semesterItems = new String[]{"1", "2", "3"};

        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, semesterItems);
        pickerSemester.setAdapter(semesterAdapter);

        String[] creditItems = new String[]{"1", "2", "3", "4"};

        ArrayAdapter<String> creditAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, creditItems);
        pickerCredits.setAdapter(creditAdapter);

        studentId = getIntent().getIntExtra("studentId", 0);
        classId = getIntent().getIntExtra("classId", 0);

        tvStudentId.setText("Student id: " + studentId);
        tvClassId.setText("Class id: " + classId);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(AddNewStudentClassActivity.this);
                db.addNewStudentClass(studentId, classId, semester, credits);

                Intent intent = new Intent(AddNewStudentClassActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        pickerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        semester = 1;
                        break;
                    case 1:
                        semester = 2;
                        break;
                    case 2:
                        semester = 3;
                        break;
                    default:
                        semester = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                semester = 1;
            }
        });

        pickerSemester.setPrompt("Select your Semester!");

        pickerCredits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        credits = 1;
                        break;
                    case 1:
                        credits = 2;
                        break;
                    case 2:
                        credits = 3;
                        break;
                    case 3:
                        credits = 4;
                        break;
                    default:
                        credits = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                credits = 1;
            }
        });

        pickerCredits.setPrompt("Select your Credits!");

    }
}