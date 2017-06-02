/**
 * Basic data structure, a FIFO structure.
 * Supports basic operations: adding, deleting.
 *
 * Generic data types are supported.
 * @author  André Parsons-Legault
 */

public class MyQueue<T> {
	protected class Node<T> {
		T data;
		Node<T> link;
		
		public Node(T data) {
			this.data = data;
			link = null;
		}
	}
	
	private Node<T> first, last;
	private int size;
	
	public MyQueue() {
		first = null;
		last = null;
		size = 0;
	}
	
	public void addToQueue(T value) {
		Node<T> add = new Node<T>(value);
		if(isEmpty()) {
			first = add;
			last = add;
		}
		else {
			last.link = add;
			last = add;
		}
		size++;
	}
	
	public T deleteFromQueue() {
		if(!isEmpty()) {
			T data = first.data;
			first = first.link;
			size--;
			return data;
		}
		else return null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	
	public T lookUp() {
		return isEmpty()? null: first.data;
	}
	
	public void clearQueue() {
		size = 0;
		first = null;
		last = null;
	}
}
