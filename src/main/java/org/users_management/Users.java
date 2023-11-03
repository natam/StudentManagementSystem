package org.users_management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Users {
    int counter;
    private List<User> users = new ArrayList<>();

    public Users(){
        counter = 0;
    }

    public boolean registerNewUser(String name, String email, String userName, String password ){
        if(checkIfUserNameExist(userName)) {
            System.out.println("User with such userName already exists.");
            return false;
        }else {
            User newUser = new User(counter, name, email);
            counter ++;
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

    public Optional<User> getUserById(int id){
        return users.stream().filter(user -> user.getId()==id).findFirst();
    }

    public List<User> searchUser(String query){
        return users.stream().filter(user -> (user.getName().contains(query) || user.getEmail().contains(query) || String.valueOf(user.getId()).equals(query))).toList();
    }

    public void deleteUser(int userId){
        Optional<User> userToDelete = getUserById(userId);
        userToDelete.ifPresent(user -> users.remove(user));
    }

    public void updateUser(int userId, String newName, String newEmail, String newPassword, String newUsername){
        Optional<User> userToUpdate = getUserById(userId);
        if(userToUpdate.isPresent()){
            if(!newName.isEmpty()){
                userToUpdate.get().setName(newName);
            }
            if(!newEmail.isEmpty()){
                userToUpdate.get().setEmail(newEmail);
            }
            if(!newUsername.isEmpty()){
                userToUpdate.get().getAccount().setUserName(newUsername);
            }
            if(!newPassword.isEmpty()){
                userToUpdate.get().getAccount().setPassword(newPassword);
            }
        }else {
            System.out.println("User not found");
        }
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
