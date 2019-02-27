package com.example.week3day1homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataEntryActivity extends AppCompatActivity {
  EditText etStudentName;
  EditText etStudentMajor;
  EditText etStudentMinor;
  EditText etStudentGPA;
  EditText etCompletedHours;
  EditText etExpectedGraduation;
  Button btnSubmit;
  StudentDatabaseHelper studentDatabaseHelper;
  Intent sentIntent;
  SharedPreferences sharedPreferences;
  public static final String LAST_STUDENT_NAME_ENTERED = "student_name";
  public static final String LAST_STUDENT_MAJOR = "major";
  public static final String LAST_STUDENT_MINOR = "minor";
  public static final String LAST_GPA = "gpa";
  public static final String LAST_CREDIT_HOURS = "hours";
  public static final String LAST_GRADUATION_YEAR = "grad_year";
  public static final String STUDENT_RESULT_KEY = "db_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        bindViews();
        sentIntent = getIntent();
        sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);
    }

    public void bindViews() {

      etStudentName = findViewById(R.id.etStudentName);
      etStudentMajor = findViewById(R.id.etStudentMAJOR);
      etStudentMinor = findViewById(R.id.etStudentMinor);
      etStudentGPA = findViewById(R.id.etCreditHours);
      etCompletedHours = findViewById(R.id.etCreditHours);
      etExpectedGraduation = findViewById(R.id.etExpectedYear);
      btnSubmit = findViewById(R.id.btnSavetoDatabase);




    }

  public Student generateStudentObjectFromInput() {

    Student returnStudent = new Student();
    returnStudent.setStudentName(etStudentName.getText() != null ? etStudentName.getText().toString() : "");
    returnStudent.setStudentMajor(etStudentMajor.getText() != null ? etStudentMajor.getText().toString() : "");
    returnStudent.setStudentMinor(etStudentMinor.getText() != null ? etStudentMinor.getText().toString() : "");
    returnStudent.setExpectedGradYear(etExpectedGraduation.getText() != null ? etExpectedGraduation.getText().toString() : "");
    returnStudent.setGpa(etStudentGPA.getText() != null ? etStudentGPA.getText().toString() : "");
    returnStudent.setStudentCompletedHours(Integer.parseInt(etCompletedHours.getText().toString()));

    return returnStudent;

  }

    public void onClick (View view)  {

      Student studentResult = generateStudentObjectFromInput();
      studentDatabaseHelper.insertStudentIntoDatabase(studentResult);
      Bundle bundleOfTheCarResult = new Bundle();
      bundleOfTheCarResult.putParcelable(STUDENT_RESULT_KEY, studentResult);
      sentIntent.putExtras(bundleOfTheCarResult);
      SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
      sharedPrefEditor.putString(LAST_STUDENT_NAME_ENTERED, studentResult.getStudentName());
      sharedPrefEditor.putString(LAST_STUDENT_MAJOR, studentResult.getStudentMajor());
      sharedPrefEditor.putString(LAST_STUDENT_MINOR, studentResult.getStudentMinor());
      sharedPrefEditor.putString(LAST_GPA, studentResult.getGpa());
      sharedPrefEditor.putInt(LAST_CREDIT_HOURS, studentResult.getStudentCompletedHours());
      sharedPrefEditor.putString(LAST_GRADUATION_YEAR, studentResult.getExpectedGradYear());
      sharedPrefEditor.commit();
      setResult(155, sentIntent);
      finish();


    }
}
