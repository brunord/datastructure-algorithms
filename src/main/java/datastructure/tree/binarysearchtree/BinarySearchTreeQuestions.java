package datastructure.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datastructure.linkedlist.MyLinkedList;

/**
 * Binary Tree questions from http://cslibrary.stanford.edu/110/BinaryTrees.html
 */
public class BinarySearchTreeQuestions {
	
	//Given a binary search tree and a "target" value, search the tree to see if it contains the target. 
	public static boolean lookup(MyBinarySearchTreeNode<Integer> root, MyBinarySearchTreeNode<Integer> node){
		
		//time cost= O(logN)
		//space cost= O(0)
		
		if(root == null)
			return false;
		
		if(root == node)
			return true;
		
		if(node.value < root.value)
			return lookup(root.leftNode, node);
		else
			return lookup(root.rightNode, node);
	}
	
	//given a binary search tree and a number, insert a new node with the given number into the tree in the correct place.
	public static MyBinarySearchTreeNode<Integer> insert(MyBinarySearchTreeNode<Integer> root, int value){
		
		//time cost= O(logN)
		//space cost= O(0)
		
		if(root == null)
			return new MyBinarySearchTreeNode<Integer>(value);
		
		if(value < root.value)
			root.leftNode = insert(root.leftNode, value);
		else
			root.rightNode = insert(root.rightNode, value);
		
		return root;
	}
	
	//Given a binary tree, count the number of nodes in the tree.
	public static int size(MyBinarySearchTreeNode<Integer> root){

		//time cost= O(N)
		//space cost= O(0)

		
		if(root == null)
			return 0;
		
		return 1 + size(root.leftNode) + size(root.rightNode);
	}

	//Given a binary tree, compute its "maxDepth" -- the number 
	//of nodes along the longest path from the root node down to the farthest leaf node. 
	public static int maxDepth(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(N)
		//space cost= O(0)
		
		if(root == null)
			return 0;
		
		return 1 + Math.max(maxDepth(root.leftNode), maxDepth(root.rightNode));
	}
	
	//Given a non-empty binary search tree (an ordered binary tree), return the 
	//minimum data value found in that tree. Note that it is not necessary to search the entire tree.
	//A maxValue() function is structurally very similar to this function. This can be solved with 
	//recursion or with a simple while loop.
	
	public static Integer minValue(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(logN)
		//space cost= O(0)
		
		if(root == null)
			return null;
		
		MyBinarySearchTreeNode<Integer> node = root;
		while(node.leftNode != null)
			node = node.leftNode;
		
		return node.value;
	}
	
	public static Integer maxValue(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(logN)
		//space cost= O(0)
		
		if(root == null)
			return null;
		
		MyBinarySearchTreeNode<Integer> node = root;
		while(node.rightNode != null)
			node = node.rightNode;
		
		return node.value;
	}
	
	//Given a binary search tree (aka an "ordered binary tree"), iterate over the nodes to print them out in increasing order. 
	public static void printOrder(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(N)
		//space cost= O(0)

		
		if(root != null){
			printOrder(root.leftNode);
			printOrder(root);
			printOrder(root.rightNode);
		}
	}
	
	//Given a binary tree, print out the nodes of the tree according to a bottom-up "postorder" traversal \
	//-- both subtrees of a node are printed out completely before the node itself is printed, 
	//and each left subtree is printed before the right subtree.
	public static void printPosOrder(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(N)
		//space cost= O(0)
		
		if(root != null){
			printOrder(root.leftNode);
			printOrder(root.rightNode);
			printOrder(root);
		}
	}
	
	//Given a binary tree and a sum, return true if the tree has a root-to-leaf path 
	//such that adding up all the values along the path equals the given sum. Return false if no such path can be found. 
	public static boolean hasSum(MyBinarySearchTreeNode<Integer> root, int sum){
		
		//time cost= O(N)
		//space cost= O(0)
		
		if(root == null)
			return sum == 0;
		
		return hasSum(root.leftNode, sum - root.value) || hasSum(root.rightNode, sum - root.value);
	}
	
	//Given a binary tree, print out all of its root-to-leaf paths as defined above. 
	public static void printPaths(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(N)
		//space cost= O(N^2)
		
		List<List<MyBinarySearchTreeNode<Integer>>> paths = getPaths(root, new ArrayList<MyBinarySearchTreeNode<Integer>>());
		
		for (List<MyBinarySearchTreeNode<Integer>> list : paths) {
			for (MyBinarySearchTreeNode<Integer> node : list) {
				System.out.print(node.value + " - ");
			}
			System.out.println();
		}
	}
	
	private static List<List<MyBinarySearchTreeNode<Integer>>> getPaths(MyBinarySearchTreeNode<Integer> root, 
			List<MyBinarySearchTreeNode<Integer>> pathSoFar){
		
		List<List<MyBinarySearchTreeNode<Integer>>> paths = new ArrayList<List<MyBinarySearchTreeNode<Integer>>>();
		
		if(root == null){
			paths.add(pathSoFar);
			return paths;
		}
			
		pathSoFar.add(root);

		if(root.leftNode == null && root.rightNode == null){
			paths.add(pathSoFar);
		}else{
			List<MyBinarySearchTreeNode<Integer>> pathLeft = new ArrayList<MyBinarySearchTreeNode<Integer>>(pathSoFar);
			List<MyBinarySearchTreeNode<Integer>> pathRight = new ArrayList<MyBinarySearchTreeNode<Integer>>(pathSoFar);
			if(root.leftNode != null)
				paths.addAll(getPaths(root.leftNode, pathLeft));
			if(root.rightNode != null)
				paths.addAll(getPaths(root.rightNode, pathRight));
		}

		return paths;
	}
	
	//Change a tree so that the roles of the left and right pointers are swapped at every node.
	public static void mirror(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(N)
		//space cost= O(0)
		
		if(root != null){
			MyBinarySearchTreeNode<Integer> temp = root.leftNode;
			root.leftNode = root.rightNode;
			root.rightNode = temp;
			
			mirror(root.leftNode);
			mirror(root.rightNode);
		}	
	}
	
	//For each node in a binary search tree, create a new duplicate node, and insert the duplicate as the left child of the original node. The resulting tree should still be a binary search tree.
	//So the tree... 
	//    2 
	//   / \ 
	//  1   3

	 //is changed to... 
	 //      2 
	 //     / \ 
	 //    2   3 
	 //   /   / 
	 //  1   3 
	 // / 
	 //1
	public static void duplicate(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(N)
		//space cost= O(N)
		
		if(root != null){
			MyBinarySearchTreeNode<Integer> node = new MyBinarySearchTreeNode<Integer>(root.value);
			node.leftNode = root.leftNode;
			duplicate(root.leftNode);
			duplicate(root.rightNode);
			root.leftNode = node;
		}	
	}
	
	//Given two binary trees, return true if they are structurally identical 
	//-- they are made of nodes with the same values arranged in the same way. 
	public static boolean isIdentical(MyBinarySearchTreeNode<Integer> root1, MyBinarySearchTreeNode<Integer> root2){
		
		//time cost= O(N)
		//space cost= O(0)
		
		if(root1 == null && root2 == null)
			return true;
		if(root1 != null && root2 == null)
			return false;
		if(root1 == null && root2 != null)
			return false;
		
		return (root1.value == root2.value) && isIdentical(root1.rightNode, root2.rightNode) 
				&& isIdentical(root1.leftNode, root2.leftNode);
	}
	
	//Suppose you are building an N node binary search tree with the values 1..N. 
	//How many structurally different  binary search trees are there that store those values? 
	//Write a recursive function that, given the number of distinct values, computes the number of structurally 
	//unique binary search trees that store those values. For example, countTrees(4) should return 14, 
	//since there are 14  structurally unique binary search trees that store 1, 2, 3, and 4. 
	//The base case is easy, and the recursion is short but dense. Your code should not construct 
	//any actual trees; it's just a counting problem.
	public static int countTrees(int n){
		
		int total = 0;
		
		if(n <= 1)
			return 1;
		else{
			//considering all values as root
			for (int rootValue = 1; rootValue <= n; rootValue++) {
				
				int countLeft = countTrees(rootValue - 1);
				int countRight = countTrees(n - rootValue);
				
				total += countLeft * countRight;
			}
		}
		
		return total;
	}
	
	//Suppose you have helper functions minValue() and maxValue() that return the min or max int value from a 
	//non-empty tree (see problem 3 above). Write an isBST() function that returns true if a tree is a 
	//binary search tree and false otherwise. Use the helper functions, and don't forget to check 
	//every node in the tree. It's ok if your solution is not very efficient. 
	public static boolean isBST(MyBinarySearchTreeNode<Integer> root){
		
		//time cost= O(N^2)
		//space cost= O(0)
		
		if(root == null)
			return true;
		
		if(maxValue(root.leftNode) > root.value)
			return false;
		if(minValue(root.rightNode) <= root.value)
			return false;
		
		return isBST(root.leftNode) && isBST(root.rightNode);
	}
	
	//VERSION 2 of previous questions. optimized
	public static boolean isBST2(MyBinarySearchTreeNode<Integer> root){
		//time cost= O(N)
		//space cost= O(0)
		
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static boolean isBST(MyBinarySearchTreeNode<Integer> root, int min, int max){
		
		if(root == null)
			return true;
		
		if(root.value >= max)
			return false;
		if(root.value < min)
			return false;
		
		return isBST(root.leftNode, min, root.value) || isBST(root.rightNode, root.value, max);
	}
	
	//Implement a function to check if a tree is balanced. For the purposes of this 
	//question, a balanced tree is defined to be a tree such that no two leaf nodes 
	//differ in distance from the root by more than one.
	public static boolean isBalanced(MyBinarySearchTreeNode<Object> root){
		
		if(root == null)
			return false;
		
		Set<Integer> leafLevels = getLeafLevels(root, 0, new HashSet<Integer>());
		
		int differentLevels = leafLevels.size();
		
		if(differentLevels > 2)
			return false;
		else if(differentLevels == 2)
			return Math.abs((Integer)leafLevels.toArray()[0] - (Integer)leafLevels.toArray()[1]) <= 1;
		else 
			return true;
	}
	
	private static Set<Integer> getLeafLevels(MyBinarySearchTreeNode<Object> node, int level, Set<Integer> leafLevels){
		
		if(node.leftNode == null && node.rightNode == null)//leaf
			leafLevels.add(level);
		if(node.leftNode != null)
			leafLevels.addAll(getLeafLevels(node.leftNode, level+1, leafLevels));
		if(node.rightNode != null)
			leafLevels.addAll(getLeafLevels(node.rightNode, level+1, leafLevels));
		
		return leafLevels;
	}
	
	//Given a sorted (increasing order) array, write an algorithm to create a binary tree with minimal height.
	public static MyBinarySearchTreeNode<Integer> buildMinimalHeightTree(int[] integers){
		
		if(integers.length == 0)
			return null;
		if(integers.length == 1)
			return new MyBinarySearchTreeNode<Integer>(integers[0]);
		
		return buildMinimalHeightTree(integers, 0, integers.length -1);
	}
	
	private static MyBinarySearchTreeNode<Integer> buildMinimalHeightTree(int[] integers, int begin, int end){
		
		if(end < begin)
			return null;
		
		int index = (begin + end)/2;
		
		MyBinarySearchTreeNode<Integer> node = new MyBinarySearchTreeNode<Integer>(index);
		
		node.leftNode = buildMinimalHeightTree(integers, begin, index-1);
		node.rightNode = buildMinimalHeightTree(integers, index+1, end);
		
		return node;
	}
	
	//Given a binary search tree, design an algorithm which creates a linked list 
	//of all the nodes at each depth (i.e., if you have a tree with depth D, you’ll have D linked lists).
	public static Map<Integer, MyLinkedList<Object>> loadLevelLinkedLists(MyBinarySearchTreeNode<Object> root){

		if(root == null)
			return new HashMap<Integer, MyLinkedList<Object>>();
		
		return loadLevelLinkedLists(root, 0, new HashMap<Integer, MyLinkedList<Object>>());
	}
	
	private static Map<Integer, MyLinkedList<Object>> loadLevelLinkedLists(MyBinarySearchTreeNode<Object> node, int level, Map<Integer, MyLinkedList<Object>> map){
		
		if(node != null){
			if(map.containsKey(level)){
				map.get(level).add(node);
			}else
				map.put(level, new MyLinkedList<Object>(node));
			
			map = loadLevelLinkedLists(node.leftNode, level+1, map);
			map = loadLevelLinkedLists(node.rightNode, level+1, map);
		}
		
		return map;
	}
	
	//Design an algorithm and write code to find the first common ancestor of two nodes 
	//in a binary tree. Avoid storing additional nodes in a data structure. 
	//NOTE: This is not necessarily a binary search tree.
	public static MyBinarySearchTreeNode<Integer> commonAncestor(MyBinarySearchTreeNode<Integer> root, MyBinarySearchTreeNode<Integer> node1,
			MyBinarySearchTreeNode<Integer> node2){
		
		if(root == null || node1 == null || node2 == null)
			return null;
		
		if(lookup(root.leftNode, node1) && lookup(root.rightNode, node2))
			return root;
		
		if(lookup(root.leftNode, node1)) //both on left side
			return commonAncestor(root.leftNode, node1, node2);
		else//both on right side
			return commonAncestor(root.rightNode, node1, node2);
	}
	
	//You have two very large binary trees: T1, with millions of nodes, and T2, 
	//with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.
	public static boolean isSubTree(MyBinarySearchTreeNode<Integer> tree1, MyBinarySearchTreeNode<Integer> tree2){
		
		if(tree2 == null)
			return true;
		
		if(tree1 == tree2)
			return true;
		else{
			return isSubTree(tree1.leftNode, tree2) || isSubTree(tree1.rightNode, tree2);
		}
	}
	//Write an algorithm to find the ‘next’ node (i.e., in-order successor) of a given node in a 
	//binary search tree where each node has a link to its parent.
	public static Node<Integer> nextInOrderNode(Node<Integer> node){
		
		if(node == null)
			return null;
		
		if(node.parent == null){ //root
			return mostLeftLeaf(node.rightNode);
		}else{
			if(node.value < node.parent.value){ // left node
				if(node.rightNode != null){
					return mostLeftLeaf(node.rightNode);
				}else
					return node.parent;
			}else{ //right node
				if(node.rightNode != null){
					return mostLeftLeaf(node.rightNode);
				}else{
					if(node.parent.parent == null) //parent is root
						return null;
					
					Node<Integer> temp = node.parent;
					while(temp != null ){
						if(temp.value < temp.parent.parent.value)//parent is left child	
							return temp.parent;
						else
							temp = temp.parent;
					}
					return temp;
				}
			}
		}
	}
	
	private static Node<Integer> mostLeftLeaf(Node<Integer> node){
		
		if(node == null)
			return node;
		
		while(node.leftNode != null)
			node = node.leftNode;
		
		return node;
	}
}

class Node<T> {
	
	T value;
	
	Node<T> parent;
	Node<T> leftNode;
	Node<T> rightNode;
	
	public Node(T value){
		this.value = value;
	}
}