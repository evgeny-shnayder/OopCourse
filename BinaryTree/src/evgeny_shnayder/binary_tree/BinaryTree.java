package evgeny_shnayder.binary_tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private Node<E> rootNode;
    private Comparator<? super E> comparator;
    private int count;

    public BinaryTree() {
    }

    public BinaryTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public int getCount() {
        return count;
    }

    private int getComparisonResult(E object1, E object2) {
        if (object1 == null && object2 == null) {
            return 0;
        }

        if (object1 == null) {
            return -1;
        }

        if (object2 == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(object1, object2);
        }

        @SuppressWarnings("unchecked") Comparable<? super E> comparable = (Comparable<? super E>) object1;
        return comparable.compareTo(object2);
    }

    public boolean add(E data) {
        if (rootNode == null) {
            rootNode = new Node<>(data);
            count++;

            return true;
        }

        Node<E> currentNode = rootNode;

        while (currentNode != null) {
            int comparisonResult = getComparisonResult(data, currentNode.getData());

            if (comparisonResult >= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node<>(data));
                    count++;

                    break;
                }

                currentNode = currentNode.getRight();
            }

            if (comparisonResult < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node<>(data));
                    count++;

                    break;
                }

                currentNode = currentNode.getLeft();
            }
        }

        return true;
    }

    public boolean contains(E data) {
        Node<E> currentNode = rootNode;

        while (currentNode != null) {
            int comparisonResult = getComparisonResult(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
                if (currentNode.getRight() == null) {
                    return false;
                }

                currentNode = currentNode.getRight();
            }

            if (comparisonResult < 0) {
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

        while (deletedNode != null) {
            int comparisonResult = getComparisonResult(data, deletedNode.getData());

            if (comparisonResult > 0) {
                if (deletedNode.getRight() == null) {
                    return false;
                }

                parentNode = deletedNode;
                deletedNode = deletedNode.getRight();
                isLeft = false;
            }

            if (comparisonResult < 0) {
                if (deletedNode.getLeft() == null) {
                    return false;
                }

                parentNode = deletedNode;
                deletedNode = deletedNode.getLeft();
                isLeft = true;
            }

            if (comparisonResult == 0) {
                if (deletedNode.getLeft() == null && deletedNode.getRight() == null) {
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

                if (deletedNode.getLeft() == null || deletedNode.getRight() == null) {
                    if (deletedNode.getRight() != null) {
                        if (parentNode == null) {
                            rootNode.setData(rootNode.getRight().getData());
                            rootNode.setRight(rootNode.getRight().getRight());

                            count--;

                            return true;
                        }

                        if (isLeft) {
                            parentNode.setLeft(deletedNode.getRight());
                        } else {
                            parentNode.setRight(deletedNode.getRight());
                        }

                        count--;

                        return true;
                    }

                    if (deletedNode.getLeft() != null) {
                        if (parentNode == null) {
                            rootNode.setData(rootNode.getLeft().getData());
                            rootNode.setRight(rootNode.getLeft().getLeft());

                            count--;

                            return true;
                        }

                        if (isLeft) {
                            parentNode.setLeft(deletedNode.getLeft());
                        } else {
                            parentNode.setRight(deletedNode.getLeft());
                        }

                        count--;

                        return true;
                    }
                }

                Node<E> minLeftNodeParent = deletedNode;
                Node<E> minLeftNode = deletedNode.getRight();

                if (minLeftNode.getLeft() == null && minLeftNode.getRight() == null) {
                    if (parentNode == null) {
                        rootNode.setRight(null);
                        rootNode.setData(minLeftNode.getData());

                        count--;

                        return true;
                    }

                    if (isLeft) {
                        parentNode.setLeft(minLeftNode);
                    } else {
                        parentNode.setRight(minLeftNode);
                    }

                    minLeftNode.setLeft(deletedNode.getLeft());
                    minLeftNode.setRight(null);

                    count--;

                    return true;
                } else if (minLeftNode.getLeft() == null && minLeftNode.getRight() != null) {
                    minLeftNodeParent.setRight(minLeftNode.getRight());

                    if (parentNode == null) {
                        rootNode.setData(minLeftNode.getData());
                        count--;

                        return true;
                    }

                    parentNode.setRight(minLeftNode);
                    minLeftNode.setLeft(deletedNode.getLeft());

                    count--;

                    return true;
                } else {
                    while (minLeftNode.getLeft() != null) {
                        minLeftNodeParent = minLeftNode;
                        minLeftNode = minLeftNode.getLeft();
                    }

                    if (minLeftNode.getRight() != null) {
                        minLeftNodeParent.setLeft(minLeftNode.getRight());
                    } else {
                        minLeftNodeParent.setLeft(null);
                    }

                    if (parentNode == null) {
                        rootNode.setData(minLeftNode.getData());
                        minLeftNode.setRight(null);
                        minLeftNode.setLeft(null);

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
            }
        }

        return true;
    }

    private void recursiveTraverse(Node<E> node, Consumer<E> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            recursiveTraverse(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            recursiveTraverse(node.getRight(), consumer);
        }
    }

    public void depthRecursiveTraverse(Consumer<E> consumer) {
        recursiveTraverse(rootNode, consumer);
    }

    public void depthTraverse(Consumer<E> consumer) {
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

    public void widthTraverse(Consumer<E> consumer) {
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
