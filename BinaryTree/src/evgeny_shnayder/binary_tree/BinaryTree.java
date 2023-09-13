package evgeny_shnayder.binary_tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private Node<E> rootNode;
    private final Comparator<? super E> comparator;
    private int count;

    public BinaryTree() {
        comparator = null;
    }

    public BinaryTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public int getCount() {
        return count;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<? super E>) data1).compareTo(data2);
    }

    public void add(E data) {
        if (rootNode == null) {
            rootNode = new Node<>(data);
            count++;

            return;
        }

        Node<E> currentNode = rootNode;

        while (currentNode != null) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult >= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node<>(data));
                    count++;

                    return;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node<>(data));
                    count++;

                    return;
                }

                currentNode = currentNode.getLeft();
            }
        }
    }

    public boolean contains(E data) {
        Node<E> currentNode = rootNode;

        while (currentNode != null) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            } else {
                if (currentNode.getLeft() == null) {
                    return false;
                }

                currentNode = currentNode.getLeft();
            }
        }

        return false;
    }

    public boolean remove(E data) {
        if (rootNode == null) {
            return false;
        }

        Node<E> deletedNode = rootNode;
        Node<E> parentNode = null;
        boolean isLeft = false;

        while (true) {
            int comparisonResult = compare(data, deletedNode.getData());

            if (comparisonResult > 0) {
                if (deletedNode.getRight() == null) {
                    return false;
                }

                parentNode = deletedNode;
                deletedNode = deletedNode.getRight();
                isLeft = false;
            } else if (comparisonResult < 0) {
                if (deletedNode.getLeft() == null) {
                    return false;
                }

                parentNode = deletedNode;
                deletedNode = deletedNode.getLeft();
                isLeft = true;
            } else {
                break;
            }
        }

        if (deletedNode.getRight() == null && deletedNode.getLeft() == null) {
            if (parentNode == null) {
                rootNode = null;
                count--;

                return true;
            }

            if (isLeft) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }

            count--;

            return true;
        }

        if (deletedNode.getRight() == null || deletedNode.getLeft() == null) {
            Node<E> deletedNodeChildren = deletedNode.getRight() != null ? deletedNode.getRight() : deletedNode.getLeft();

            if (parentNode == null) {
                rootNode = deletedNodeChildren;
                count--;

                return true;
            }

            if (isLeft) {
                parentNode.setLeft(deletedNodeChildren);
            } else {
                parentNode.setRight(deletedNodeChildren);
            }

            count--;

            return true;
        }

        Node<E> minLeftNodeParent = deletedNode;
        Node<E> minLeftNode = deletedNode.getRight();

        if (minLeftNode.getLeft() == null) {
            minLeftNodeParent.setRight(minLeftNode.getRight());
        } else {
            while (minLeftNode.getLeft() != null) {
                minLeftNodeParent = minLeftNode;
                minLeftNode = minLeftNode.getLeft();
            }

            minLeftNodeParent.setLeft(minLeftNode.getRight());
        }

        if (parentNode == null) {
            minLeftNode.setRight(rootNode.getRight());
            minLeftNode.setLeft(rootNode.getLeft());
            rootNode = minLeftNode;
            count--;

            return true;
        }

        minLeftNode.setLeft(deletedNode.getLeft());
        minLeftNode.setRight(deletedNode.getRight());

        if (isLeft) {
            parentNode.setLeft(minLeftNode);
        } else {
            parentNode.setRight(minLeftNode);
        }

        count--;

        return true;
    }

    private void traverseInDepthRecursive(Node<E> node, Consumer<E> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            traverseInDepthRecursive(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            traverseInDepthRecursive(node.getRight(), consumer);
        }
    }

    public void traverseInDepthRecursive(Consumer<E> consumer) {
        if (rootNode == null) {
            return;
        }

        traverseInDepthRecursive(rootNode, consumer);
    }

    public void traverseInDepth(Consumer<E> consumer) {
        if (rootNode == null) {
            return;
        }

        ArrayList<Node<E>> stack = new ArrayList<>();

        stack.add(rootNode);

        while (!stack.isEmpty()) {
            Node<E> currentNode = stack.remove(stack.size() - 1);

            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }
        }
    }

    public void traverseInWidth(Consumer<E> consumer) {
        if (rootNode == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();

        queue.add(rootNode);

        while (!queue.isEmpty()) {
            Node<E> currentNode = queue.poll();

            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }
}
