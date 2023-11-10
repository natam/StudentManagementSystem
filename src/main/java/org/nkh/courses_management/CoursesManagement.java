package org.nkh.courses_management;

import org.nkh.teachers_management.Teacher;
import org.nkh.teachers_management.TeachersManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoursesManagement {
    private List<Course> courses;
    private TeachersManagement teachers;
    public CoursesManagement(TeachersManagement teachers){
        courses = new ArrayList<>();
        this.teachers = teachers;
    }

    public void addCourse(Course course){
        Optional<Teacher> foundTeacher = teachers.getTeacherById(course.getTeacher().getId());
        if(foundTeacher.isPresent()){
            courses.add(course);
            foundTeacher.get().addCourse(course);
        }else {
            System.out.println("Can not add course. Teacher " + course.getTeacher().getName() + " not found in the system");
        }

    }
}
