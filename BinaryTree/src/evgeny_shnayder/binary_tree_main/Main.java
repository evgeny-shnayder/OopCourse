package evgeny_shnayder.binary_tree_main;

import evgeny_shnayder.binary_tree.BinaryTree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = (data1, data2) -> {
            if (data1 == null && data2 == null) {
                return 0;
            }

            if (data1 == null) {
                return -1;
            }

            if (data2 == null) {
                return 1;
            }

            return Integer.compare(data1, data2);
        };

        BinaryTree<Integer> tree = new BinaryTree<>(comparator);

        tree.add(123);
        tree.add(12);
        tree.add(248);
        tree.add(98);
        tree.add(238);
        tree.add(255);
        tree.add(null);
        tree.add(null);

        tree.add(250);
        tree.add(259);
        tree.add(10);
        tree.add(9);
        tree.add(249);
        tree.add(240);
        tree.add(260);

        System.out.println("Дерево содержит элемент: " + tree.contains(null));
        System.out.println("Размер дерева: " + tree.getCount());
        System.out.println(tree.remove(123));
        System.out.println("Размер дерева: " + tree.getCount());

        Consumer<Integer> printer = System.out::println;

        tree.traverseInDepthRecursive(printer);

        BinaryTree<Integer> tree1 = new BinaryTree<>();

        tree1.add(10);
        tree1.add(15);
        tree1.add(20);
        tree1.add(25);
        tree1.add(30);
        tree1.add(12);
        tree1.add(13);

        System.out.println("Размер дерева: " + tree1.getCount());
        System.out.println(tree1.remove(12));
        System.out.println("Размер дерева: " + tree1.getCount());

        tree1.traverseInWidth(printer);
        tree1.traverseInDepth(printer);

        BinaryTree<Integer> tree2 = new BinaryTree<>();

        tree2.traverseInWidth(printer);
    }
}
