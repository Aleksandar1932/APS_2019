package mk.ukim.finki.aps.lab8;

public class BinarySearchTree<E extends Comparable<E>> {
    public BNode<E> root;
    int counter;

    public BinarySearchTree() {
        this.root = null;
        counter = 0;
    }

    public void insert(E x) {
        this.root = insert(x, root);
    }

    public BNode<E> getRoot() {
        return root;
    }

    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else ;  // Duplicate; do nothing
        return t;
    }

    public void printTree(BNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(" " + t.info + " Level:" + getNodeLevel(root, t.info));
            printTree(t.right);
        }
    }

    public int maxDepth(BNode<E> node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);

            return (Math.max(leftDepth, rightDepth) + 1);
        }
    }

    public BNode<E> find(E x) {
        return find(x, root);
    }

    public BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;

        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t;    // Match
        }
    }

    public int getNodeLevel(BNode<E> node, E x) {
        return getNodeLevelUtil(node, x, 1);
    }

    public int getNodeLevelUtil(BNode<E> node, E x, int level) {
        if (node == null) {
            return 0;
        }

        if (node.info == x) {
            return level;
        }

        int downLevel = getNodeLevelUtil(node.left, x, level + 1);
        if (downLevel != 0) { //dali e levo, ako go najde levo vrat;
            return downLevel;
        }

        //ne  e najdeno levo znaci baraj desno; kraj kraeva 0 ke bide;
        downLevel = getNodeLevelUtil(node.right, x, level + 1);
        return downLevel;
    }

    public int countNodesOnLevel(BNode<E> t, int level, int count) {


        if (t != null) {
            countNodesOnLevel(t.left, level, count);
            if (this.getNodeLevel(root, t.info) == level + 1) {
                counter++;
            }
            countNodesOnLevel(t.right, level, count);
        }
        return counter;
    }


}
