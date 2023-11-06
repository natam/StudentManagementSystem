package org.utils;

import java.util.List;

public class Utils {
    public static <T> void print(List<T> objectList){
        int counter = 0;
        for (T obj: objectList){
            System.out.println(counter + ". "+ obj.toString());
            counter++;
        }
    }

    public static <T> void print(T object){
        System.out.println(object.toString());
    }
}
