package evgeny_shnayder.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private int count;
    private ListItem<T> head;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        count++;
    }

    public SinglyLinkedList(SinglyLinkedList<T> list) {
        if (list.count > 0) {
            head = new ListItem<>(list.head.getData());
            count = list.count;

            for (ListItem<T> copyItem = head, item = list.head.getNext(); item != null;
                 item = item.getNext(), copyItem = copyItem.getNext()) {
                copyItem.setNext(new ListItem<>(item.getData()));
            }
        }
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        checkListToEmpty();

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Заданный индекс " + index + " выходит за размер списка от 0 до "
                    + (count - 1) + " включительно.");
        }
    }

    private void checkListToEmpty() {
        if (count == 0) {
            throw new NoSuchElementException("В списке отсутствуют элементы.");
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }

        return currentItem;
    }

    public T getByIndex(int index) {
        checkIndex(index);

        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);
        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> removedItem = getItemByIndex(index);

        getItemByIndex(index - 1).setNext(removedItem.getNext());
        count--;

        return removedItem.getData();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Заданный индекс " + index + " больше размера списка " + count + ".");
        }

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> previousItem = getItemByIndex(index - 1);
            previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
            count++;
        }
    }

    public boolean removeByData(T data) {
        if (count == 0) {
            return false;
        }

        ListItem<T> dataItem = new ListItem<>(data);

        if (head.equals(dataItem)) {
            removeFirst();

            return true;
        }

        for (ListItem<T> currentItem = head.getNext(), previousItem = head; currentItem != null;
             previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentItem.equals(dataItem)) {
                previousItem.setNext(currentItem.getNext());
                count--;

                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        checkListToEmpty();

        T removedData = head.getData();

        head = head.getNext();
        count--;

        return removedData;
    }

    public void reverse() {
        if (count > 1) {
            ListItem<T> previousItem = null;
            ListItem<T> currentItem = head;
            ListItem<T> nextItem = head.getNext();

            while (nextItem != null) {
                currentItem.setNext(previousItem);
                previousItem = currentItem;
                currentItem = nextItem;
                nextItem = currentItem.getNext();
            }

            currentItem.setNext(previousItem);
            head = currentItem;
        }
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        ListItem<T> item = head;

        stringBuilder.append('[');

        while (item != null) {
            stringBuilder.append(item.getData()).append(", ");
            item = item.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
