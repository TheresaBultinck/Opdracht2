package datastructure;

import java.util.Iterator;

/**
 * Linked List
 * @author Theresa Bultinck
 *
 * @param <T> type of the parameter
 */
public class LinkedList<T> implements Iterable<T> {
	
	private ListNode<T> head = null;
	private ListNode<T> tail = null;
	private int size = 0;
	
	/**
	 * Constructor for the linked list with one node
	 * @param node part of the linked list
	 */
	public LinkedList(ListNode<T> node){
		head = tail = node;
		size++;
	}
	
	/**
	 * Default constructor
	 */
	public LinkedList(){
	}
	
	/**
	 * 
	 * @return true if empty, false if the linked list contains elements
	 */
	public boolean isEmpty(){
		return (size == 0);
	}
	
	/**
	 * 
	 * @return the number of elements (nodes) in the list
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * 
	 * @return the head of the list
	 */
	public T first(){
		return head.getElement();
	}
	
	/**
	 * 
	 * @return the tail of the list
	 */
	public T last(){
		return tail.getElement();
	}
	
	private void update(ListNode<T> first,ListNode<T> second, ListNode<T> third){
		if (first != null)
			first.setNext(second);
		second.setPrev(first);
		second.setNext(third);
		if (third != null)
			third.setPrev(second);
		size++;
	}
	
	/**
	 * Adds an element before a ListNode node
	 * @param node
	 * @param element
	 */
	public void addBefore(ListNode<T> node, T element){
		ListNode<T> addedNode = new ListNode<T>(element);
		ListNode<T> before = node.getPrev();
		update(before,addedNode,node);
		if (node == head){
			this.head = addedNode; 
		}
	}
	
	/**
	 * Adds an element after a ListNode node
	 * @param node
	 * @param element
	 */
	public void addAfter(ListNode<T> node, T element){
		ListNode<T> addedNode = new ListNode<T>(element);
		ListNode<T> after = node.getNext();
		update(node,addedNode,after);
		if (node == tail){
			this.tail = addedNode;
		}
	}
	
	/**
	 * Adds an element to the linked list at the end of the list?
	 * @param element
	 */
	public void add(T element){
		if (size == 0){
			ListNode<T> addedNode = new ListNode<T> (element);
			head = tail = addedNode; 
			size++;
		}
		else {
			addAfter(tail, element);
		}
	}
	
	@Override
	public String toString(){
		if(size < 1) {
			return "[]";
		}
		ListNode<T> currentNode = head;
		String result = "[";
		while(currentNode.hasNext()){
			result += currentNode.toString() + ",";
			currentNode = currentNode.getNext();
		}
		result += currentNode.toString() +  "]";
		return result;
	}

	/**
	 * ListNodes to construct the linked List with.
	 * @author Theresa Bultinck
	 *
	 * @param <T>
	 */
	@SuppressWarnings("hiding")
	private class ListNode<T>{
		
		private T element;
		private ListNode<T> next;
		private ListNode<T> prev;
		
		@SuppressWarnings("unused")
		public ListNode(T e, ListNode<T> n, ListNode<T> p){
			element = e;
			next = n;
			prev = p;
		}
		
		public ListNode(T e){
			element = e;
		}
		
		/**
		 * @return element
		 */
		public T getElement(){
			return element;
		}
		
		/**
		 * @return next
		 */
		public ListNode<T> getNext(){
			return next;
		}
		
		/**
		 * @return prev
		 */
		public ListNode<T> getPrev(){
			return prev;
		}
		
		/**
		 * Changes the next ListNode. 
		 * @param n
		 */
		public void setNext(ListNode<T> n){
			next = n;
		}
		
		/**
		 * Sets the previous ListNode.
		 * @param p
		 */
		public void setPrev(ListNode<T> p){
			prev = p;
		}
		
		/**
		 * @return true if there is no node next and false if there is a node next
		 */
		public boolean hasNext(){
			return (next != null);
		}
		
		/**
		 * @return true if there is no node before and false if there is a node before
		 */
		@SuppressWarnings("unused")
		public boolean hasPrev(){
			return (prev != null);
		}
		
		public String toString(){
			return element.toString();
		}
	}
	
	@Override
	public Iterator<T> iterator(){
		return new LinkedListIterator<T>(this);
	}
	
	/**
	 * Constructs an iterator to iterate through the Linked List.
	 * @author Theresa Bultinck
	 *
	 * @param <T>
	 */
	@SuppressWarnings("hiding")
	public class LinkedListIterator<T> implements Iterator<T>{
		private LinkedList<T>.ListNode<T> current;
		
		public LinkedListIterator(LinkedList<T> linkedList){
			this.current = linkedList.head;
		}
		
		@Override
		public boolean hasNext(){
			return current != null;
		}
		
		@Override
		public T next(){
			T element = current.getElement();
			current = current.getNext();
			return element;
		}
	}
}
