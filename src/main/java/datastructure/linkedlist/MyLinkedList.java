package datastructure.linkedlist;

public class MyLinkedList<T> {

	MyLinkedListNode<T> head;
	
	public MyLinkedList(T value){
		this.head = new MyLinkedListNode<T>(value);
	}
	
	public MyLinkedList(){
		this.head = null;
	}
	//Cost = O(N)
	public void add(T value){
		
		if(head == null){
			this.head = new MyLinkedListNode<T>(value);
		}else{
			MyLinkedListNode<T> temp = head;
			
			while(temp.next != null)
				temp = temp.next;
			
			temp.next = new MyLinkedListNode<T>(value);	
		}
	}
	//Cost = O(N)
	public void delete(T value){
		
		if(value.equals(head.value))
			head = head.next;
		else{
			MyLinkedListNode<T> temp = head;
			
			while(temp.next != null){
				if(temp.next.value.equals(value)){
					temp.next = temp.next.next;
					break;
				}
				temp = temp.next;
			}
		}
	}
	
	public MyLinkedListNode<T> getHead(){
		return this.head;
	}
}
