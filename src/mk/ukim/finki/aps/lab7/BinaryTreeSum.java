package mk.ukim.finki.aps.lab7;

import java.io.*;
import java.util.*;

/*
    Klasite BNode<E> i BTree<E> se komentirani namerno,
    bidejki se duplikat vo zadacata PreorderNonRecursive.java;
*/

//class BNode<E> {
//
//    public E info;
//    public BNode<E> left;
//    public BNode<E> right;
//
//    static int LEFT = 1;
//    static int RIGHT = 2;
//
//    public BNode(E info) {
//        this.info = info;
//        left = null;
//        right = null;
//    }
//
//    public BNode() {
//        this.info = null;
//        left = null;
//        right = null;
//    }
//
//    public BNode(E info, BNode<E> left, BNode<E> right) {
//        this.info = info;
//        this.left = left;
//        this.right = right;
//    }
//
//}

//class BTree<E extends Comparable<E>> {
//
//    public BNode<E> root;
//
//    public BTree() {
//        root = null;
//    }
//
//    public BTree(E info) {
//        root = new BNode<E>(info);
//    }
//
//    public void makeRoot(E elem) {
//        root = new BNode(elem);
//    }
//
//    public void makeRootNode(BNode<E> node) {
//        root = node;
//    }
//
//    public BNode<E> addChild(BNode<E> node, int where, E elem) {
//
//        BNode<E> tmp = new BNode<E>(elem);
//
//        if (where == BNode.LEFT) {
//            if (node.left != null)  // veke postoi element
//                return null;
//            node.left = tmp;
//        } else {
//            if (node.right != null) // veke postoi element
//                return null;
//            node.right = tmp;
//        }
//
//        return tmp;
//    }
//
//    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {
//
//        if (where == BNode.LEFT) {
//            if (node.left != null)  // veke postoi element
//                return null;
//            node.left = tmp;
//        } else {
//            if (node.right != null) // veke postoi element
//                return null;
//            node.right = tmp;
//        }
//
//        return tmp;
//    }
//
//}

public class BinaryTreeSum {


    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode[] nodes = new BNode[N];
        BTree<Integer> tree = new BTree<>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        int baranaVrednost = Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod ovde

        binaryTreeSumEvaluator(tree, baranaVrednost);


    }

    public static void binaryTreeSumEvaluator(BTree<Integer> tree, int findValue) {
        BNode<Integer> nodeToEvaluate = findNodeWithValue(tree.root, findValue); //Ovde go imame klucot za baranje
        int resultLeft = sumInSubtree(nodeToEvaluate.left, findValue, "<");
        int resultRight = sumInSubtree(nodeToEvaluate.right, findValue, ">");

        System.out.println(resultLeft + " " + resultRight);


    }

    public static BNode<Integer> findNodeWithValue(BNode<Integer> node, int value) {
        BNode<Integer> result = null;
        if (node == null) {
            return null;
        }
        if (node.info.equals(value)) {
            return node;
        }
        if (node.left != null) {
            result = findNodeWithValue(node.left, value);
        }
        if (result == null) {
            result = findNodeWithValue(node.right, value);
        }

        return result;
    }

    public static int sumInSubtree(BNode<Integer> nodeToCalculateFor, int value, String sign) {
        if (nodeToCalculateFor == null) {
            return 0;
        } //Terminalen slucaj


        if (evaluateBySign(nodeToCalculateFor.info, value, sign)) {
            return (nodeToCalculateFor.info
                    + sumInSubtree(nodeToCalculateFor.left, value, sign)
                    + sumInSubtree(nodeToCalculateFor.right, value, sign));
        }

        return (sumInSubtree(nodeToCalculateFor.left, value, sign)
                + sumInSubtree(nodeToCalculateFor.right, value, sign));
    }

    public static boolean evaluateBySign(Integer a, Integer b, String sign) {
        if (sign.equals("<")) {
            return a < b;
        } else if (sign.equals(">")) {
            return a > b;
        }
        return false;
    }
}