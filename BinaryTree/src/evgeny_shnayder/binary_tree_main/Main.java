package evgeny_shnayder.binary_tree_main;

import evgeny_shnayder.binary_tree.BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        System.out.println(tree.add(123));
        System.out.println(tree.add(12));
        System.out.println(tree.add(248));
        System.out.println(tree.add(98));
        System.out.println(tree.add(238));
        System.out.println(tree.add(255));
        tree.add(250);
        tree.add(259);
        tree.add(10);
        tree.add(9);

        tree.printTreeWidthTraverse();

        System.out.println(tree.removeNode(9));

        tree.printTreeWidthTraverse();
        tree.printTreeDepthRecursiveTraverse();
        tree.printTreeDepthTraverse();

        System.out.println(tree.getNode(5));
    }
}
