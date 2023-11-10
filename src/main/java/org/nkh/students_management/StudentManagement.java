package org.nkh.students_management;

import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;

import java.util.List;

public class StudentManagement {
    private List<User> users;

    public StudentManagement(List<User> users){
        this.users = users;
    }

    public List<Student> getStudents() {
        return users.stream().filter(user -> user.getUserRole() == UserRoles.STUDENT).map((user -> (Student) user)).toList();
    }

    public List<Student> getStudentsByCourse(String courseTitle) {
        return getStudents().stream().filter(student -> student.findCourse(courseTitle) != null).toList();
    }

    public List<Student> searchStudent(String query) {
        return getStudents().stream().filter(user -> (user.getName().contains(query) || user.getEmail().contains(query) || String.valueOf(user.getId()).equals(query))).toList();
    }
}
