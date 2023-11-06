package org.nkh.export_data;

import org.nkh.students_management.Student;
import org.nkh.users_management.User;

import java.io.FileWriter;
import java.io.IOException;

public class Export {
    public static final String USER_OUT_FILE = "src/main/resources/user.txt";
    public static final String STUDENT_OUT_FILE = "src/main/resources/user.txt";

    public void exportTXT(Object object){
        String file =getOutputFile(object.getClass());
        if (!file.isEmpty()){
            try(FileWriter fileWriter = new FileWriter(file)){
                fileWriter.write(object.toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public String getOutputFile(Class<?> cls){
        if(cls == User.class){
            return USER_OUT_FILE;
        }else if(cls == Student.class){
            return STUDENT_OUT_FILE;
        }else return "";
    }
}
