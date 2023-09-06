package evgeny_shnayder.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final List<E>[] lists;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public HashTable() {
        lists = (List<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Значение вместимости " + capacity + " должно быть больше нуля.");
        }

        //noinspection unchecked
        lists = (List<E>[]) new ArrayList[capacity];
    }

    public HashTable(Collection<? extends E> collection) {
        //noinspection unchecked
        lists = (List<E>[]) new ArrayList[DEFAULT_CAPACITY];

        for (E element : collection) {
            int index = getIndex(element);

            if (lists[index] == null) {
                lists[index] = new ArrayList<>();
            }

            lists[index].add(element);
            size++;
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

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
    }

    @Override
    public boolean contains(Object object) {
        int index = getIndex(object);

        return lists[index] != null && lists[index].contains(object);
    }

    private class HashTableIterator implements Iterator<E> {
        private int visitedElementsCount;
        private int currentArrayIndex;
        private int currentListIndex = -1;

        private final int expectedModCount;

        public HashTableIterator() {
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return visitedElementsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементы коллекции закончились.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода.");
            }

            while (lists[currentArrayIndex] == null || lists[currentArrayIndex].isEmpty()) {
                currentArrayIndex++;
            }

            ++currentListIndex;

            E element = lists[currentArrayIndex].get(currentListIndex);

            if (currentListIndex == lists[currentArrayIndex].size() - 1) {
                currentListIndex = -1;
                currentArrayIndex++;
            }

            visitedElementsCount++;

            return element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (List<E> list : lists) {
            if (list == null) {
                continue;
            }

            for (E element : list) {
                array[i] = element;
                i++;
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (size > array.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        size++;
        modCount++;

        return lists[index].add(element);
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(object)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (E element : collection) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isRemoved = false;

        for (List<E> list : lists) {
            if (list != null) {
                int oldSize = list.size();

                if (list.removeAll(collection)) {
                    isRemoved = true;
                    size -= oldSize - list.size();
                    modCount++;
                }
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isRemoved = false;

        for (List<E> list : lists) {
            if (list != null) {
                int oldSize = list.size();

                if (list.retainAll(collection)) {
                    isRemoved = true;
                    size -= oldSize - list.size();
                    modCount++;
                }
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(lists, null);

        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (List<E> list : lists) {
            stringBuilder.append(list).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
