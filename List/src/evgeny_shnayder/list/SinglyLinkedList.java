package evgeny_shnayder.list;

public class SinglyLinkedList<T> {
    private int count;
    private ListItem<T> head;

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        count++;
    }

    public SinglyLinkedList(SinglyLinkedList<T> list) {
        this.head = list.head;
        this.count = list.count;

        for (ListItem<T> node = head; node != null; node = node.getNext()) {
            ListItem<T> newNode = new ListItem<>(node.getData());
            newNode.setNext(node.getNext());
        }
    }

    public int getCount() {
        return count;
    }

    public T getFirstValue() {
        return head.getData();
    }

    private ListItem<T> getOnIndexItem(int index) {
        if (index <= 0 || index > count) {
            throw new IllegalArgumentException("Указанное значение " + index + " больше длинны списка или меньше нуля.");
        }

        ListItem<T> currentNode = head;
        int nodeCount = 1;

        while (nodeCount < index) {
            currentNode = currentNode.getNext();
            nodeCount++;
        }

        return currentNode;
    }

    public T getOnIndexValue(int index) {
        return getOnIndexItem(index).getData();
    }

    public T setValueOnIndex(int index, T data) {
        ListItem<T> neededNode = getOnIndexItem(index);
        T lastValue = neededNode.getData();
        neededNode.setData(data);

        return lastValue;
    }

    public T removeItem(int index) {
        T lastValue = getOnIndexItem(index).getData();

        if (index == 1) {
            head = head.getNext();
            count--;

            return lastValue;
        }

        if (index == count) {
            getOnIndexItem(index - 1).setNext(null);
            count--;

            return lastValue;
        }

        getOnIndexItem(index - 1).setNext(getOnIndexItem(index + 1));
        count--;

        return lastValue;
    }

    public void addBeforeItems(T data) {
        ListItem<T> newNode = new ListItem<>(data);
        newNode.setNext(head);
        head = newNode;
        count++;
    }

    public void addItem(int index, T data) {
        if (index <= 0 || index > count + 1) {
            throw new IllegalArgumentException("Указанное значение " + index + " больше длинны списка или меньше нуля.");
        }

        if (index == 1) {
            addBeforeItems(data);
        } else if (index > count) {
            getOnIndexItem(index - 1).setNext(new ListItem<>(data));
            count++;
        } else {
            ListItem<T> lastItem = getOnIndexItem(index);
            getOnIndexItem(index - 1).setNext(new ListItem<>(data, lastItem));
            count++;
        }
    }

    public boolean removeByValueItem(T data) {
        int count = 1;

        for (ListItem<T> node = head; node != null; node = node.getNext()) {
            if (node.getData().equals(data)) {
                removeItem(count);
                this.count--;

                return true;
            }

            count++;
        }

        return false;
    }

    public T removeFirstItem() {
        T lastValue = head.getData();

        head = head.getNext();
        count--;

        return lastValue;
    }

    public void revers() {
        ListItem<T> lastItem = null;
        ListItem<T> currentItem = head;
        ListItem<T> nextItem = head.getNext();

        while (nextItem != null) {
            currentItem.setNext(lastItem);
            lastItem = currentItem;
            currentItem = nextItem;
            nextItem = currentItem.getNext();
        }

        currentItem.setNext(lastItem);
        head = currentItem;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        ListItem<T> node = head;

        while (node != null) {
            result.append(node.getData()).append(" ");
            node = node.getNext();
        }

        return result.toString();
    }
}
