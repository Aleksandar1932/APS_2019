package mk.ukim.finki.aps.vezbanje1;

public class SLLNode<E>{
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E element, SLLNode<E> succ){
        this.element = element;
        this.succ = succ;
    }

    @Override
	public String toString() {
		return element.toString();
	}
}