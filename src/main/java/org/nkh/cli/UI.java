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

    public void runUI(UIActions uiActions) {
        out.println("Student Management System");
        printSystemAccessOptions();
        String command = sc.nextLine();
        while (!command.equals("exit")) {
            MenuOptions option = MenuOptions.getModuleById(command, uiActions);
            if(option != null) {
                option.implementAction(uiActions);
            }
            command = uiActions.printAndReadOutput("Enter the command, you would like to implement: ");
        }
        sc.close();
    }

    public void printSystemAccessOptions() {
        out.println("Please select:");
        out.println("1 - login");
        out.println("2 - registration");
        out.println("exit - exit the application");
    }
}
