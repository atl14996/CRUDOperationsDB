package com.example.week3day1homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.week3day1homework.StudentDatabaseContract.COLUMN_CLASSYEAR;
import static com.example.week3day1homework.StudentDatabaseContract.COLUMN_GPA;
import static com.example.week3day1homework.StudentDatabaseContract.COLUMN_HOURS;
import static com.example.week3day1homework.StudentDatabaseContract.COLUMN_MAJOR;
import static com.example.week3day1homework.StudentDatabaseContract.COLUMN_MINOR;
import static com.example.week3day1homework.StudentDatabaseContract.COLUMN_STUDENT_ID;
import static com.example.week3day1homework.StudentDatabaseContract.COLUMN_STUDENT_NAME;
import static com.example.week3day1homework.StudentDatabaseContract.DATABASE_NAME;
import static com.example.week3day1homework.StudentDatabaseContract.DATABASE_VERSION;
import static com.example.week3day1homework.StudentDatabaseContract.TABLE_NAME;

public class StudentDatabaseHelper extends SQLiteOpenHelper {


    public StudentDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertStudentIntoDatabase(@NonNull Student student) {

        SQLiteDatabase writeableDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_NAME, student.getStudentName());
        contentValues.put(COLUMN_MAJOR, student.getStudentMajor());
        contentValues.put(COLUMN_MINOR, student.getStudentMinor());
        contentValues.put(COLUMN_GPA, student.getGpa());
        contentValues.put(COLUMN_CLASSYEAR, student.getExpectedGradYear());
        contentValues.put(COLUMN_HOURS, student.getStudentCompletedHours());

        //insert the car into the table using contentValues

        return writeableDatabase.insert(TABLE_NAME, null, contentValues);


    }

    public static String getAllStudentsQuery() {
        return "SELECT * FROM" + TABLE_NAME;

    }

    public ArrayList<Student> getAllStudentsFromDatabase() {

        ArrayList<Student> returnStudentList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery(getAllStudentsQuery(), null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_STUDENT_ID));
                String StudentName = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME));
                String major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(COLUMN_MINOR));
                String gradYear = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSYEAR));
                int hours = cursor.getInt(cursor.getColumnIndex(COLUMN_HOURS));
                String gpa = cursor.getString(cursor.getColumnIndex(COLUMN_GPA));


                returnStudentList.add(new Student(id, StudentName, major, minor, gradYear, gpa, hours));
            }
            while (cursor.moveToNext());
            //return the result in a list
        }
        cursor.close();
        return returnStudentList;
    }


    public Student getStudentById(int id) {

        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Student returnStudent = new Student();

        Cursor cursor = readableDatabase.rawQuery(StudentDatabaseContract.getOneStudentById(id), null);
        if (cursor.moveToFirst()) {

            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_STUDENT_ID));
            String StudentName = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME));
            String major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
            String minor = cursor.getString(cursor.getColumnIndex(COLUMN_MINOR));
            String gradYear = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSYEAR));
            int hours = cursor.getInt(cursor.getColumnIndex(COLUMN_HOURS));
            String gpa = cursor.getString(cursor.getColumnIndex(COLUMN_GPA));


            returnStudent = new Student(id, StudentName, major, minor, gradYear, gpa, hours);


        }

        cursor.close();
        return returnStudent;
    }

    public long updateStudentInDatabase(@NonNull Student updateStudentInfo) {


        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        //Data holder used for database key value pairs
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_NAME, updateStudentInfo.getStudentName());
        contentValues.put(COLUMN_MAJOR, updateStudentInfo.getStudentMajor());
        contentValues.put(COLUMN_MINOR, updateStudentInfo.getStudentMinor());
        contentValues.put(COLUMN_GPA, updateStudentInfo.getGpa());
        contentValues.put(COLUMN_CLASSYEAR, updateStudentInfo.getExpectedGradYear());
        contentValues.put(COLUMN_HOURS, updateStudentInfo.getStudentCompletedHours());
        String whereClause = String.format(Locale.US, "WHERE %s = \"%s\"", COLUMN_STUDENT_ID, updateStudentInfo.getStudentId());

        return writeableDatabase.update(TABLE_NAME, contentValues,
                StudentDatabaseContract.getWhereClauseById(),
                new String[]{String.valueOf(updateStudentInfo.getStudentId())});


    }

    //delete entry or entries from the database

    public long deleteFromDatabaseById(String[] id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();



        return sqLiteDatabase.delete(TABLE_NAME, StudentDatabaseContract.getWhereClauseById() + id[0], null);





    }

}
