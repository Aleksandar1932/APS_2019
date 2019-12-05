package mk.ukim.finki.aps.lab2.zad2;

/*
Aleksandar Ivanovski 22.10.2019 17:35
 */
public class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred;
    protected DLLNode<E> succ;

    public DLLNode(E element, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = element;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return this.element.toString();
    }
}
