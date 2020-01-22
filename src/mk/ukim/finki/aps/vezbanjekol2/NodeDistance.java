package mk.ukim.finki.aps.vezbanjekol2;

import mk.ukim.finki.aps.vezbanjekol2.btree.BNode;
import mk.ukim.finki.aps.vezbanjekol2.btree.BTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NodeDistance {
    public static void main(String[] args) throws IOException {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<String> nodes[] = new BNode[N];
        BTree<String> tree = new BTree<String>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<String>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = st.nextToken();
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

        int cases = Integer.parseInt(br.readLine());
        for (int counter = 0; counter < cases; counter++) {
            String[] caseLineTokens = br.readLine().split(" ");
            calculateDistance(tree, caseLineTokens[0], caseLineTokens[1]);
        }

    }

    public static BNode<String> getNodeByInfo(BTree<String> tree, String info) {
        return tree.getNodesInorder(tree.root)
                .stream()
                .filter(stringBNode -> stringBNode.getInfo().equals(info))
                .findFirst().orElse(new BNode<>("ERROR"));
    }

    public static BNode<String> lca(BNode<String> root, String A, String X) {
//        if (root == null) {
//            return root;
//        }
        if (root.getInfo().equals(A) || root.getInfo().equals(X)) {
            return root;
        }

        BNode<String> left = lca(root.left, A, X);
        BNode<String> right = lca(root.right, A, X);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return lca(root.left, A, X);
        } else
            return lca(root.right, A, X);
    }


    public static void calculateDistance(BTree<String> bTree, String nodeA, String nodeX) {
        String resultString;
        BNode<String> A = getNodeByInfo(bTree, nodeA);
        BNode<String> X = getNodeByInfo(bTree, nodeX);

        if (A.getInfo().equals("ERROR") || X.getInfo().equals("ERROR")) {
            resultString = "ERROR";
        } else {
            BNode<String> lca = lca(bTree.getRoot(), nodeA, nodeX);

            int levelA = bTree.findNodeLevel(lca, A, 0);
            int levelX = bTree.findNodeLevel(lca, X, 0);

            resultString = (2 * (levelA + levelX)) + "";

        }
        System.out.println("" + resultString);
    }
}
