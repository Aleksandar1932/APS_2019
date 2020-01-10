package mk.ukim.finki.aps.vezbanjekol2;

import mk.ukim.finki.aps.vezbanjekol2.btree.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ValidityCheck {
    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<Integer>();

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

        br.close();

        // vasiot kod ovde

//        tree.printPreOrderIterative(tree.root);
//
//        ArrayList<BNode<Integer>> inorderList = tree.getNodesInorder(tree.root);
//
//        System.out.println(inorderList);

        System.out.println(checkTreeValidity(tree.getNodesInorder(tree.root)));
    }

    private static boolean checkTreeValidity(ArrayList<BNode<Integer>> nodes) {
        boolean returnValue = true;
        for (int i = 0; i < nodes.size(); i++) {
            BNode<Integer> currentNode = nodes.get(i); //Za polesna manipulacija;
            if (currentNode.left != null) {
                if (currentNode.left.getInfo() > currentNode.getInfo()) {
                    returnValue = false;
                    break;
                }
            }
            if (currentNode.right != null) {
                if (currentNode.right.getInfo() > currentNode.getInfo()) {
                    returnValue = false;
                    break;
                }
            }
        }

        return returnValue;
    }
}
