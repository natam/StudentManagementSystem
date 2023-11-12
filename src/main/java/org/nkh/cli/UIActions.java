package org.nkh.cli;

import org.nkh.authentication.Authentication;
import org.nkh.students_management.Student;
import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;
import org.nkh.users_management.Users;
import org.nkh.utils.Utils;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class UIActions {
    private Authentication authentication;
    private Users users;

    public UIActions(Users users, Authentication authentication) {
        this.users = users;
        this.authentication = authentication;
    }

    public String printAndReadOutput(String message) {
        Scanner sc = new Scanner(in);
        out.println(message);
        return sc.nextLine();
    }

    public void printCommandOptions() {
        out.println("Select command you would like to implement from following:");
        out.println("1 - enter/modify your first name");
        out.println("2 - enter/modify your last name");
        out.println("3 - enter/modify your age");
        out.println("4 - enter/modify your gender");
        out.println("exit - exit the application");
    }

    public void printStudentsCommandOptions() {
        out.println("Select command you would like to implement from following:");
        out.println("11 - add new student");
        out.println("12 - find student");
        out.println("13 - edit student");
        out.println("14 - delete student");
        out.println("15 - update student's grade");
        out.println("exit - exit the application");
    }

    public void printUsersCommandOptions() {
        out.println("Select command you would like to implement from following:");
        out.println("7 - add new user");
        out.println("8 - find user");
        out.println("9 - edit user");
        out.println("10 - delete user");
        out.println("exit - exit the application");
    }

    public void printCoursesCommandOptions() {

    }

    protected void updateGrade() {
        String studentName = printAndReadOutput("Enter student name:");
        String courseTitle = printAndReadOutput("Enter course title:");
        String grade = printAndReadOutput("Enter grate:");
        users.addOrUpdateGrade(courseTitle, studentName, Double.parseDouble(grade));
    }

    protected void updateUser() {
        String userToUpdateId = printAndReadOutput("Enter the id of user, you want to update:");
        String newName = printAndReadOutput("Enter user new name:");
        String newEmail = printAndReadOutput("Enter user new email:");
        String newUsername = printAndReadOutput("Enter user new username:");
        String newPassword = printAndReadOutput("Enter user new password:");
        users.updateUser(userToUpdateId, newName, newEmail, newPassword, newUsername);
    }

    protected void updateStudent() {
        String studentToUpdateId = printAndReadOutput("Enter the id of student, you want to update:");
        String newName = printAndReadOutput("Enter student new name:");
        String newEmail = printAndReadOutput("Enter student new email:");
        String newUsername = printAndReadOutput("Enter student new username:");
        String newPassword = printAndReadOutput("Enter student new password:");
        users.getUserById(studentToUpdateId).ifPresent(user -> {
            if (user.getUserRole() == UserRoles.STUDENT) {
                users.updateUser(studentToUpdateId, newName, newEmail, newPassword, newUsername);
            } else out.println("Student with id " + studentToUpdateId + " exists");
        });
    }

    protected void deleteUser() {
        String userToDeleteId = printAndReadOutput("Enter the id of user, you want to update:");
        users.deleteUser(userToDeleteId);
    }

    protected void deleteStudent() {
        String userToDeleteId = printAndReadOutput("Enter the id of user, you want to update:");
        users.getUserById(userToDeleteId).ifPresent(user -> {
            if (user.getUserRole() == UserRoles.STUDENT) {
                users.deleteUser(userToDeleteId);
            } else out.println("Student with id " + userToDeleteId + " exists");
        });
    }

    protected void register() {
        String name = printAndReadOutput("Please enter your name:");
        String email = printAndReadOutput("Please enter your email:");
        String newUserName = printAndReadOutput("Please enter your username:");
        String newPassword = printAndReadOutput("Please enter your password:");
        String role = printAndReadOutput("Please enter your role teacher/student:");
        try {
            UserRoles userRole = UserRoles.getRole(role);
            if (authentication.registerNewUser(name, email, newUserName, newPassword, userRole)) {
                authentication.getSession().getCurrentUser().getUserRole().printModulesNavigationOptions();
            }
        } catch (Exception e) {
            out.println("Invalid role entered: " + role);
        }
    }

    protected void addUser() {
        String name = printAndReadOutput("Enter user name:");
        String email = printAndReadOutput("Enter user email:");
        String userName = printAndReadOutput("Enter user username:");
        String password = printAndReadOutput("Enter user password:");
        String role = printAndReadOutput("Enter user role teacher/student/admin:");
        try {
            UserRoles userRole = UserRoles.getRole(role);
            users.addNewUser(name, email, userName, password, userRole);
        } catch (Exception e) {
            out.println("Invalid role entered: " + role);
        }
    }

    protected void addStudent() {
        String name = printAndReadOutput("Enter student name:");
        String email = printAndReadOutput("Enter student email:");
        users.addNewUser(name, email, null, null, UserRoles.STUDENT);
    }

    protected void searchUsers() {
        String query = printAndReadOutput("Please enter user's name or id or email:");
        List<User> foundUsers = users.searchUser(query);
        if (!foundUsers.isEmpty()) {
            Utils.print(foundUsers);
        } else out.println("No users for search query were found");
    }

    protected void searchStudents() {
        String query = printAndReadOutput("Please enter student's name or id or email:");
        List<Student> foundStudents = users.searchStudent(query);
        if (!foundStudents.isEmpty()) {
            Utils.print(foundStudents);
        } else out.println("No students for search query were found");
    }

    protected void login() {
        String userNameInput = printAndReadOutput("Please enter your username:");
        String passwordInput = printAndReadOutput("Please enter your password:");
        if (authentication.login(userNameInput, passwordInput)) {
            authentication.getSession().getCurrentUser().getUserRole().printModulesNavigationOptions();
        }
    }

    public UserRoles getCurrentUserRole(){
        return authentication.getSession().getCurrentUser().getUserRole();
    }
}
