package mk.ukim.finki.aps.vezbanjekol2.btree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public static int LEFT = 1;
    public static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    public E getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "BNode{" +
                "info=" + info +
                '}';
    }
}

