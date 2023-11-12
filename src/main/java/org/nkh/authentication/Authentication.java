package org.nkh.authentication;

import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;
import org.nkh.users_management.Users;

import java.util.Optional;

public class Authentication {
    private Users users;
    private Session session;

    public Authentication(Users users){
        this.users = users;
    }

    public Session getSession() {
        return session;
    }

    public boolean login(String userName, String password) {
        Optional<User> user = users.getUserByUserName(userName);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                System.out.println("Welcome " + user.get().getName() + "!");
                session = new Session(user.get());
                return true;
            }
        } else {
            System.out.println("UserName or password is not valid");
        }
        return false;
    }

    public boolean registerNewUser(String name, String email, String userName, String password, UserRoles role) {
        if (users.addNewUser(name, email, userName, password, role)) {
            login(userName, password);
            return true;
        }return false;
    }

    public void logout(){
        this.session = null;
    }
}
