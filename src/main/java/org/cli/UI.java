package org.cli;

import org.users_management.User;
import org.users_management.UserRoles;
import org.users_management.Users;
import org.utils.Utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class UI {
    Scanner sc = new Scanner(in);
    User currentUser;

    public void runUI(Users users){
        out.println("Student Management System");
        printSystemAccessOptions();
        out.println("Please login to continue");

        String command = sc.nextLine();

        while (!command.equals("exit")){
            readAndImplementCommand(command, users);
            command = printAndReadOutput("Enter the command, you would like to implement: ");
        }

    }

    public void readAndImplementCommand(String command, Users users){
        switch (command) {
            case "1":
                String userNameInput = printAndReadOutput("Please enter your username:");
                String passwordInput = printAndReadOutput("Please enter your password:");
                if(users.login(userNameInput, passwordInput)){
                    users.getUserByUserName(userNameInput).ifPresent(user -> currentUser=user);
                    currentUser.getUserRole().printModulesNavigationOptions();
                }
                break;
            case "2":
                String name = printAndReadOutput("Please enter your name:");
                String email = printAndReadOutput("Please enter your email:");
                String newUserName = printAndReadOutput("Please enter your username:");
                String newPassword = printAndReadOutput("Please enter your password:");
                String role = printAndReadOutput("Please enter your role teacher/student:");
                    try {
                    UserRoles userRole = UserRoles.getRole(role);
                    if(users.registerNewUser(name,email,newUserName,newPassword, userRole)){
                        users.getUserByUserName(newUserName).ifPresent(user -> currentUser=user);
                        currentUser.getUserRole().printModulesNavigationOptions();
                    }
                } catch (Exception e) {
                    out.println("Invalid role entered: " + role);
                }
                break;
            case "3":
                currentUser.getUserRole().printModulesNavigationOptions();
                break;
            case "4":
                printStudentsCommandOptions();
                break;
            case "6":
                name = printAndReadOutput("Enter user name:");
                email = printAndReadOutput("Enter user email:");
                newUserName = printAndReadOutput("Enter user username:");
                newPassword = printAndReadOutput("Enter user password:");
                role = printAndReadOutput("Enter user role teacher/student/admin:");
                try {
                    UserRoles userRole = UserRoles.getRole(role);
                    if(users.registerNewUser(name,email,newUserName,newPassword, userRole)){
                        users.getUserByUserName(newUserName).ifPresent(user -> currentUser=user);
                        currentUser.getUserRole().printModulesNavigationOptions();
                    }
                } catch (Exception e) {
                    out.println("Invalid role entered: " + role);
                }
                break;
            case "7":
                String query = printAndReadOutput("Please enter user's name or id or email:");
                List<User> foundUsers = users.searchUser(query);
                if(!foundUsers.isEmpty()){
                    Utils.print(foundUsers);
                }
                break;
            case "8":

                break;
            case "9":

                break;
            case "10":

                break;
            case "11":

                break;
            case "12":

                break;
            case "13":

                break;
            case "exit":
                break;
            default:
                out.println("Command is not defined.");
                printCommandOptions();
        }
    }

    public String printAndReadOutput(String message){
        out.println(message);
        String userInput = sc.nextLine();;
        return userInput;
    }

    public void printCommandOptions(){
        out.println("Select command you would like to implement from following:");
        out.println("1 - enter/modify your first name");
        out.println("2 - enter/modify your last name");
        out.println("3 - enter/modify your age");
        out.println("4 - enter/modify your gender");
        out.println("exit - exit the application");
    }

    public void printStudentsCommandOptions(){
        out.println("Select command you would like to implement from following:");
        out.println("10 - add new student");
        out.println("11 - find student");
        out.println("12 - edit student");
        out.println("13 - delete student");
        out.println("exit - exit the application");
    }

    public void printUsersCommandOptions(){
        out.println("Select command you would like to implement from following:");
        out.println("6 - add new user");
        out.println("7 - find user");
        out.println("8 - edit user");
        out.println("9 - delete user");
        out.println("exit - exit the application");
    }

    public void printSystemAccessOptions(){
        out.println("Please select:");
        out.println("1 - login");
        out.println("2 - registration");
        out.println("exit - exit the application");
    }
}
