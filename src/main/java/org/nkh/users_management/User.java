package org.nkh.users_management;

import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String name;
    @Email
    private String email;
    private boolean isActive;
    private LocalDateTime creationDate;

    private String userName;
    private String password;
    UserRoles userRole;

    public User(String name, String email, UserRoles userRole) {
        this.userRole = userRole;
        this.id = userRole + "_" + System.currentTimeMillis();
        this.name = name;
        this.email = email;
        this.creationDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        return str.append(id)
                .append(", ")
                .append(name)
                .append(", ")
                .append(email)
                .append(", ")
                .append(email)
                .append(", ")
                .append(isActive)
                .append(", ")
                .append(creationDate)
                .append(", ")
                .append(userRole)
                .append(", ")
                .append(userName)
                .append(", ")
                .append(password)
                .append(System.lineSeparator()).toString();
    }
}
