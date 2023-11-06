package org.nkh.cli;

import org.nkh.students_management.Student;
import org.nkh.users_management.User;
import org.nkh.users_management.UserRoles;
import org.nkh.users_management.Users;
import org.nkh.utils.Utils;

import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class UI {
    Scanner sc = new Scanner(in);
    Users users;
    User currentUser;

    public UI(Users users) {
        this.users = users;
    }

    public void runUI() {
        out.println("Student Management System");
        printSystemAccessOptions();
        out.println("Please login to continue");

        String command = sc.nextLine();

        while (!command.equals("exit")) {
            readAndImplementCommand(command);
            command = printAndReadOutput("Enter the command, you would like to implement: ");
        }
        sc.close();
    }

    public void readAndImplementCommand(String command) {
        switch (command) {
            case "1":
                login();
                break;
            case "2":
                register();
                break;
            case "3":
                currentUser.getUserRole().printModulesNavigationOptions();
                break;
            case "4":
                if (currentUser.getUserRole() != UserRoles.STUDENT) {
                    printStudentsCommandOptions();
                } else out.println("You don't have permissions");
                break;
            case "6":
                addUser();
                break;
            case "7":
                searchUsers();
                break;
            case "8":
                updateUser();
                break;
            case "9":
                deleteUser();
                break;
            case "10":
                addStudent();
                break;
            case "11":
                searchStudents();
                break;
            case "12":
                updateStudent();
                break;
            case "13":
                deleteStudent();
                break;
            case "14":
                if(currentUser.getUserRole()==UserRoles.TEACHER) {
                    String studentName = printAndReadOutput("Enter student name:");
                    String courseTitle = printAndReadOutput("Enter course title:");
                    String grade = printAndReadOutput("Enter grate:");
                    users.addOrUpdateGrade(courseTitle, studentName, Double.parseDouble(grade));
                }else out.println("You don't have permissions");
                break;
            case "exit":
                break;
            default:
                out.println("Command is not defined.");
                printCommandOptions();
        }
    }

    public String printAndReadOutput(String message) {
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
        out.println("10 - add new student");
        out.println("11 - find student");
        out.println("12 - edit student");
        out.println("13 - delete student");
        out.println("14 - update student's grade");
        out.println("exit - exit the application");
    }

    public void printUsersCommandOptions() {
        out.println("Select command you would like to implement from following:");
        out.println("6 - add new user");
        out.println("7 - find user");
        out.println("8 - edit user");
        out.println("9 - delete user");
        out.println("exit - exit the application");
    }

    public void printSystemAccessOptions() {
        out.println("Please select:");
        out.println("1 - login");
        out.println("2 - registration");
        out.println("exit - exit the application");
    }

    public void updateUser() {
        if (currentUser.getUserRole() == UserRoles.ADMIN) {
            String userToUpdateId = printAndReadOutput("Enter the id of user, you want to update:");
            String newName = printAndReadOutput("Enter user new name:");
            String newEmail = printAndReadOutput("Enter user new email:");
            String newUsername = printAndReadOutput("Enter user new username:");
            String newPassword = printAndReadOutput("Enter user new password:");
            users.updateUser(userToUpdateId, newName, newEmail, newPassword, newUsername);
        } else {
            out.println("You don't have permissions");
        }
    }

    public void updateStudent() {
        if (currentUser.getUserRole() == UserRoles.TEACHER) {
            String studentToUpdateId = printAndReadOutput("Enter the id of student, you want to update:");
            String newName = printAndReadOutput("Enter student new name:");
            String newEmail = printAndReadOutput("Enter student new email:");
            String newUsername = printAndReadOutput("Enter student new username:");
            String newPassword = printAndReadOutput("Enter student new password:");
            users.getUserById(studentToUpdateId).ifPresent(user -> {
                if (user.getUserRole() == UserRoles.STUDENT) {
                    users.updateUser(studentToUpdateId, newName, newEmail, newPassword, newUsername);
                }else out.println("Student with id " + studentToUpdateId + " exists");
            });
        } else {
            out.println("You don't have permissions");
        }
    }

    public void deleteUser() {
        if (currentUser.getUserRole() == UserRoles.ADMIN) {
            String userToDeleteId = printAndReadOutput("Enter the id of user, you want to update:");
            users.deleteUser(userToDeleteId);
        } else {
            out.println("You don't have permissions");
        }
    }

    public void deleteStudent() {
        if (currentUser.getUserRole() == UserRoles.ADMIN) {
            String userToDeleteId = printAndReadOutput("Enter the id of user, you want to update:");
            users.getUserById(userToDeleteId).ifPresent(user -> {
                if (user.getUserRole() == UserRoles.STUDENT) {
                    users.deleteUser(userToDeleteId);
                }else out.println("Student with id " + userToDeleteId + " exists");
            });
        } else {
            out.println("You don't have permissions");
        }
    }

    public void register() {
        String name = printAndReadOutput("Please enter your name:");
        String email = printAndReadOutput("Please enter your email:");
        String newUserName = printAndReadOutput("Please enter your username:");
        String newPassword = printAndReadOutput("Please enter your password:");
        String role = printAndReadOutput("Please enter your role teacher/student:");
        try {
            UserRoles userRole = UserRoles.getRole(role);
            if (users.registerNewUser(name, email, newUserName, newPassword, userRole)) {
                users.getUserByUserName(newUserName).ifPresent(user -> currentUser = user);
                currentUser.getUserRole().printModulesNavigationOptions();
            }
        } catch (Exception e) {
            out.println("Invalid role entered: " + role);
        }
    }

    public void addUser() {
        if (currentUser.getUserRole() == UserRoles.ADMIN) {
            String name = printAndReadOutput("Enter user name:");
            String email = printAndReadOutput("Enter user email:");
            String userName = printAndReadOutput("Enter user username:");
            String password = printAndReadOutput("Enter user password:");
            String role = printAndReadOutput("Enter user role teacher/student/admin:");
            try {
                UserRoles userRole = UserRoles.getRole(role);
                users.registerNewUser(name, email, userName, password, userRole);
            } catch (Exception e) {
                out.println("Invalid role entered: " + role);
            }
        } else out.println("You don't have permissions");
    }

    public void addStudent() {
        if (currentUser.getUserRole() == UserRoles.TEACHER) {
            String name = printAndReadOutput("Enter student name:");
            String email = printAndReadOutput("Enter student email:");
            users.registerNewUser(name, email, null, null, UserRoles.STUDENT);
        } else out.println("You don't have permissions");
    }

    private void searchUsers() {
        if (currentUser.getUserRole() == UserRoles.ADMIN) {
            String query = printAndReadOutput("Please enter user's name or id or email:");
            List<User> foundUsers = users.searchUser(query);
            if (!foundUsers.isEmpty()) {
                Utils.print(foundUsers);
            } else out.println("No users for search query were found");
        } else out.println("You don't have permissions");
    }

    private void searchStudents() {
        if (currentUser.getUserRole() != UserRoles.STUDENT) {
            String query = printAndReadOutput("Please enter student's name or id or email:");
            List<Student> foundStudents = users.searchStudent(query);
            if (!foundStudents.isEmpty()) {
                Utils.print(foundStudents);
            } else out.println("No students for search query were found");
        } else out.println("You don't have permissions");
    }

    private void login(){
        String userNameInput = printAndReadOutput("Please enter your username:");
        String passwordInput = printAndReadOutput("Please enter your password:");
        if (users.login(userNameInput, passwordInput)) {
            users.getUserByUserName(userNameInput).ifPresent(user -> currentUser = user);
            currentUser.getUserRole().printModulesNavigationOptions();
        }
    }
}
