package org.nkh.authentication;

import org.nkh.users_management.User;

import java.time.LocalDateTime;

public class Session {
    private String sessionId;
    private LocalDateTime loginTime;
    private User currentUser;

    public Session(User user) {
        sessionId = "session_" + System.currentTimeMillis();
        loginTime = LocalDateTime.now();
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
