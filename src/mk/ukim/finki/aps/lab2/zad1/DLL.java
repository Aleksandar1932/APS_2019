package mk.ukim.finki.aps.lab2.zad1;

public class DLL<E> {
    protected DLLNode<E> first;
    protected DLLNode<E> last;

    public DLL() {
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode(o, null, first);
        if (first == null) {
            last = ins;
        } else {
            first.pred = ins;
        }
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null) {
            insertFirst(o);
        } else {
            DLLNode<E> ins = new DLLNode(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
        } else {
            DLLNode<E> ins = new DLLNode(o, after, after.succ);
            after.succ.pred = ins;
            after.succ = ins;
        }
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
        } else {
            DLLNode<E> ins = new DLLNode(o, before.pred, before);
            before.pred.succ = ins;
            before.pred = ins;
        }
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null) {
                return deleteFirst(); //Slucaj koga imame DLL samo so eden element, kojsto vsusnost e first;
            } else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else {
            return null;
        }
    }

    public E deleteNode(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        } else if (node == last) {
            return deleteLast();
        } else {
            node.pred.succ = node.succ;
            node.succ.pred = node.pred;
            return node.element;
        }
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public int getSize() {
        int retValue = 0;
        DLLNode<E> temp = first;
        while (temp != null) {
            retValue++;
            temp = temp.succ;
        }
        return retValue;
    }

    @Override
    public String toString() {
        String retValue = new String();
        if (first != null) {
            DLLNode<E> tmp = first;

            while (tmp != null) {
                if (tmp.succ == null){
                    return retValue + tmp;
                }
                retValue += tmp + "->";
                tmp = tmp.succ;
            }
        }
        return retValue;
    }
}
