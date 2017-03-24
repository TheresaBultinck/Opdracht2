package datastructure;

public class ListNode<T>{
	
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
	
	public T getElement(){
		return element;
	}
	
	public ListNode<T> getNext(){
		return next;
	}
	
	public ListNode<T> getPrev(){
		return prev;
	}
	
	public void setNext(ListNode<T> n){
		next = n;
	}
	
	public void setPrev(ListNode<T> p){
		prev = p;
	}
	
	public boolean hasNext(){
		return (next == null);
	}
	
	public boolean hasPrev(){
		return (prev == null);
	}
	
}
