package evgeny_shnayder.binary_tree;

public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private T data;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(Node<T> left, Node<T> right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public String toString() {
        return data.toString();
    }
}
