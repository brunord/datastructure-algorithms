package datastructure.queue;

public class MyQueue <T>{
	
	MyQueueNode<T> first;
	MyQueueNode<T> last;
	
	public MyQueue(){
	}
	
	public MyQueue(T value){
		this.first = new MyQueueNode<T>(value);
		this.last = this.first;
	}
	
	//Cost = O(1)
	public boolean enqueue(T value){
		
		if(isEmpty()){
			this.first = new MyQueueNode<T>(value);
			this.last = this.first;
		}else{
			MyQueueNode<T> newNode = new MyQueueNode<T>(value);
			this.last.next = newNode;
			this.last = newNode;
		}
		return true;
	}
	//Cost = O(1)
	public MyQueueNode<T> dequeue(){
		
		MyQueueNode<T> node = null;
		
		if(!isEmpty()){
			node = this.first;
			this.first = this.first.next;
		}
		
		return node;
	}
	
	public boolean isEmpty(){
		return this.first == null;
	}
	
}
