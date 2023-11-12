package org.nkh.cli;

import org.nkh.users_management.UserRoles;

import java.util.List;

public enum MenuOptions {
    LOGIN("1"){
        @Override
        void implementAction(UIActions uiActions) {
            uiActions.login();
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN,UserRoles.TEACHER, UserRoles.STUDENT);
        }
    },
    REGISTER("2"){
        @Override
        void implementAction(UIActions uiActions) {
            uiActions.register();
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN,UserRoles.TEACHER, UserRoles.STUDENT);
        }
    },
    USER_MANAGEMENT("3") {
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.printUsersCommandOptions();
            }else System.out.println("You don't have permissions for this action");
        }
        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN);
        }
    },
    STUDENT_MANAGEMENT("4") {
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.printStudentsCommandOptions();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN,UserRoles.TEACHER);
        }
    },
    COURSE_MANAGEMENT("5") {
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.printCoursesCommandOptions();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN);
        }
    },
    MY_PROFILE("6"){
        @Override
        void implementAction(UIActions uiActions) {

        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN,UserRoles.TEACHER, UserRoles.STUDENT);
        }
    },
    ADD_USER("7"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.addUser();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN);
        }
    },
    FIND_USER("8"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.searchUsers();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN);
        }
    },
    EDIT_USER("9"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.updateUser();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN);
        }
    },
    DELETE_USER("10"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.deleteUser();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN);
        }
    },
    ADD_STUDENT("11"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.addStudent();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN, UserRoles.TEACHER);
        }
    },
    FIND_STUDENT("12"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.searchStudents();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN, UserRoles.TEACHER);
        }
    },
    EDIT_STUDENT("13"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.updateStudent();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN,UserRoles.TEACHER);
        }
    },
    DELETE_STUDENT("14"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.deleteStudent();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN, UserRoles.TEACHER);
        }
    },
    UPDATE_GRADES("15"){
        @Override
        void implementAction(UIActions uiActions) {
            if(this.getUserRoles().contains(uiActions.getCurrentUserRole())){
                uiActions.updateGrade();
            }System.out.println("You don't have permissions for this action");
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.TEACHER);
        }
    },
    EXIT("exit") {
        @Override
        void implementAction(UIActions uiActions) {
        }

        @Override
        List<UserRoles> getUserRoles() {
            return List.of(UserRoles.ADMIN,UserRoles.TEACHER, UserRoles.STUDENT);
        }
    };
    String value;

    MenuOptions(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static MenuOptions getModuleById(String option, UIActions uiActions){
        for (MenuOptions menuOption : MenuOptions.values()) {
            if (menuOption.getValue().equals(option)) {
                return menuOption;
            }
        }
        return null;
    }

    abstract void implementAction(UIActions uiActions);
    abstract List<UserRoles> getUserRoles();
}
