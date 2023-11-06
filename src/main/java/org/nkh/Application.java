package org.nkh;

import org.nkh.cli.UI;
import org.nkh.users_management.UserRoles;
import org.nkh.users_management.Users;

public class Application {
    public static void main(String[] args) {
        Users users = new Users();
        users.registerNewUser("Natallia", "test@gmail.com", "admin","12345!", UserRoles.ADMIN);
        UI myUI = new UI(users);
        myUI.runUI();
    }
}
