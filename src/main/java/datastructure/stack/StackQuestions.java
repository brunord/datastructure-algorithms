package datastructure.stack;

import java.util.PriorityQueue;

public class StackQuestions<T> {
	
	//How would you design a stack which, in addition to push and pop, 
	//also has a function min which returns the minimum element? 
	//Push, pop and min should all operate in O(1) time.

	//Approach 1: Using a extra stack to keep the current minimum on the top
	// - each new element pushed, compare with the top of minStack. If less or equals than the top
	//of minStack, push it into the stack, AND into the minStack. If not, do nothing
	// - for each pop operation, verify if the value retrieved is less or equal than the top of 
	// minStack. If yes, pop the value from both stacks, if not, pop the value only from the main stack
	
	//Approach 2: add an additional attribute to stackNode, to keep track of 
	//the min value at the moment the node is pushed into the stack.
	//this approach is more expensive in terms of space
	
	//=============
	
/*	Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
	Therefore, in real life, we would likely start a new stack when the previous stack 
	exceeds some threshold. Implement a data structure SetOfStacks that mimics 
	this. SetOfStacks should be composed of several stacks, and should create a 
	new stack once the previous one exceeds capacity. SetOfStacks.push() 
	and SetOfStacks.pop() should behave identically to a single stack 
	(that is, pop() should return the same values as it would if there were just a single stack).
	FOLLOW UP
	Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.*/
	
	public SetOfStacks<T> getSetOfStacks(){
		return new SetOfStacks<T>();
	}
	
	class SetOfStacks<S>{
		
		private static final int THRESOLD = 4;
		
		private int maxStacks = 10;
		
		@SuppressWarnings("unchecked")
		MyStack<S>[] stacks = new MyStack[maxStacks];
		int[] stackCounters = new int[maxStacks];
		
		private int currentStack = 0;

		public void push(S value){

			if(stackCounters[currentStack] == THRESOLD){
				
				if(stacks.length == maxStacks)
					duplicateStackSet();
				currentStack++;
			}
			if(stacks[currentStack] == null)
				stacks[currentStack] = new MyStack<>();
			stacks[currentStack].push(value);
			stackCounters[currentStack]++;
		}
		
		public MyStackNode<S> pop(){
			
			MyStackNode<S> node = null;
			
			if(!isEmpty()){
				node = stacks[currentStack].pop();
				stackCounters[currentStack]--;
				
				while(stackCounters[currentStack] == 0 && currentStack > 0)
					currentStack--;
			}
			return node;
		}
		
		public MyStackNode<S> popAt(int index){
			
			if(index >= 0 && index <= currentStack && stackCounters[index] > 0){
				MyStackNode<S> node = stacks[index].pop();
				stackCounters[index]--;
				if(index == currentStack){
					while(stackCounters[currentStack] == 0 && currentStack > 0)
						currentStack--;
				}
				return node;
			}else
				return null;
		}
		
		public boolean isEmpty(){
			return currentStack == 0 && (stacks[currentStack] == null || stacks[currentStack].isEmpty());
		}
		
		//time cost = O(maxStacks)
		private void duplicateStackSet(){
			
			this.maxStacks = this.maxStacks*2;
			
			@SuppressWarnings("unchecked")
			MyStack<S>[] temp = new MyStack[maxStacks];
			int[] tempCounters = new int[maxStacks];
			
			for (int i = 0; i < stacks.length; i++) {
				temp[i] = stacks[i];
				tempCounters[i] = stackCounters[i];
			}
			this.stacks = temp;
			this.stackCounters = tempCounters;
		}
	}
	
	
	//In the classic problem of the Towers of Hanoi, you have 3 rods and N disks of different sizes 
	// which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size 
	// from top to bottom (e.g., each disk sits on top of an even larger one). You have the following constraints:
	//(A) Only one disk can be moved at a time.
	//(B) A disk is slid off the top of one rod onto the next rod.
	//(C) A disk can only be placed on top of a larger disk.
	//Write a program to move the disks from the first rod to the last using Stacks.
	
	static class HanoiSolver{
		
		MyStack<Integer> source = new MyStack<>();
		MyStack<Integer> buffer = new MyStack<>();
		MyStack<Integer> target = new MyStack<>();
		
		int n;
		
		public HanoiSolver(int n){
			
			this.n = n;
			
			while(n > 0){
				source.push(n);
				n--;
			}
		}

		public void solve(){
			this.move(source, buffer, target, n);
		}
		
		private void move(MyStack<Integer> source, MyStack<Integer> buffer, MyStack<Integer> target, int n){
			
			if(n == 2){
				this.movingTop(source, buffer);
				this.movingTop(source, target);
				this.movingTop(buffer, target);
			}else{
				move(source, target, buffer, n-1);
				this.movingTop(source, target);
				move(buffer, source, target, n-1);
			}
		}
		
		private void movingTop(MyStack<Integer> source, MyStack<Integer> target){
			
			if(target.isEmpty() || target.peek().value >= source.peekValue()){
				target.push(source.pop().value);
			}else{
				System.out.println("ERROR!!!");
				System.exit(0);
			}
		}
	}
	
	//Write a program to sort a stack in ascending order. 
	//You should not make any assumptions about how the stack is implemented. 
	//The following are the only functions that should be used to
	//write this program: push | pop | peek | isEmpty.
	public static void sortAsc(MyStack<Integer> stack){
		
		//time cost = O (N + NlogN + N)
		//space cost = O (N)
		
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		
		while(!stack.isEmpty())
			heap.add(stack.pop().value);
		
		while(!heap.isEmpty())
			stack.push(heap.poll());
	}
}
