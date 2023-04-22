package evgeny_shnayder.binary_tree;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> rootNode;

    private int count;

    public BinaryTree() {
    }

    public BinaryTree(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    public int getCount() {
        return count;
    }

    public Node<T> getRootNode() {
        return rootNode;
    }

    private void setRootNode(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    public boolean add(T data) {
        Node<T> currentNode = rootNode;

        if (currentNode == null) {
            setRootNode(new Node<>(data));
            count++;

            return true;
        }

        while (currentNode != null) {
            int compareNumber = data.compareTo(currentNode.getData());

            if (compareNumber >= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node<>(data));
                    count++;

                    return true;
                } else {
                    currentNode = currentNode.getRight();
                }
            }

            if (compareNumber < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node<>(data));
                    count++;

                    return true;
                } else {
                    currentNode = currentNode.getLeft();
                }
            }
        }

        return false;
    }

    public Node<T> getNode(T data) {
        Node<T> currentNode = rootNode;

        if (currentNode == null) {
            throw new NullPointerException("Бинарное дерево не содержит узлов.");
        }

        while (currentNode != null) {
            if (Objects.equals(data, currentNode.getData())) {
                return currentNode;
            }

            int compareNumber = data.compareTo(currentNode.getData());

            if (compareNumber >= 0) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    throw new NoSuchElementException("Узел со сначением " + data + " отсутствует в дереве.");
                }
            }

            if (compareNumber < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    throw new NoSuchElementException("Узел со сначением " + data + " отсутствует в дереве.");
                }
            }
        }

        return null;
    }

    public boolean removeNode(T data) {
        if (rootNode == null) {
            return false;
        }

        if (Objects.equals(data, rootNode.getData())) {
            if (rootNode.getRight() != null) {
                Node<T> parentMinLeftNode = rootNode;
                Node<T> minLeftNode = rootNode.getRight();

                if (minLeftNode.getLeft() == null && minLeftNode.getRight() == null) {
                    minLeftNode.setLeft(rootNode.getLeft());
                    minLeftNode.setRight(null);
                    setRootNode(minLeftNode);
                } else {
                    while (minLeftNode.getLeft() != null) {
                        parentMinLeftNode = minLeftNode;
                        minLeftNode = minLeftNode.getLeft();
                    }

                    if (minLeftNode.getRight() != null) {
                        parentMinLeftNode.setLeft(minLeftNode.getRight());
                    } else {
                        parentMinLeftNode.setLeft(null);
                    }

                    minLeftNode.setLeft(rootNode.getLeft());
                    minLeftNode.setRight(rootNode.getRight());
                    setRootNode(minLeftNode);
                    count--;

                    return true;
                }
            }
        }

        Node<T> removedNode = rootNode;
        Node<T> parentNode = null;

        while (true) {
            int compareNumber = data.compareTo(removedNode.getData());
            boolean isLeft = false;
            boolean isRight = false;

            if (compareNumber >= 0) {
                if (removedNode.getRight() != null) {
                    parentNode = removedNode;
                    removedNode = removedNode.getRight();
                    isRight = true;
                } else {
                    return false;
                }
            }

            if (compareNumber < 0) {
                if (removedNode.getLeft() != null) {
                    parentNode = removedNode;
                    removedNode = removedNode.getLeft();
                    isLeft = true;
                } else {
                    return false;
                }
            }

            if (Objects.equals(data, removedNode.getData())) {
                if (removedNode.getRight() != null && removedNode.getLeft() != null) {
                    Node<T> parentMinLeftNode = removedNode;
                    Node<T> minLeftNode = removedNode.getRight();

                    if (minLeftNode.getLeft() == null && minLeftNode.getRight() == null) {
                        if (isLeft) {
                            parentNode.setLeft(minLeftNode);
                            minLeftNode.setLeft(removedNode.getLeft());
                            minLeftNode.setRight(null);
                        }

                        if (isRight) {
                            parentNode.setRight(minLeftNode);
                            minLeftNode.setLeft(removedNode.getLeft());
                            minLeftNode.setRight(null);
                        }
                    } else {
                        while (minLeftNode.getLeft() != null) {
                            parentMinLeftNode = minLeftNode;
                            minLeftNode = minLeftNode.getLeft();
                        }

                        if (minLeftNode.getRight() != null) {
                            parentMinLeftNode.setLeft(minLeftNode.getRight());
                        } else {
                            parentMinLeftNode.setLeft(null);
                        }

                        minLeftNode.setLeft(removedNode.getLeft());
                        minLeftNode.setRight(removedNode.getRight());

                        if (isLeft) {
                            parentNode.setLeft(minLeftNode);
                        }

                        if (isRight) {
                            parentNode.setRight(minLeftNode);
                        }

                        count--;

                        return true;
                    }
                }

                if (removedNode.getRight() != null || removedNode.getLeft() != null) {
                    if (removedNode.getLeft() != null) {
                        if (isLeft) {
                            parentNode.setLeft(removedNode.getLeft());
                        }

                        if (isRight) {
                            parentNode.setRight(removedNode.getLeft());
                        }
                    }

                    if (removedNode.getRight() != null) {
                        if (isLeft) {
                            parentNode.setLeft(removedNode.getRight());
                        }

                        if (isRight) {
                            parentNode.setRight(removedNode.getRight());
                        }
                    }

                    count--;

                    return true;
                }

                if (removedNode.getRight() == null && removedNode.getLeft() == null) {
                    if (isLeft) {
                        parentNode.setLeft(null);
                    }

                    if (isRight) {
                        parentNode.setRight(null);
                    }

                    count--;

                    return true;
                }
            }
        }
    }

    private void recursiveTraverse(Node<T> node) {
        System.out.println(node);

        if (node.getLeft() != null) {
            recursiveTraverse(node.getLeft());
        }

        if (node.getRight() != null) {
            recursiveTraverse(node.getRight());
        }
    }

    public void printTreeDepthTraverse() {
        Stack<Node<T>> stack = new Stack<>();

        stack.add(rootNode);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();

            System.out.println(currentNode);

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    public void printTreeDepthRecursiveTraverse() {
        recursiveTraverse(rootNode);
    }

    public void printTreeWidthTraverse() {
        Queue<Node<T>> queue = new LinkedList<>();

        queue.add(rootNode);

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.peek();

            System.out.println(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }

            queue.remove(currentNode);
        }
    }
}
