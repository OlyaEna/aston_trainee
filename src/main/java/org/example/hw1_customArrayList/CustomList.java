package org.example.hw1_customArrayList;

import java.util.Collection;

public interface CustomList<E> extends Iterable<E> {

    boolean isEmpty();

    int size();

    boolean add(E element);

    void add(int index, E element);

    boolean addAll(Collection<? extends E> c);

    void remove(E element);

    void remove(int index);

    E get(int index);

    int indexOf(E element);

    void set(int index, E element);

    void clear();
     void sort();
}
