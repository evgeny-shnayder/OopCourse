package evgeny_shnayder.list;

public class ListItem<T> {
    private T data;
    private ListItem<T> next;

    public T getData() {
        return data;
    }

    protected void setData(T data) {
        this.data = data;
    }

    public ListItem<T> getNext() {
        return next;
    }

    protected void setNext(ListItem<T> next) {
        this.next = next;
    }

    protected ListItem(T data, ListItem<T> next){
        this.data = data;
        this.next = next;
    }

    protected ListItem(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
