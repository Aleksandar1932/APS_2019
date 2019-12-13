package mk.ukim.finki.aps.lab8;

import java.util.Random;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        Random r = new Random(System.currentTimeMillis());

        int[] testInputArr = {3, 4, 1, 8, 6, 0, 5, 2, 10, 17, 13};

        for (int i = 0; i < 11; i++){
            //3 4 1 8 6 0 5 2 10 17 13
            bst.insert(testInputArr[i]);
        }

        bst.printTree(bst.root);

        System.out.println("The depth of the BST is: " + bst.maxDepth(bst.getRoot()));
    }
}
