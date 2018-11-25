package datastructure.linkedlist;

public class MyLinkedListNode<T> {
	
	T value;
	
	MyLinkedListNode<T> next;
	
	public MyLinkedListNode(T value){
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public MyLinkedListNode<T> getNext() {
		return next;
	}

	public void setNext(MyLinkedListNode<T> next) {
		this.next = next;
	}
}
