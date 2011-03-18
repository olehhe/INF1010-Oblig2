package inf1010.assignment;

import java.lang.UnsupportedOperationException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import inf1010.lib.two.IfiCollection;

public class SinglyLinkedList<E extends Comparable<E>> implements
		                        IfiCollection<E> {
    
    // internal list-element class
    class Element {
	public E data;
	public Element next;

        Element(E e) {
            this.data = e;
        }
    }
    
    public Element first;
    private int eCount = 0;

/**
* Adds a creature to the singly linked list (sorting in ascending order)
* @param E  object of a creature (Human or EvilAlien)
* @return       True if creature was successfully added to the list
*               False if creature already exists in the list
**/

    public boolean add(E e) {
        if(e == null) throw new NullPointerException();
        if(this.contains(e)) return false;

        Element elem = new Element(e);
        int cmpt = 0;

        if(this.first == null) {
            this.first = elem;
            eCount++;
            return true;
        } else {
            Element prevElem = this.first;
            cmpt = prevElem.data.compareTo(e);

            if(cmpt > 0) {
                elem.next = this.first;
                this.first = elem;
                eCount++;
                return true;
            } else if(cmpt < 0 && prevElem.next == null) {
                prevElem.next = elem;
                eCount++;
                return true;
            }
            
            for(Element curElem = prevElem.next; curElem != null;
                                        curElem = curElem.next) {
                cmpt = curElem.data.compareTo(e);

                if(cmpt > 0) {
                    elem.next = curElem;
                    prevElem.next = elem;
                    eCount++;
                    return true;
                } else if(cmpt < 0 && curElem.next == null) {
                    curElem.next = elem;
                    eCount++;
                    return true;
                }

            prevElem = prevElem.next;
            }
        }
        return false;
    }

/**
* Checks if the given list contains the given creature
* @param E      Creature to be checked
* @return       True if given list contains given creature
*               False if given list does not contain given creature
**/

    public boolean contains(E e) {
    	if(e == null) throw new NullPointerException();

        Element elem = new Element(e);

        for(Element current = this.first; current != null; current = current.next) {
            int cmpt = current.data.compareTo(elem.data);

            if(cmpt == 0) return true;
        }
        return false;
    }

/**
* Removes the given creature from the given list
* @param E      Creature to be removed
* @return       True if creature is successfully removed
*               False if list doesn't contain the given creature
**/
    public boolean remove(E e) {
        if(e == null) throw new NullPointerException();
        if(!this.contains(e)) return false;

        Element prevElem = first;
        int cmpt = prevElem.data.compareTo(e);

        if(cmpt == 0) {
            first = first.next;
            eCount--;
            return true;
        }

        for(Element curElem = first.next; curElem != null;
                                curElem = curElem.next) {
        
            cmpt = curElem.data.compareTo(e);

            if(cmpt == 0) {
                prevElem.next = curElem.next;                
                eCount--;
                return true;
            }
            prevElem = prevElem.next;
        }
        return false;
    } // remove()

    public int size() {
    	return eCount;
    }

    public boolean isEmpty() {
        if(this.first == null) {
            return true;
        }
        return false;
    }

    public void clear() {
        this.first = null;
        eCount = 0;
    }

/**
* Get a specific creature from the list
* @param E      Creature to fetch
* @return E     Returns the creature if found
*               Return "null" if not found
**/
    public E get(E e) {
        Element elem = new Element(e);

        for(Element elemF = first; elemF != null; elemF = elemF.next) {
            if(elemF.data.compareTo(elem.data) == 0) {
                return elemF.data;
            }
        }
        return null;
    }

/**
* Fill an array of objects in the list (ascending order)
* @param E[]    Array of the proper size
* @return       Array of creatures if method was successful
*               Null if list was empty
**/

    public E[] toArray(E[] a) {
        if(a == null)
            throw new NullPointerException();

        if(a.length > this.size())
            a[this.size()] = null;

            int arCount = 0;

            for(Element elem = first; elem != null; elem = elem.next) {
                a[arCount] = elem.data;
                arCount++;
            }
            return a;        
    }
    
/**
* Iterator that iterates over the given list
* @return       Iterator<E>
**/

    public Iterator<E> iterator() {
        return new ListIterator(first, this);
    }

    public class ListIterator implements Iterator<E> {
        SinglyLinkedList sObject;
        private Element curPos, f;
        private boolean allowRemove = false;
        
        private ListIterator(Element f, SinglyLinkedList s) {        
            if(first != null)
                this.curPos = f;
                sObject = s;
        }

        public boolean hasNext() {
            return (curPos != null);
        }

        public E next() {
            if(!hasNext()) 
                throw new NoSuchElementException();
            
            E data = this.curPos.data;
            this.curPos = this.curPos.next;
            allowRemove = true;
            return data;
        }

        @SuppressWarnings("unchecked")
        public void remove() {
            if(!allowRemove)
                throw new IllegalStateException();
    
            sObject.remove(curPos.data); // Nullpointer
            allowRemove = false;
        }
    } // ListIterator

} // SinglyLinkedList
