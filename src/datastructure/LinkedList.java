package datastructure;

/**
 * Linked List
 * @author Theresa Bultinck
 *
 * @param <T> type of the parameter
 */
public class LinkedList<T> {
	
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
	public ListNode<T> first(){
		return head;
	}
	
	/**
	 * 
	 * @return the tail of the list
	 */
	public ListNode<T> last(){
		return tail;
	}
	
	private void update(ListNode<T> first,ListNode<T> second, ListNode<T> third){
		first.setNext(second);
		second.setPrev(first);
		second.setNext(third);
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
			addedNode = head; 
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
			addedNode = tail;
		}
	}
	
	/**
	 * Adds an element to the linked list
	 * @param element
	 */
	public void add(T element){
		ListNode<T> addedNode = new ListNode<T> (element);
		ListNode<T> beforeTail = tail.getPrev();
		update(beforeTail,tail,addedNode);
	}

	private class ListNode<T>{
		
		private T element;
		private ListNode<T> next;
		private ListNode<T> prev;
		
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
		
		public void setNext(ListNode<T> n){
			next = n;
		}
		
		public void setPrev(ListNode<T> p){
			prev = p;
		}
		
		/**
		 * @return true if there is no node next and false if there is a node next
		 */
		public boolean hasNext(){
			return (next == null);
		}
		
		/**
		 * @return true if there is no node before and false if there is a node before
		 */
		public boolean hasPrev(){
			return (prev == null);
		}
		
	}
}
