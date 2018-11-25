package datastructure.stack;

public class MyStack<T> {
	
	MyStackNode<T> top;
	
	public MyStack(T value){
		this.top = new MyStackNode<T>(value);
	}
	
	public MyStack(){
	}
	//Cost = O(1)
	public boolean push(T value){
		
		if(isEmpty())
			this.top = new MyStackNode<T>(value);
		else{
			MyStackNode<T> newNode = new MyStackNode<T>(value);
			newNode.next = this.top;
			this.top = newNode;
		}
		
		return true;
	}
	//Cost = O(1)
	public MyStackNode<T> pop(){
		
		MyStackNode<T> node = null;
		
		if(!isEmpty()){
			node = this.top;
			this.top = this.top.next;
		}
		
		return node;
	}
	//Cost = O(1)
	public MyStackNode<T> peek(){
		
		MyStackNode<T> node = null;
		
		if(!isEmpty())
			node = this.top;
		
		return node;
	}
	//Cost = O(1)
	public T peekValue(){
		
		T value = null;
		
		if(!isEmpty())
			value = this.top.value;
		
		return value;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
}