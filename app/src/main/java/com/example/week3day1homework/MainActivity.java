package com.example.week3day1homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvStudentID;
    TextView tvstudentName;
    TextView tvstudentMajor;
    TextView tvstudentMinor;
    TextView tvStudentGPA;
    TextView tvstudentGraduationYear;
    TextView tvstudentCreditHours;
    Button btndataEntryActivity;
    Button btnUpdataDeleteQuery;
    EditText etStudentName;
    EditText etStudentMajor;
    EditText etStudentMinor;
    EditText etStudentGPA;
    EditText etCompletedHours;
    EditText etExpectedGraduation;
    SharedPreferences sharedPreferences;
    StudentDatabaseHelper studentDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);
        studentDatabaseHelper = new StudentDatabaseHelper(this);
    }


    public void bindViews(){

        tvStudentID = findViewById(R.id.tvStudentID);
        tvstudentName = findViewById(R.id.tvStudentName);
        tvstudentMajor = findViewById(R.id.tvStudentMajor);
        tvstudentMinor = findViewById(R.id.tvStudentMinor);
        tvStudentGPA = findViewById(R.id.tvStudentGPA);
        tvstudentGraduationYear = findViewById(R.id.tvStudentGradYear);
        tvstudentCreditHours = findViewById(R.id.tvStudentCreditHours);
        btndataEntryActivity = findViewById(R.id.btnEnterStudentData);
        btnUpdataDeleteQuery = findViewById(R.id.btnQueryUpdateDelete);
etStudentName = findViewById(R.id.etUpdateStudentName);
etStudentMajor = findViewById(R.id.etUpdateStudentMAJOR);
etStudentMinor = findViewById(R.id.etUpdateStudentMinor);
etStudentGPA = findViewById(R.id.etUpdateStudentGPA);
etCompletedHours = findViewById(R.id.etUpdateCreditHours);
etExpectedGraduation = findViewById(R.id.etUpdateExpectedYear);




    }

    public void populateTextViews(@NonNull Student StudentInfotoPopulate) {
        tvStudentID.setText(StudentInfotoPopulate.getStudentId());
        tvstudentName.setText(StudentInfotoPopulate.getStudentName());
        tvstudentMajor.setText(StudentInfotoPopulate.getStudentMajor());
        tvstudentMinor.setText(StudentInfotoPopulate.getStudentMinor());
        tvStudentGPA.setText(StudentInfotoPopulate.getGpa());
        tvstudentGraduationYear.setText(StudentInfotoPopulate.getExpectedGradYear());
        tvstudentCreditHours.setText(StudentInfotoPopulate.getStudentCompletedHours());



    }

    public void onClick (View view) {
switch (view.getId())  {

    case R.id.btnEnterStudentData:

        Intent enterData = new Intent(this, DataEntryActivity.class);
        startActivityForResult(enterData, 999);

        break;

    case R.id.btnQueryUpdateDelete:

            Intent queryUD = new Intent(this, QueryUD.class);
        startActivityForResult(queryUD, 255);

        break;

    case R.id.btnUpdateRecord:



        String name = etStudentName.getText().toString();
        String major = etStudentMajor.getText().toString();
        String minor = etStudentMinor.getText().toString();
        String gpa = etStudentGPA.getText().toString();
        int hours = Integer.parseInt(etCompletedHours.getText().toString());
        String graduation = etExpectedGraduation.getText().toString();
        int studentnumber = Integer.parseInt(tvStudentID.getText().toString());

        Student updateStudent = new Student(studentnumber, name, major, minor, gpa, graduation, hours);

        studentDatabaseHelper.updateStudentInDatabase(updateStudent);
        break;

    case R.id.btndeleteRecord:
        String id = tvStudentID.getText().toString();
        String[] id1 = {id};



        studentDatabaseHelper.deleteFromDatabaseById(id1);

        break;


}



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Bundle resultBundle = data.getExtras();
            if (resultBundle != null) {
                Student resultStudent = resultBundle.getParcelable(QueryUD.STUDENT_RESULT_KEY);
                if (resultStudent != null) {
                    populateTextViews(resultStudent);

                }
            }


        }
    }}