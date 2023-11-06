package org.students_management;

import org.users_management.User;
import org.users_management.UserRoles;

public class Teacher extends User {
    public Teacher(String name, String email) {
        super(name, email, UserRoles.TEACHER);
    }
}
