package datastructure.stack;

public class MyStackNode<T> {

	T value;
	
	MyStackNode<T> next;
	
	public MyStackNode(T value){
		this.value = value;
	}
}
