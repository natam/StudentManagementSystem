package org.nkh.students_management;

import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends User {
    private int age;
    private List<Course> takenCourses;

    public Student(String name, String email) {
        super(name, email, UserRoles.STUDENT);
        this.takenCourses = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addCourse(Course course){
        takenCourses.add(course);
    }

    public void removeCourse(Course course){
        takenCourses.remove(course);
    }

    public Course findCourse(String title){
       return takenCourses.stream().filter(course -> course.getTitle().equals(title)).findFirst().get();
    }

    public List<Course> getTakenCourses() {
        return takenCourses;
    }

    public void sortCoursesByTitle(){
        takenCourses = takenCourses.stream().sorted(Comparator.comparing(Course::getTitle)).toList();
    }

    public void sortCoursesById(){
        takenCourses = takenCourses.stream().sorted(Comparator.comparing(Course::getId)).toList();
    }

    @Override
    public String toString(){
        return getId() +
                ", " +
                getName() +
                ", " +
                age +
                ", courses: " +
                takenCourses.stream().map(Course::getTitle).collect(Collectors.joining(", ")) + System.lineSeparator();
    }
}
