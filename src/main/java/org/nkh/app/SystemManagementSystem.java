package org.nkh.app;

import org.nkh.authentication.Authentication;
import org.nkh.authentication.Session;
import org.nkh.cli.UI;
import org.nkh.cli.UIActions;
import org.nkh.courses_management.CoursesManagement;
import org.nkh.users_management.UserRoles;
import org.nkh.users_management.Users;

public class SystemManagementSystem implements Runnable{
    private Users usersManagement;
    private CoursesManagement coursesManagement;
    private Session session;
    private Authentication authentication;
    private UI ui;
    private UIActions uiActions;
    public void init(){
        usersManagement = new Users();
        usersManagement.addNewUser("Natallia", "test@gmail.com", "admin","12345!", UserRoles.ADMIN);
        authentication = new Authentication(usersManagement);
        coursesManagement = new CoursesManagement(usersManagement);
        uiActions = new UIActions(usersManagement, authentication);
        ui = new UI();
    }

    public void run() {
        init();
        ui.runUI(uiActions);
    }
}
