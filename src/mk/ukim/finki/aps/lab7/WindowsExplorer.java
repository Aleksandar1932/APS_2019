package mk.ukim.finki.aps.lab7;


import java.util.*;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i = 0; i < level; i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>) node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level + 1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

}

class SLLNode<P> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
}

public class WindowsExplorer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Inicijalizacija na drvo so koren c:
        SLLTree<String> explorerTree = new SLLTree<>();
        explorerTree.makeRoot("c:");
        SLLNode<String> currentNode = explorerTree.root;


        int numberOfCommands = in.nextInt();

        for (int i = 0; i <= numberOfCommands; i++) {
            /*
                Mozni komandi:
                    CREATE: Vo tekovniot node se kreira nov node;
                    OPEN: Se menuva tekovniot folder, so onoj koj e prosleden;
                    DELETE: Se brise prosledeniot folder;
                    BACK: Tekovniot folder stanuva eden nanazad, t.e. roditelot na tekovniot;
                    PATH: Se pecati patekata na tekovniot folder;
                    PRINT: Se pecati celata sturktra na datoteciot sistem, od negoviot koren;
             */

            String commandLine = in.nextLine();

            if (commandLine.contains("CREATE")) {
                createCommand(commandLine, currentNode, explorerTree);
            } else if (commandLine.contains("OPEN")) {
                currentNode = openCommand(commandLine, currentNode, explorerTree);
            } else if (commandLine.contains("DELETE")) {
                deleteCommand(commandLine, currentNode, explorerTree);
            } else if (commandLine.contains("BACK")) {
                currentNode = backCommand(commandLine, currentNode, explorerTree);
            } else if (commandLine.contains("PATH")) {
                pathCommand(commandLine, currentNode, explorerTree);
            } else if (commandLine.contains("PRINT")) {
                printCommand(commandLine, currentNode, explorerTree);
            }
            else if(commandLine.equals("")){

            }
        }

    }

    private static void printCommand(String commandLine, SLLNode<String> currentNode, SLLTree<String> explorerTree) {
        explorerTree.printTree();
    }

    private static void pathCommand(String commandLine, SLLNode<String> currentNode, SLLTree<String> explorerTree) {

        if(currentNode == explorerTree.root){
            System.out.println("c:\\");
            return;
        }

        SLLNode<String> nodeToPrint = currentNode;
        StringBuilder retString = new StringBuilder();
        while (nodeToPrint.parent != null) {
            retString.append(nodeToPrint.element).append("\\");
            nodeToPrint = nodeToPrint.parent;
        }

        System.out.println("c:\\" + reverseString(retString.toString()));

    }

    public static String reverseString(String stringToReverse) {
//        StringBuilder retString = new StringBuilder();
//        for (int i = stringToReverse.length()-1; i >= 0; i--) {
//            retString.append(stringToReverse.charAt(i));
//        }
//
//        return retString.toString();

        String[] tokenizedPath = stringToReverse.split("\\\\");

//        System.out.println(" tokeniziran " + Arrays.toString(tokenizedPath));
        StringBuilder retString = new StringBuilder();

        for(int i = tokenizedPath.length-1 ; i >= 0 ; i--){
            retString.append(tokenizedPath[i]).append("\\");
        }

//        System.out.println("Sredeno: " + retString);

        return retString.toString();
    }

    private static SLLNode<String> backCommand(String commandLine, SLLNode<String> currentNode, SLLTree<String> explorerTree) {
        currentNode = currentNode.parent;
        return currentNode;

    }

    private static void deleteCommand(String commandLine, SLLNode<String> currentNode, SLLTree<String> explorerTree) {
        String[] tokens = commandLine.split("\\s+");
        explorerTree.remove(findNodeWithElement(tokens[1], currentNode));
    }

    public static SLLNode<String> findNodeWithElement(String element, SLLNode<String> currentNode) {
        SLLNode<String> toDelete = currentNode.firstChild;

        while (toDelete != null) {
            if (toDelete.element.equals(element)) {
                return toDelete;
            }
            toDelete = toDelete.sibling;
        }

        return null;
    }

    private static SLLNode<String> openCommand(String commandLine, SLLNode<String> currentNode, SLLTree<String> explorerTree) {
        String[] tokens = commandLine.split("\\s+");
        /*
            tokens[0] - command name
            tokens[1] - node
         */

        SLLNode<String> toOpen = currentNode.firstChild;

        while (toOpen != null) {
            if (toOpen.element.equals(tokens[1])) {
                currentNode = toOpen;
            }
            toOpen = toOpen.sibling;
        }

        return currentNode;
    }

    private static void createCommand(String commandLine, SLLNode<String> currentNode, SLLTree<String> explorerTree) {
        String[] tokens = commandLine.split("\\s+");
        /*
            tokens[0] - command name
            tokens[1] - node value;
         */

//        explorerTree.addChild(currentNode, tokens[1]);

        String node = tokens[1];
        SLLNode<String> temp = currentNode.firstChild;
        if (temp == null || (temp.element.compareTo(node) > 0)) {
            explorerTree.addChild(currentNode,node);
        }
        else  {
            SLLNode<String> ins = new SLLNode(node);
            while (temp.sibling != null) {
                if (node.compareTo(temp.sibling.element) < 0) {
                    ins.sibling = temp.sibling;
                    temp.sibling = ins;
                    ins.parent = currentNode;
                    break;
                }
                temp = temp.sibling;
            }
            ins.parent = currentNode;
            temp.sibling = ins;
        }
    }
}
