package org.nkh.users_management;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.nkh.courses_management.Course;
import org.nkh.students_management.Student;
import org.nkh.teachers_management.Teacher;

import java.util.*;

public class Users {
    private List<User> users;

    private Map<String, String> credentials;

    public Users() {
        users = new ArrayList<>();
        credentials = new HashMap<>();
    }

    public boolean addNewUser(String name, String email, String userName, String password, UserRoles role) {
        if (checkIfUserNameExist(userName)) {
            System.out.println("User with such userName already exists.");
            return false;
        } else if (!searchUser(name).isEmpty()) {
            System.out.println("User with such name already exists.");
            return false;
        } else {
            User newUser = null;
            if (role == UserRoles.STUDENT) {
                newUser = new Student(name, email);
            } else if (role == UserRoles.ADMIN) {
                newUser = new Admin(name, email);
            } else if (role == UserRoles.TEACHER) {
                newUser = new Teacher(name, email);
            }
            if (newUser != null) {
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<User>> violations = validator.validate(newUser);
                if (!violations.isEmpty()) {
                    System.out.println("User can not be created.");
                    for (ConstraintViolation<User> violation : violations) {
                        System.out.println(violation.getMessage());
                    }
                    return false;
                } else {
                    newUser.setUserName(userName);
                    newUser.setPassword(password);
                    credentials.put(userName, password);
                    users.add(newUser);
                    return true;
                }
            } else return false;
        }
    }

    public boolean checkIfUserNameExist(String userName) {
        return credentials.containsKey(userName);
    }

    public Optional<User> getUserByUserName(String userName) {
        return users.stream().filter(user -> user.getUserName().equals(userName)).findFirst();
    }

    public Optional<User> getUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public List<User> searchUser(String query) {
        return users.stream().filter(user -> (user.getName().contains(query) || user.getEmail().contains(query) || String.valueOf(user.getId()).equals(query))).toList();
    }

    public void deleteUser(String userId) {
        Optional<User> userToDelete = getUserById(userId);
        userToDelete.ifPresent(user ->
        {
            if (!user.getUserName().isEmpty() && user.getUserName() != null) {
                credentials.remove(user.getUserName());
            }
        });
        userToDelete.ifPresent(user -> users.remove(user));
    }

    public void updateUser(String userId, String newName, String newEmail, String newPassword, String newUsername) {
        Optional<User> userToUpdate = getUserById(userId);
        if (userToUpdate.isPresent()) {
            if (!newName.isEmpty()) {
                userToUpdate.get().setName(newName);
            }
            if (!newEmail.isEmpty()) {
                userToUpdate.get().setEmail(newEmail);
            }
            if (!newUsername.isEmpty()) {
                userToUpdate.get().setUserName(newUsername);
            }
            if (!newPassword.isEmpty()) {
                userToUpdate.get().setPassword(newPassword);
            }
        } else {
            System.out.println("User not found");
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Student> getStudents() {
        return users.stream().filter(user -> user.getUserRole() == UserRoles.STUDENT).map((user -> (Student) user)).toList();
    }

    public List<Student> getStudentsByCourse(String courseTitle) {
        return getStudents().stream().filter(student -> student.findCourse(courseTitle) != null).toList();
    }

    public List<Student> searchStudent(String query) {
        return getStudents().stream().filter(user -> (user.getName().contains(query) || user.getEmail().contains(query) || String.valueOf(user.getId()).equals(query))).toList();
    }

    public void addOrUpdateGrade(String courseTitle, String studentName, double grade) {
        Optional<Student> foundStudent = getStudentsByCourse(courseTitle).stream().filter(student -> student.getName().equals(studentName)).findFirst();
        if (foundStudent.isPresent()) {
            foundStudent.get().findCourse(courseTitle).setGrade(grade);
        } else {
            System.out.println("Student " + studentName + " doesn't take the course " + courseTitle);
        }
    }

    public Set<String> getAllCourses() {
        Set<String> courses = new HashSet<>();
        getStudents()
                .stream()
                .map(student -> student.getTakenCourses().stream().map(Course::getTitle).toList())
                .forEach(courses::addAll);
        return courses;
    }

    public List<Teacher> getTeachers(){
        return users.stream().filter(user -> user.getUserRole() == UserRoles.TEACHER).map((user -> (Teacher) user)).toList();
    }

    public Optional<Teacher> getTeacherById(String id) {
        return getTeachers().stream().filter(user -> (user.getId().equals(id))).findFirst();
    }
}
