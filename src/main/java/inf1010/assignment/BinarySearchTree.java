package inf1010.assignment;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Stack;
import java.util.Arrays;
import inf1010.lib.two.IfiCollection;

public class BinarySearchTree<E extends Comparable<E>> implements 
                                        IfiCollection<E> {

    class Node {
        E data;
        Node left, right;

        Node(E data) {
            this.data = data;
        }
    }
	
    public Node root;
    public int bCount = 0;
	
    public boolean add(Node cNode, Node cur) {            
        int cmpt = cur.data.compareTo(cNode.data);

        if(cmpt < 0) {
            if(cur.right == null) {
                cur.right = cNode;
                bCount++;
            } else add(cNode, cur.right);
        } else if(cmpt > 0) {
            if(cur.left == null) {
                cur.left = cNode;
                bCount++;
            } else add(cNode, cur.left);
        } else {
            throw new RuntimeException("Duplicated nodes is not supported.");
	}
        return true;
    } // add()

    public boolean add(E e) {
        if(e == null) throw new NullPointerException();
        if(this.contains(e)) return false;

        Node cNode = new Node(e);

        if(this.isEmpty()) {
            this.root = cNode;
            bCount++;
            return true;
        } else if(add(cNode, root)) return true;

        return false;
    }

    public boolean contains(E e) {
        if(e == null) throw new NullPointerException();
        if(this.isEmpty()) return false;
        
        Node node = new Node(e);

        if(root != null) {
            Node current = root;

            while(current != null) {
                int cmpt = current.data.compareTo(node.data);

                if(cmpt == 0) {
                    return true;
                } else if(cmpt < 0) {
                    current = current.right;
                } else if(cmpt > 0) {
                    current = current.left;
                }
            }
        }
        return false;
    } // contains()

    public boolean remove(E e) {
	throw new UnsupportedOperationException();
    } // remove()

    public int size() {
        return bCount;
    } // size()

    public boolean isEmpty() {
        return (root == null);
    } // isEmpty()

    public void clear() {
        root = null;
        bCount = 0;    
    } // clear()

/**
* Get the creature of interest
* @param E      object of the creature to fetch
* @return E     Returns the creature if list contains e
*               Returns null if list does not contain e
**/
    public E get(E e) {
        if(!this.contains(e)) return null;

        Node node = new Node(e);
        Node current = root;

        while(current != null) {
            int cmpt = current.data.compareTo(node.data);

            if(cmpt < 0)
                current = current.right;
              else if(cmpt > 0)
                current = current.left;
              else
                return current.data;
        }
        return null;
    } // get()

    public E[] toArray(E[] a) {
	if(this.isEmpty()) return null;
        if(a == null) throw new NullPointerException();

        int tmp = 0;
        Stack<E> heap = new Stack<E>();

        traverseAndStack(heap, root);

        for(tmp = 0; !heap.empty(); tmp++) {
            a[tmp] = heap.pop();
        }

        Arrays.sort(a);

        if(a.length > this.size()) a[tmp] = null;

        return a;
    } // toArray()

    private void traverseAndStack(Stack<E> heap, Node where) {
        heap.push(where.data);

        if(where.left != null) {
            traverseAndStack(heap, where.left);
        }

        if(where.right != null) {
            traverseAndStack(heap, where.right);
        }
    }
	
    public Iterator<E> iterator() {
        InternalStackStructure iss = new InternalStackStructure();
        Iterator<E> it = iss.iterator();
        return it;
    } // iterator()

    class InternalStackStructure implements Iterable<E> {

        class StackElement {
            StackElement below;
            E data;

            StackElement(E e) {
                this.data = e;
            }
        }

        public StackElement top, bottom;

        InternalStackStructure() {
            travAdd(BinarySearchTree.this.root);
        }

/**
* Traverses through the original tree and calls add() for each node
* @param Node   Given node to add
**/

        public void travAdd(Node tElem) {
            if(tElem != null) {
                travAdd(tElem.left);
                add(tElem.data);
                travAdd(tElem.right);
            }
        }

/**
* Add method that adds StackElements to the new stack-structure
**/

        public void add(E e) {
            StackElement curE = new StackElement(e);                

            if(top == null) {
                top = curE;
                bottom = curE; 
            } else {
                for(StackElement s = top; s != null; s = s.below) {
                    if(s.below == null) {
                        s.below = curE;
                        bottom = curE;
                        break;
                    } 
                }
            }
        }

        public Iterator<E> iterator() {
            return new StackIterator();
        }
    
        private class StackIterator implements Iterator<E> {
            private StackElement curPos;

            StackIterator() {
                this.curPos = InternalStackStructure.this.top;
            }

            public boolean hasNext() {
                return (curPos != null);
            }

            public E next() {
                if(!hasNext())
                    throw new NoSuchElementException();
        
                E data = this.curPos.data;
                this.curPos = this.curPos.below;
                return data;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        } // class::StackIterator
    } // class::InternalStackStructure
} // class::BinarySearchTree
