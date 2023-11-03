package org.users_management;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private boolean isActive;
    private LocalDateTime creationDate;

    private Account account;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.creationDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
