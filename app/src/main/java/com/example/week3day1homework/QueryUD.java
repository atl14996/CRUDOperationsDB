package com.example.week3day1homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QueryUD extends AppCompatActivity {
    public static final String STUDENT_RESULT_KEY = "db_result";
    public static final String LAST_STUDENT_NAME_ENTERED = "student_name";
    public static final String LAST_STUDENT_MAJOR = "major";
    public static final String LAST_STUDENT_MINOR = "minor";
    public static final String LAST_GPA = "gpa";
    public static final String LAST_CREDIT_HOURS = "hours";
    public static final String LAST_GRADUATION_YEAR = "grad_year";


EditText etStudentNumber;
Button btnSearchButton;
    Intent sentIntent;
    SharedPreferences sharedPreferences;
    StudentDatabaseHelper studentDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_ud);
        sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);
        sentIntent = getIntent();
    }

    public void bindViews()     {


        etStudentNumber = findViewById(R.id.etEnterStudentNumber);
        btnSearchButton = findViewById(R.id.btnSearchByID);
    }


    public void onClick (View view) {
        int id = Integer.parseInt(etStudentNumber.getText().toString());
        Student studentResult = studentDatabaseHelper.getStudentById(id);
        Bundle bundleOfTheStudentResult = new Bundle();
        bundleOfTheStudentResult.putParcelable(STUDENT_RESULT_KEY, studentResult);
        sentIntent.putExtras(bundleOfTheStudentResult);

        setResult(155, sentIntent);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(LAST_STUDENT_NAME_ENTERED, studentResult.getStudentName());
        sharedPrefEditor.putString(LAST_STUDENT_MAJOR, studentResult.getStudentMajor());
        sharedPrefEditor.putString(LAST_STUDENT_MINOR, studentResult.getStudentMinor());
        sharedPrefEditor.putString(LAST_GPA, studentResult.getGpa());
        sharedPrefEditor.putInt(LAST_CREDIT_HOURS, studentResult.getStudentCompletedHours());
        sharedPrefEditor.putString(LAST_GRADUATION_YEAR, studentResult.getExpectedGradYear());
        sharedPrefEditor.commit();
        finish();


    }
}
