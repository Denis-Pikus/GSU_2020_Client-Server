package by.gsu.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LocalContainer<T> implements Container<T> {

    private List<T> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public void set(int index, T element) {
        list.set(index, element);
    }

    @Override
    public void delete(int index) {
        list.remove(index);
    }

    @Override
    public Collection<T> getAll() {
        return list;
    }
}
