package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlyhocsinh.database.DatabaseHelper;
import com.example.quanlyhocsinh.model.Student;

public class AddNewStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etName, etBirthday, etHometown;
    private Spinner yearStudyPicker;
    private Button btnAddStudent;
    private String name = "", birthday = "", hometown = "", yearStudy = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        etName = findViewById(R.id.etName);
        etBirthday = findViewById(R.id.etBirthday);
        etHometown = findViewById(R.id.etHometown);

        yearStudyPicker = findViewById(R.id.pickerYearStudy);

        btnAddStudent = findViewById(R.id.btnAddStudent);

        String[] yearStudyItems = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, yearStudyItems);

        yearStudyPicker.setAdapter(arrayAdapter);
        yearStudyPicker.setOnItemSelectedListener(this);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etName.getText()) || TextUtils.isEmpty(etBirthday.getText()) || TextUtils.isEmpty(etHometown.getText())) {
                    Toast.makeText(AddNewStudentActivity.this, "Please fill all this field", Toast.LENGTH_SHORT).show();
                } else {

                    DatabaseHelper db = new DatabaseHelper(AddNewStudentActivity.this);

                    name = etName.getText().toString().trim();
                    birthday = etBirthday.getText().toString().trim();
                    hometown = etHometown.getText().toString().trim();

                    Student addStudent = new Student();

                    addStudent.setName(name);
                    addStudent.setBirthday(birthday);
                    addStudent.setHometown(hometown);
                    addStudent.setYearStudy(yearStudy);

                    db.addStudent(addStudent);
                    Toast.makeText(AddNewStudentActivity.this, "Add student successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
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
}