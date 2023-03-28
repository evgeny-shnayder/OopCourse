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
        head = new ListItem<>(list.head.getData());
        this.count = list.count;

        for (ListItem<T> copyNode = head, node = list.head.getNext(); node != null;
             node = node.getNext(), copyNode = copyNode.getNext()) {
            copyNode.setNext(new ListItem<>(node.getData()));
        }
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        checkSizeList();

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Заданный индекс " + index + " выходит за размер списка" + " от 0 до "
                    + (count - 1) + " включительно.");
        }

    }

    private void checkSizeList() {
        if (count == 0) {
            throw new NoSuchElementException("В списке отсутствуют элементы.");
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> currentNode = head;
        int i = 0;

        while (i < index) {
            currentNode = currentNode.getNext();
            i++;
        }

        return currentNode;
    }

    public T getByIndex(int index) {
        checkIndex(index);

        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index);

        ListItem<T> node = getItemByIndex(index);
        T oldData = node.getData();
        node.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        T deletedData = getItemByIndex(index).getData();

        if (index == 0) {
            removeFirst();

            return deletedData;
        }

        int i = 1;

        for (ListItem<T> currentNode = head.getNext(), prevNode = head; currentNode != null;
             prevNode = currentNode, currentNode = currentNode.getNext()) {
            if (i == index) {
                prevNode.setNext(currentNode.getNext());
                count--;

                break;
            }

            i++;
        }

        return deletedData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(int index, T data) {
        checkIndex(index);

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> currentNode = head;
            int i = index;

            while (i > 0) {
                currentNode = currentNode.getNext();
                i--;
            }

            currentNode.setNext(new ListItem<>(data, currentNode.getNext()));
            count++;
        }
    }

    public boolean removeByData(T data) {
        if (head.getData() == data) {
            removeFirst();

            return true;
        }

        for (ListItem<T> currentNode = head.getNext(), prevNode = head; currentNode != null;
             prevNode = currentNode, currentNode = currentNode.getNext()) {
            if (currentNode.getData() == data) {
                prevNode.setNext(currentNode.getNext());
                count--;

                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        checkSizeList();

        T deletedData = head.getData();

        head = head.getNext();
        count--;

        return deletedData;
    }

    public void reverse() {
        checkSizeList();

        ListItem<T> prevItem = null;
        ListItem<T> currentItem = head;
        ListItem<T> nextItem = head.getNext();

        while (nextItem != null) {
            currentItem.setNext(prevItem);
            prevItem = currentItem;
            currentItem = nextItem;
            nextItem = currentItem.getNext();
        }

        currentItem.setNext(prevItem);
        head = currentItem;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        ListItem<T> node = head;

        stringBuilder.append('[');

        while (node != null) {
            stringBuilder.append(node.getData()).append(", ");
            node = node.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
