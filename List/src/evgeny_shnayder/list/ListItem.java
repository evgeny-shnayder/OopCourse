package evgeny_shnayder.list;

class ListItem<T> {
    private T data;
    private ListItem<T> next;

    protected ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public ListItem(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListItem<T> getNext() {
        return next;
    }

    public void setNext(ListItem<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        ListItem<T> item = (ListItem<T>) object;

        if (item.data == null || data == null) {
            return item.data == data;
        }

        return item.data.equals(data);
    }
}
