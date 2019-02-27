package com.example.week3day1homework;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    public int studentId;
    public String studentName;
    public String studentMajor;
    public String studentMinor;
    public String expectedGradYear;
    public String gpa;
    public int studentCompletedHours;

    public Student() {


    }

    public Student(int studentId, String studentName, String studentMajor, String studentMinor, String expectedGradYear, String gpa, int studentCompletedHours) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentMinor = studentMinor;
        this.expectedGradYear = expectedGradYear;
        this.gpa = gpa;
        this.studentCompletedHours = studentCompletedHours;
    }

    protected Student(Parcel in) {
        studentId = in.readInt();
        studentName = in.readString();
        studentMajor = in.readString();
        studentMinor = in.readString();
        expectedGradYear = in.readString();
        gpa = in.readString();
        studentCompletedHours = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(studentId);
        dest.writeString(studentName);
        dest.writeString(studentMajor);
        dest.writeString(studentMinor);
        dest.writeString(expectedGradYear);
        dest.writeString(gpa);
        dest.writeInt(studentCompletedHours);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentMinor() {
        return studentMinor;
    }

    public void setStudentMinor(String studentMinor) {
        this.studentMinor = studentMinor;
    }

    public String getExpectedGradYear() {
        return expectedGradYear;
    }

    public void setExpectedGradYear(String expectedGradYear) {
        this.expectedGradYear = expectedGradYear;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public int getStudentCompletedHours() {
        return studentCompletedHours;
    }

    public void setStudentCompletedHours(int studentCompletedHours) {
        this.studentCompletedHours = studentCompletedHours;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentMinor='" + studentMinor + '\'' +
                ", expectedGradYear='" + expectedGradYear + '\'' +
                ", gpa=" + gpa +
                ", studentCompletedHours=" + studentCompletedHours +
                '}';
    }
}
