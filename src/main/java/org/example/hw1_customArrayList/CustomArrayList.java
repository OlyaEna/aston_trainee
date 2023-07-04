package org.example.hw1_customArrayList;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CustomArrayList<E extends Comparable> implements CustomList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;
    private int size;

    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("illegal size:" + initialCapacity);
        }
        this.elements = (E[]) new Comparable[initialCapacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }


    @Override
    public boolean add(E element) {
        if (size == elements.length)
            ensureCapacity();
        elements[this.size++] = element;
        return true;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= (this.size + 1)) {
            throw new IllegalArgumentException("illegal index:" + index);
        }
    }

    @Override
    public void add(int index, E element) {
        checkRange(index);
        ensureCapacity();
        System.arraycopy(this.elements, index, elements, index + 1, size - index);
        elements[index] = element;
        this.size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacity();
        System.arraycopy(a, 0, elements, size, numNew);
        size += numNew;
        return true;
    }


    @Override
    public void remove(E element) {
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elements[i] == null) {
                    remove(i);
                    return;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.elements[i])) {
                    remove(i);
                    return;
                }
            }
        }
    }

    public void remove(int index) {
        int movedNumber = this.size - index - 1;
        if (movedNumber > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, movedNumber);
        }
        this.elements[--this.size] = null;
    }

    @Override
    public E get(int index) {
        return (E) this.elements[index];
    }

    @Override
    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.elements.length; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void set(int index, E element) {
        checkRange(index);
        ensureCapacity();
        this.elements[index] = element;
    }


    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }


    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    private void quickSort(Comparable[] array, int low, int high) {
        if (array.length == 0) return;
        if (low >= high) return;

        int middle = low + (high - low) / 2;
        Comparable middleComp = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i].compareTo(middleComp) < 0) {
                i++;
            }
            while (array[j].compareTo(middleComp) > 0) {
                j--;
            }
            if (i <= j) {
                Comparable<E> temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    public void sort() {
        quickSort( elements, 0, size - 1);
    }


    private class CustomIterator<E> implements Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < size();
        }

        @Override
        public E next() {
            E value = (E) elements[current++];
            return value;
        }
    }
}