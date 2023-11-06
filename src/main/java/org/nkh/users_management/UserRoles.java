package org.nkh.users_management;

import java.util.InputMismatchException;

import static java.lang.System.out;

public enum UserRoles {
    ADMIN {
        @Override
        public void printModulesNavigationOptions() {
            out.println("Select command you would like to implement from following:");
            out.println("3 - go to Users Management module");
            out.println("4 - go to Students Management module");
            out.println("exit - exit the application");
        }
    },
    STUDENT {
        @Override
        public void printModulesNavigationOptions() {
            out.println("Select command you would like to implement from following:");
            out.println("5 - go to my profile");
            out.println("exit - exit the application");
        }
    },
    TEACHER {
        @Override
        public void printModulesNavigationOptions() {
            out.println("Select command you would like to implement from following:");
            out.println("3 - go to Users Management module");
            out.println("exit - exit the application");
        }
    };
    public static UserRoles getRole(String input) throws Exception {
        switch (input.toLowerCase()){
            case "admin": return ADMIN;
            case "student": return STUDENT;
            case "teacher": return TEACHER;
            default: throw new InputMismatchException("Role is not recognized");
        }
    }

    public abstract void printModulesNavigationOptions();

}
