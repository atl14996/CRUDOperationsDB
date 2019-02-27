package com.example.week3day1homework;

import java.util.Locale;

public class StudentDatabaseContract {

    public static final String DATABASE_NAME = "student_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "student_data";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENT_NAME = "student_name";
    public static final String COLUMN_MAJOR = "student_major";
    public static final String COLUMN_MINOR = "student_minor";
    public static final String COLUMN_CLASSYEAR = "grad_year";
    public static final String COLUMN_GPA = "gpa";
    public static final String COLUMN_HOURS = "completed_hours";


    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("CREATE TABLE");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        queryBuilder.append(COLUMN_STUDENT_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        queryBuilder.append(COLUMN_STUDENT_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_MAJOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_MINOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_CLASSYEAR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_GPA);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_HOURS);
        queryBuilder.append(" TEXT ) ");



        return queryBuilder.toString();

    }

    public static String getOneStudentById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_STUDENT_ID, id);
        // return "SELECT * FROM" + TABLE_NAME + " WHERE " + COLUMN_ID + " = ";
    }

    public static String getWhereClauseById(){

        return String.format(Locale.US, "%s = ", COLUMN_STUDENT_ID);
    }
}
