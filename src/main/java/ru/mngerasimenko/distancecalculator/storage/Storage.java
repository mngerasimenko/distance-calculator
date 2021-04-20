package ru.mngerasimenko.distancecalculator.storage;

import java.util.*;

public abstract class Storage<E> {

    protected List<E> storageList;

    public void addItem(E item) {
        if (storageList == null) storageList = new ArrayList<E>();
        storageList.add(item);
    }

    public List<E> getAllItem() {
        return storageList;
    }
}
