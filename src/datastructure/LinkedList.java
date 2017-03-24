package datastructure;

public class LinkedList<T> {
	
	private ListNode<T> head = null;
	private ListNode<T> tail = null;
	private int size = 0;
	
	public LinkedList(ListNode<T> node){
		head = tail = node;
		size++;
	}
	
	public boolean isEmpty(){
		return (size == 0);
	}
	
	public int getSize(){
		return size;
	}
	
	public ListNode<T> first(){
		return head;
	}
	
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
	
	public void addBefore(ListNode<T> node, T element){
		ListNode<T> addedNode = new ListNode<T>(element);
		ListNode<T> before = node.getPrev();
		update(before,addedNode,node);
		//Controle of node head is en dan eventueel updaten
	}
	
	public void addAfter(ListNode<T> node, T element){
		ListNode<T> addedNode = new ListNode<T>(element);
		ListNode<T> after = node.getNext();
		update(node,addedNode,after);
		//controle of node tail is en dan eventueel updaten
	}
	
}
