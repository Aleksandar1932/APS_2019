package mk.ukim.finki.aps.vezbanje1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // kreiranje na prazna lista;
        this.first = null;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Jazolot koj sakate da go vnesite ne postoi");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            // ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        }

        else {
            System.out.println("Listata e prazna");
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;

            if (first == node) {
                return this.deleteFirst();
            }

            else {
                while (tmp.succ != node && tmp.succ.succ != null)
                    tmp = tmp.succ;
                if (tmp.succ == node) {
                    tmp.succ = tmp.succ.succ;
                    return node.element;
                } else {
                    System.out.println("Elementot ne postoi vo nizata");
                    return null;
                }
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public void deleteList() {
        first = null;
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;

            while (tmp.succ != null) {
                tmp = tmp.succ;
            }

            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null) {
                tmp = tmp.succ;
            }
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Listata e prazna");
            }
        } else {
            System.out.println("Listata e prazna, pa nemoze find da se pravi");
        }

        return first; // za sekoj slucaj;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first.succ == null) {
            ret += first;
        } else {
            if (first != null) {
                SLLNode<E> tmp = first;
                ret += tmp + "->";
                while (tmp.succ != null) {
                    int flag = 1;
                    tmp = tmp.succ;

                    if (tmp.succ == null) {
                        flag = 0;
                    }
                    if (flag == 1) {
                        ret += tmp + "->";
                    } else if (flag == 0) {
                        ret += tmp;
                    }
                }
            } else
                ret = "Prazna lista!!!";
        }
        return ret;
    }

    public void mirror(){
        if (first != null){
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp!=null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
        else{
            System.out.println("Ne moze mirror na obratna lista");
        }
    }
}
