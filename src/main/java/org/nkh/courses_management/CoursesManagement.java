package org.nkh.courses_management;

import org.nkh.teachers_management.Teacher;
import org.nkh.users_management.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoursesManagement {
    private List<Course> courses;
    private Users users;
    public CoursesManagement(Users users){
        courses = new ArrayList<>();
        this.users = users;
    }

    public void addCourse(Course course){
        Optional<Teacher> foundTeacher = users.getTeacherById(course.getTeacher().getId());
        if(foundTeacher.isPresent()){
            courses.add(course);
            foundTeacher.get().addCourse(course);
        }else {
            System.out.println("Can not add course. Teacher " + course.getTeacher().getName() + " not found in the system");
        }
    }
}
