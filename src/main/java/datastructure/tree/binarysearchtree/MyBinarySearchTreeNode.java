package datastructure.tree.binarysearchtree;

public class MyBinarySearchTreeNode<T> {
	
	T value;
	
	MyBinarySearchTreeNode<T> leftNode;
	MyBinarySearchTreeNode<T> rightNode;
	
	public MyBinarySearchTreeNode(T value){
		this.value = value;
	}
}
