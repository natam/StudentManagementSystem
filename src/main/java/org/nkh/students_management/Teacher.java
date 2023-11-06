package org.nkh.students_management;

import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;

public class Teacher extends User {
    public Teacher(String name, String email) {
        super(name, email, UserRoles.TEACHER);
    }
}
