package org.nkh.utils.sorting;

import java.util.Comparator;
import java.util.List;

public class SortList {
    public static List<?> sortById(List<? extends SortableById> objects){
        return objects.stream().sorted(Comparator.comparing(SortableById::getId)).toList();
    }

    public static List<?> sortByName(List<? extends SortableByName> objects){
        return objects.stream().sorted(Comparator.comparing(SortableByName::getName)).toList();
    }

    public static List<?> sortByTitle(List<? extends SortableByTitle> objects){
        return objects.stream().sorted(Comparator.comparing(SortableByTitle::getTitle)).toList();
    }
}
