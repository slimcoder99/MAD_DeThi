package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyhocsinh.database.DatabaseHelper;
import com.example.quanlyhocsinh.model.Class;

public class AddNewClassActivity extends AppCompatActivity {

    private EditText etName, etDescription;
    private Button btnAddNewClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_class);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);

        btnAddNewClass = findViewById(R.id.btnAddClass);

        btnAddNewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etName.getText()) || TextUtils.isEmpty(etDescription.getText())) {
                    Toast.makeText(AddNewClassActivity.this, "Please fill all this fields", Toast.LENGTH_SHORT).show();
                } else {
                    Class newClass = new Class();
                    newClass.setName(etName.getText().toString());
                    newClass.setDescription(etDescription.getText().toString());

                    DatabaseHelper db = new DatabaseHelper(AddNewClassActivity.this);
                    db.addNewClass(newClass);
                    Toast.makeText(AddNewClassActivity.this, "Successfully add new class", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}