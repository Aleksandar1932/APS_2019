package mk.ukim.finki.aps.av8;

import java.util.Iterator;

public interface Tree<E> {
	// //////////Accessors ////////////

	public Tree.Node<E> root();

	public Tree.Node<E> parent(Tree.Node<E> node);

	public int childCount(Tree.Node<E> node);

	// //////////Transformers ////////////
	public void makeRoot(E elem);

	public Tree.Node<E> addChild(Tree.Node<E> node, E elem);

	public void remove(Tree.Node<E> node);

	// //////////Iterator ////////////
	public Iterator<E> children(Tree.Node<E> node);

	// //////Inner interface for tree nodes ////////
	public interface Node<E> {

		public E getElement();

		public void setElement(E elem);

	}
}
