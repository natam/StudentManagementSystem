package org.users_management;

public class Admin extends User{
    public Admin(String name, String email) {
        super(name, email, UserRoles.ADMIN);
    }
}
