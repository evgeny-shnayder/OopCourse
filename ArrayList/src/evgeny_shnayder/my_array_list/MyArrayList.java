package evgeny_shnayder.my_array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private int capacity = 10;
    private T[] elements;
    private int size;
    private int modCount;

    public MyArrayList() {
        elements = (T[]) new Object[capacity];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размер начальной вместимости " + initialCapacity + " не должен быть меньше нуля.");
        }

        this.capacity = initialCapacity;
        elements = (T[]) new Object[initialCapacity];
    }

    public int getSize() {
        return size;
    }

    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public void ensureCapacity(int minSize) {
        if (minSize < size) {
            elements = Arrays.copyOf(elements, minSize + 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + "выходит за границы списка от 0 до " + (size - 1)
                    + " включительно.");
        }
    }

    private void checkIndexBeforeAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + "выходит за границы списка от 0 до " + size + " включительно.");
        }
    }

    private void checkSizeOfCollection(Collection<?> collection) {
        if (collection.size() == 0) {
            throw new NullPointerException("Указанная коллекция пустая.");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        for (Object element : elements) {
            if (object.equals(element)) {
                return true;
            }
        }

        return false;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        int expectedModCount;

        MyListIterator() {
            expectedModCount = modCount;
        }

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            ++currentIndex;

            if (currentIndex >= size) {
                throw new NoSuchElementException("Элементы коллекции закончились.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода.");
            }

            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        Iterator<T> iterator = this.iterator();

        for (int i = 0; iterator.hasNext(); i++) {
            array[i] = iterator.next();
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (size <= array.length) {
            System.arraycopy(elements, 0, array, 0, size);
            array[size] = null;
            return array;
        }

        return (T1[]) Arrays.copyOf(elements, size);
    }

    public boolean add(T data) {
        if (size == elements.length - 1) {
            increaseCapacity();
        }

        elements[size] = data;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int removedElementIndex = indexOf(object);

        if (removedElementIndex != -1) {
            remove(removedElementIndex);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        boolean result = false;
        for (Object element : collection) {
            if (contains(element)) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        checkSizeOfCollection(collection);

        for (T element : collection) {
            if (capacity <= size) {
                increaseCapacity();
            }

            add(element);
            modCount++;
        }

        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        checkSizeOfCollection(collection);
        checkIndexBeforeAdd(i);

        Iterator<? extends T> iterator = collection.iterator();

        for (int j = i; iterator.hasNext(); j++) {
            if (capacity <= size) {
                increaseCapacity();
            }

            add(j, iterator.next());
            modCount++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        checkSizeOfCollection(collection);
        boolean result = false;

        for (Object element : collection) {
            while (indexOf(element) != -1) {
                remove(element);
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        checkSizeOfCollection(collection);

        if (!containsAll(collection)) {
            clear();
            return false;
        }

        for (int i = 0; i < size; ) {
            if (!collection.contains(elements[i])) {
                remove(i);
            } else {
                i++;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public T get(int i) {
        checkIndex(i);

        return elements[i];
    }

    @Override
    public T set(int i, T t) {
        checkIndex(i);

        T changedElement = elements[i];
        elements[i] = t;

        return changedElement;
    }

    public void add(int index, T data) {
        checkIndexBeforeAdd(index);

        T[] arrayData = elements;

        System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        arrayData[index] = data;
        elements = Arrays.copyOf(arrayData, arrayData.length);
        size++;
        modCount++;
    }

    @Override
    public T remove(int i) {
        checkIndex(i);

        T removedElement = elements[i];

        System.arraycopy(elements, i + 1, elements, i, size - i);
        modCount++;
        size--;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (size == 0) {
            return "[]";
        }

        result.append("[");

        for (int i = 0; /*i < array.length*/ elements[i] != null; i++) {
            result.append(elements[i]).append(", ");
        }

        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());
        }

        result.append("]");

        return result.toString();
    }
}
