package datastructure.queue;

public class MyQueueNode <T>{

	T value;
	
	MyQueueNode<T> next;
	
	public MyQueueNode(T value) {
		this.value = value;
	}
}
