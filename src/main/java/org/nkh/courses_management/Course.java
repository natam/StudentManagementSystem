package org.nkh.courses_management;

import org.nkh.teachers_management.Teacher;

public class Course {
    private int id;
    private String title;
    private Teacher teacher;
    private double grade;

    public Course(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
