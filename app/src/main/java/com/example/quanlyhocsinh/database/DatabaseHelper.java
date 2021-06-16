package com.example.quanlyhocsinh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.quanlyhocsinh.model.Class;
import com.example.quanlyhocsinh.model.StatisticClass;
import com.example.quanlyhocsinh.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MAD_Database";
    public static final String STUDENT_TABLE_NAME = "Student";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BIRTHDAY = "birthday";
    public static final String HOMETOWN = "hometown";
    public static final String YEAR_STUDY = "year_study";
    public static final String CLASS_TABLE_NAME = "Class";
    public static final String CLASS_ID = "id";
    public static final String CLASS_NAME = "name";
    public static final String CLASS_DESCRIPTION = "description";
    public static final String STUDENT_CLASS_TABLE_NAME = "Student_Class";
    public static final String STUDENT_CLASS_STUDENT_ID = "student_id";
    public static final String STUDENT_CLASS_CLASS_ID = "class_id";
    public static final String STUDENT_CLASS_SEMESTER = "semester";
    public static final String STUDENT_CLASS_CREDITS = "credits";

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + STUDENT_TABLE_NAME + "(" +
                                ID + " INTEGER PRIMARY KEY," +
                                NAME + " TEXT NOT NULL," +
                                BIRTHDAY + " TEXT NOT NULL," +
                                HOMETOWN + " TEXT NOT NULL," +
                                YEAR_STUDY + " TEXT NOT NULL);";
        db.execSQL(sqlStatement);

        String sqlStatement2 = "CREATE TABLE " + CLASS_TABLE_NAME + " (" +
                CLASS_ID + " INTEGER PRIMARY KEY," +
                CLASS_NAME + " TEXT NOT NULL," +
                CLASS_DESCRIPTION + " TEXT NOT NULL);";

        db.execSQL(sqlStatement2);

        String sqlStatement3 = "CREATE TABLE " + STUDENT_CLASS_TABLE_NAME + "(" +
                            STUDENT_CLASS_STUDENT_ID + " INTEGER," +
                            STUDENT_CLASS_CLASS_ID + " INTEGER NOT NULL," +
                            STUDENT_CLASS_SEMESTER + " INTEGER NOT NULL," +
                            STUDENT_CLASS_CREDITS + " INTEGER NOT NULL," +
                            "FOREIGN KEY (" + STUDENT_CLASS_STUDENT_ID + ") REFERENCES " + STUDENT_TABLE_NAME + " (" + ID + ")," +
                            "FOREIGN KEY (" + STUDENT_CLASS_CLASS_ID + ") REFERENCES " + CLASS_TABLE_NAME + " (" + CLASS_ID + "));";

        db.execSQL(sqlStatement3);


        Toast.makeText(context, "Create Table Successfull", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlStatement = "DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME;

        db.execSQL(sqlStatement);

        String sqlStatement1 = "DROP TABLE IF EXISTS " + CLASS_TABLE_NAME;
        db.execSQL(sqlStatement1);

        String sqlStatement2 = "DROP TABLE IF EXISTS " + STUDENT_CLASS_TABLE_NAME;
        db.execSQL(sqlStatement2);

        onCreate(db);
        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(BIRTHDAY, student.getBirthday());
        contentValues.put(HOMETOWN, student.getHometown());
        contentValues.put(YEAR_STUDY, student.getYearStudy());

        db.insert(STUDENT_TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<Student> getAllStudents() {
        List<Student> listStudent = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + STUDENT_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setBirthday(cursor.getString(2));
                student.setHometown(cursor.getString(3));
                student.setYearStudy(cursor.getString(4));

                listStudent.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listStudent;
    }

    public void addNewClass(Class newClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CLASS_NAME, newClass.getName());
        contentValues.put(CLASS_DESCRIPTION, newClass.getDescription());

        db.insert(CLASS_TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<Class> getAllClass() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Class> listClass = new ArrayList<>();

        String sqlStatement = "SELECT * FROM " + CLASS_TABLE_NAME;

        Cursor cursor = db.rawQuery(sqlStatement, null);

        if (cursor.moveToFirst()) {
            do {

                Class newClass = new Class();

                newClass.setId(cursor.getInt(0));
                newClass.setName(cursor.getString(1));
                newClass.setDescription(cursor.getString(2));

                listClass.add(newClass);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return  listClass;
    }

    public void addNewStudentClass(int studentId, int classId, int semester, int credits) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_CLASS_STUDENT_ID, studentId);
        contentValues.put(STUDENT_CLASS_CLASS_ID, classId);
        contentValues.put(STUDENT_CLASS_SEMESTER, semester);
        contentValues.put(STUDENT_CLASS_CREDITS, credits);

        db.insert(STUDENT_CLASS_TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<Student> getAllStudentJoinedClassId(int classId) {

        SQLiteDatabase db = this.getReadableDatabase();

        List<Student> listStudent = new ArrayList<>();

        String sqlStatement = "SELECT Student.id, Student.name, Student.hometown, Student.birthday, Student.year_study FROM Student, Class , Student_Class WHERE" +
                " Student.id = Student_Class.student_id AND" +
                " Class.id= Student_class.class_id AND Class.id = ?;";

        Cursor cursor = db.rawQuery(sqlStatement, new String[]{String.valueOf(classId)});

        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setHometown(cursor.getString(2));
                student.setBirthday(cursor.getString(3));
                student.setYearStudy(cursor.getString(4));

                listStudent.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return  listStudent;
    }

    public List<StatisticClass> getStatisticClass() {
        List<StatisticClass> listClass;
        listClass = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sqlStatement = "select SC.count_class, SC.class_id, C.name, C.description from (SELECT COUNT(Student_Class.class_id) as count_class, Student_Class.class_id as class_id " +
                "From Student_Class " +
                "Group by Student_Class.class_id) as SC " +
                "join Class as C on SC.class_id = C.id " +
                "order by count_class desc;";

        Cursor cursor = db.rawQuery(sqlStatement, null);

        if(cursor.moveToFirst()) {
            do {
                StatisticClass statisticClass = new StatisticClass();
                statisticClass.setNumber_student(cursor.getInt(0));
                statisticClass.setId(cursor.getInt(1));
                statisticClass.setName(cursor.getString(2));
                statisticClass.setDescription(cursor.getString(3));

                listClass.add(statisticClass);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listClass;
    }

}
