package org.utils;

import java.util.List;

public class Utils {
    public void print(List<Object> objectList){
        int counter = 0;
        for (Object obj: objectList){
            System.out.println(counter + ". "+ obj.toString());
            counter++;
        }
    }
}
