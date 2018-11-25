package datastructure.graph;

import java.util.ArrayList;
import java.util.List;

public class MyDirectedGraphNode<T> {

	private T value;
	
	private boolean visited;
	
	List<MyDirectedGraphNode<T>> adjacentNodes;
	
	public MyDirectedGraphNode(T value){
		this.value = value;
		adjacentNodes = new ArrayList<MyDirectedGraphNode<T>>();
	}
	
	public MyDirectedGraphNode(){
		adjacentNodes = new ArrayList<MyDirectedGraphNode<T>>();
	}
	
	public void addNode(MyDirectedGraphNode<T> node){
		adjacentNodes.add(node);
	}
	
	public T getValue(){
		this.visited = true;
		return value;
	}
	
	public boolean isVisited(){
		return this.visited;
	}
}
