package mk.ukim.finki.aps.lab8;

import java.util.Scanner;


public class BinarnoDrvo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int numberOfNodes = in.nextInt();

        for (int i = 0; i < numberOfNodes; i++) {
            bst.insert(in.nextInt());
        }

//        int totalHeight = bst.maxDepth(bst.getRoot());
//        System.out.println("The depth of the BST is: " + totalHeight);

        int nodeInfoKey = in.nextInt();
        BNode<Integer> keyNode = bst.find(nodeInfoKey);

        int heightOfNode = bst.maxDepth(keyNode);
        System.out.println("" + heightOfNode);

        System.out.println("" + bst.countNodesOnLevel(bst.getRoot(),heightOfNode,0));

    }
}
