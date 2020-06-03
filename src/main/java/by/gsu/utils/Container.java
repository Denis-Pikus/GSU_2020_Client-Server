package by.gsu.utils;

import java.util.Collection;

public interface Container<T> {

    int size();

    void add(T element);

    void set(int index, T element);

    void delete(int index);

    Collection<T> getAll();

}
