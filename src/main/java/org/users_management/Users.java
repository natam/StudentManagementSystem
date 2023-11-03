package org.users_management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Users {
    private List<User> users = new ArrayList<>();

    public boolean registerNewUser(String name, String email, String userName, String password ){
        if(checkIfUserNameExist(userName)) {
            System.out.println("User with such userName already exists.");
            return false;
        }else {
            User newUser = new User(users.size(), name, email);
            Account userAccount = new Account(newUser.getId(), userName, password, UserRoles.STANDARD);
            newUser.setAccount(userAccount);
            users.add(newUser);
            return true;
        }
    }

    public boolean checkIfUserNameExist(String userName){
        long matches = users.stream().map(user -> user.getAccount().getUserName()).filter(name -> name.equals(userName)).count();
        return matches > 0;
    }

    public Optional<User> getUserByUserName(String userName){
        return users.stream().filter(user -> user.getAccount().getUserName().equals(userName)).findFirst();
    }

    public boolean login(String userName, String password){
        Optional<User> user = getUserByUserName(userName);
        if(user.isPresent()){
            if(user.get().getAccount().getPassword().equals(password)){
                System.out.println("Welcome " + user.get().getName() + "!");
                return true;
            }
        }else {
            System.out.println("UserName or password is not valid");
        }
        return false;
    }
}
