package evgeny_shnayder.array_list;

import java.util.*;


public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость " + initialCapacity + " не должна быть меньше нуля");
        }

        //noinspection unchecked
        elements = (E[]) new Object[initialCapacity];
    }

    public int getSize() {
        return size;
    }

    private void increaseCapacity() {
        if (elements.length == 0) {
            //noinspection unchecked
            elements = (E[]) new Object[DEFAULT_CAPACITY];
            return;
        }

        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за границы списка от 0 до " + (size - 1)
                    + " включительно.");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за границы списка от 0 до " + size + " включительно.");
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
        return indexOf(object) != -1;
    }

    private class MyListIterator implements Iterator<E> {
        private final int expectedModCount = modCount;
        private int currentIndex = -1;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементы коллекции закончились.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода.");
            }

            ++currentIndex;

            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (size > array.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, array, 0, size);

        if (size < array.length) {
            array[size] = null;
        }

        return array;
    }

    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index != -1) {
            remove(index);

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
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        checkIndexForAdd(index);

        int minCapacity = size + collection.size();

        ensureCapacity(minCapacity);

        if (index != size) {
            System.arraycopy(elements, index, elements, index + collection.size(), size - index);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(collection.toArray(), 0, elements, index, collection.size());

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (Object element : collection) {
            while (remove(element)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isRemoved = false;

        for (int i = 0; i < size; ) {
            if (!collection.contains(elements[i])) {
                remove(i);
                isRemoved = true;
            } else {
                i++;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(elements, null);

        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    public void add(int index, E element) {
        checkIndexForAdd(index);

        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        modCount++;
        size--;

        return removedElement;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<E> subList(int i, int i1) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
