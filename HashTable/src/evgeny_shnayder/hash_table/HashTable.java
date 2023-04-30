package evgeny_shnayder.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private List<T>[] table;

    private int count;

    private int modCount;

    @SuppressWarnings("unchecked")
    public HashTable() {
        int capacity = 10;
        table = (List<T>[]) new ArrayList[capacity];
    }

    public HashTable(int capacity) {
        //noinspection unchecked
        table = (List<T>[]) new ArrayList[capacity];
    }

    public HashTable(ArrayList<T>[] table) {
        this.table = table;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        for (List<T> element : table) {
            if (!Objects.equals(element, null)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(Object object) {
        int hash = Math.abs(object.hashCode() % table.length);

        if (Objects.equals(table[hash], null)) {
            return false;
        }

        return table[hash].contains(object);
    }

    private class HashTableIterator implements Iterator<T> {
        private int currentArrayIndex = -1;

        private int currentListIndex = -1;

        private final int expectedModCount;

        public HashTableIterator() {
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return currentArrayIndex + 1 < table.length;
        }

        @Override
        public T next() {
            if (currentArrayIndex == table.length) {
                throw new NoSuchElementException("Элементы коллекции закончились.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода.");
            }

            ++currentArrayIndex;

            if (Objects.equals(table[currentArrayIndex], null)) {
                currentListIndex = -1;

                return null;
            }

            ++currentListIndex;

            T data = table[currentArrayIndex].get(currentListIndex);

            if (currentListIndex == table[currentArrayIndex].size() - 1) {
                currentListIndex = -1;
            } else {
                currentArrayIndex--;
            }

            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[count];

        int i = 0;

        for (List<T> element : table) {
            if (Objects.equals(element, null)) {
                continue;
            }

            System.arraycopy(element.toArray(), 0, array, i, element.size());
            i = i + element.size();
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (count >= array.length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), count, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(Arrays.copyOf(toArray(), count, array.getClass()), 0, array, 0, count);
        array[count] = null;

        return array;
    }

    @Override
    public boolean add(T element) {
        int hash = Math.abs(element.hashCode() % table.length);

        if (Objects.equals(table[hash], null)) {
            table[hash] = new ArrayList<>();
            table[hash].add(element);
            count++;
            modCount++;

            return true;
        }

        count++;
        modCount++;

        return table[hash].add(element);
    }

    @Override
    public boolean remove(Object object) {
        int hash = Math.abs(object.hashCode() % table.length);

        if (Objects.equals(table[hash], null)) {
            return false;
        }

        if (table[hash].remove(object)) {
            if (table[hash].size() == 0) {
                table[hash] = null;
            }

            count--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (Object element : collection) {
            int hash = Math.abs(element.hashCode() % table.length);

            if (Objects.equals(table[hash], null) || !table[hash].contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (T element : collection) {
            if (!add(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (Object element : collection) {
            int hash = Math.abs(element.hashCode() % table.length);

            if (Objects.equals(table[hash], null)) {
                continue;
            }

//            table[hash] = table[hash].stream().filter(x -> !Objects.equals(x, element)).collect(Collectors.toList());

            while (table[hash].remove(element)) {
                isRemoved = true;
            }

            if (table[hash].isEmpty()) {
                table[hash] = null;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isRemoved = false;

        for (int i = 0; i < table.length; i++) {
            if (!Objects.equals(table[i], null)) {
                if (table[i].retainAll(collection)) {
                    isRemoved = true;
                }

                if (table[i].isEmpty()) {
                    table[i] = null;
                }
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            if (!Objects.equals(table[i], null)) {
                table[i].clear();
                table[i] = null;
            }
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (List<T> element : table) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
