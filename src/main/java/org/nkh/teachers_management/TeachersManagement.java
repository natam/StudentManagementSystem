package org.nkh.teachers_management;

import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;

import java.util.List;
import java.util.Optional;

public class TeachersManagement {
    private List<User> users;
    public TeachersManagement(List<User> users){
        this.users = users;
    }

    public List<Teacher> getTeachers(){
        return users.stream().filter(user -> user.getUserRole() == UserRoles.TEACHER).map((user -> (Teacher) user)).toList();
    }

    public Optional<Teacher> getTeacherById(String id) {
        return getTeachers().stream().filter(user -> (user.getId().equals(id))).findFirst();
    }
}
