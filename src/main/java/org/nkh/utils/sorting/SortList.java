package org.nkh.utils.sorting;

import java.util.Comparator;
import java.util.List;

public class SortList {
    public List<SortableById> sortById(List<SortableById> objects){
        return objects.stream().sorted(Comparator.comparing(SortableById::getId)).toList();
    }

    public List<SortableByName> sortByName(List<SortableByName> objects){
        return objects.stream().sorted(Comparator.comparing(SortableByName::getName)).toList();
    }

    public List<SortableByTitle> sortByTitle(List<SortableByTitle> objects){
        return objects.stream().sorted(Comparator.comparing(SortableByTitle::getTitle)).toList();
    }
}
