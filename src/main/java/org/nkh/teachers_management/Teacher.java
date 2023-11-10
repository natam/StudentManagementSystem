package org.nkh.teachers_management;

import org.nkh.courses_management.Course;
import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private List<Course> courses;
    public Teacher(String name, String email) {
        super(name, email, UserRoles.TEACHER);
        courses = new ArrayList<>();
    }

    public void addCourse(Course course){
        courses.add(course);
    }
}
