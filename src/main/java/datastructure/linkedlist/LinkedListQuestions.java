package datastructure.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LinkedListQuestions {
	
	//Write code to remove duplicates from an unsorted linked list.
	public static void removeDuplicates(MyLinkedList<String> linkedList){
		
		//time cost=O(N)
		//space cost=O(N)
		//N=linked list nodes

		if(linkedList.head != null){
			
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			
			MyLinkedListNode<String> runner = linkedList.head;
			map.put(runner.value, true);
			
			MyLinkedListNode<String> previous = runner;
			
			runner = runner.next;

			while(runner != null){
				if(map.containsKey(runner.value)){
					previous.next = runner.next;
					runner = runner.next;
				}else{
					map.put(runner.value, true);
					previous = previous.next;
					runner = runner.next;
				}
			}
		}
	}
	
	//How would you solve this problem if a temporary buffer is not allowed?
	public static void removeDuplicatesInPlace(MyLinkedList<String> linkedList){
		
		//time cost=O(N^2)
		//space cost=O(0)
		//N=linked list nodes

		if(linkedList.head != null){

			MyLinkedListNode<String> current = linkedList.head;
			
			MyLinkedListNode<String> previous = current;
			MyLinkedListNode<String> runner = current.next;
			
			while(runner != null){
				if(runner.value.equals(current.value)){
					previous.next = runner.next;
				}else{
					previous = previous.next;
				}
				runner = runner.next;
				
				if(runner == null && current.next != null){
					current = current.next;
					previous = current;
					runner = current.next;
				}
			}
		}
	}
	
	//Implement an algorithm to find the nth to last element of a singly linked list.
	public static String findNth(MyLinkedList<String> linkedList, int nth){
		
		//time cost=O(N)
		//space cost=O(0)
		//N=linked list nodes
		
		//an elegant and more expensive solution could be done using a stack
		
		MyLinkedListNode<String> frontRunner = linkedList.head;
		MyLinkedListNode<String> backRunner = linkedList.head;

		int counter = nth;
		
		while(counter > 0){
			
			frontRunner = frontRunner.next;
			counter--;
			
			if(frontRunner == null)
				return null;
		}
		
		while(frontRunner.next != null){
			frontRunner = frontRunner.next;
			backRunner = backRunner.next;
		}
		
		return backRunner.value;
	}
	
	//Implement an algorithm to delete a node in the middle of a single linked list, given only access to that node.
	public static void deleteLinkedListNode(MyLinkedListNode<Object> node){

		//time cost=O(1)
		//space cost=O(0)
		// Assuming that a node "in the middle", has a non-null next node
		node.value = node.next.value;
		node.next = node.next.next;
	}
	
	//You have two numbers represented by a linked list, where each node contains a single digit. 
	//The digits are stored in reverse order, such that the 1’s digit is at the head of the list. 
	// Write a function that adds the two numbers and returns the sum as a linked list.
	//EXAMPLE
	//Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
	//Output: 8 -> 0 -> 8
	
	public static MyLinkedList<Integer> sum(MyLinkedList<Integer> list1, MyLinkedList<Integer> list2){
		
		//time cost=O(N)
		//space cost=O(N)
		//N = number of nodes of the biggest linkedList passed as argument
		
		if(list1 == null)
			return list2;
		if(list2 == null)
			return list1;
		
		MyLinkedList<Integer> sum = new MyLinkedList<Integer>();
		
		MyLinkedListNode<Integer> currentNode1 = list1.head;
		MyLinkedListNode<Integer> currentNode2 = list2.head;
		
		int extra = 0;
		
		while(currentNode1 != null && currentNode2 != null){
			
			int temp = currentNode1.value + currentNode2.value + extra;
			
			if(temp > 9){
				extra = 1;
				sum.add(temp - 10);
			}else{
				extra = 0; 
				sum.add(temp);
			}
			
			currentNode1 = currentNode1.next;
			currentNode2 = currentNode2.next;
		}
		
		if(currentNode1 != null){
			while(currentNode1 != null){
				int temp = currentNode1.value + extra;
				
				if(temp > 9){
					extra = temp - 9;
					sum.add(temp - 10);
				}else{
					extra = 0;
					sum.add(temp);
				}
				currentNode1 = currentNode1.next;
				if(currentNode1 == null && extra > 0)
					sum.add(extra);
			}
		}
		
		if(currentNode2 != null){
			while(currentNode2 != null){
				int temp = currentNode2.value + extra;
				
				if(temp > 9){
					extra = temp - 9;
					sum.add(temp - 10);
				}else{
					extra = 0;
					sum.add(temp);
				}
				currentNode2 = currentNode2.next;
				if(currentNode2 == null && extra > 0)
					sum.add(extra);
			}
		}
		
		return sum;
	}
	
	//Given a circular linked list, implement an algorithm which returns node at the beginning of the loop.
	//DEFINITION
	//Circular linked list: A (corrupt) linked list in which a node’s next pointer points 
	//to an earlier node, so as to make a loop in the linked list.
	//EXAMPLE
	//input: A -> B -> C -> D -> E -> C [the same C as earlier]
	//output: C
	public static MyLinkedListNode<Object> getStartLoopNode(MyLinkedList<Object> linkedList){
		
		MyLinkedListNode<Object> runner1 = linkedList.head;
		MyLinkedListNode<Object> runner2 = linkedList.head;
		
		while(runner2.next != null){
			
			runner1 = runner1.next;
			runner2 = runner2.next.next;
			
			if(runner1 == runner2) // two runners meet up inside the loop
				break;
		}
		
		if(runner2.next == null) // invalid input
			return null;
		
		//at this moment, the distance to the start of the loop, is the same of 
		//the distance from the head to the start of the loop. reason: when runner1 enters to loop,
		// lets say at the step k, runner2 is k steps ahead (for each step, runner2 increments by 1 the number
		//of steps ahead). so, they will meet up inside the loop k steps before the start.
		runner1 = linkedList.head;
		
		while(runner1 != runner2){
			runner1 = runner1.next;
			runner2 = runner2.next;
		}
		
		return runner1;
	}
}
