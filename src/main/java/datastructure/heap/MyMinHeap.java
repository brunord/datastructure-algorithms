package datastructure.heap;

public class MyMinHeap {

int[] array;
	
	int counter;
	int size;
	
	public MyMinHeap(int size){
		this.size = size;
		this.array = new int[size];
		this.counter = 0;
	}
	
	public MyMinHeap(){
		this.size = 50; //default
		this.array = new int[size];
		this.counter = 0;
	}
	
	public int min(){
		return array[0];
	}
	
	public void put(int value){
		
		if(value > 0){
			
			array[counter] = value;
			
			bubbleUp(counter);
			
			counter++;
		}
	}
	
	public int pop(){
		
		int max = array[0];
		array[0] = 0;
		
		if(counter == 1){
			counter--;
			
		}else if(counter > 1){
			switchValues(0, counter - 1);
			counter--;
			
			bubbleDown(0);
		}
		return max;
	}

	private void bubbleUp(int index){

		int parentIndex = getParentIndex(index);
		
		while(parentIndex >= 0 && (array[index] < array[parentIndex])){
			
			switchValues(index, parentIndex);
			
			index = parentIndex;
			parentIndex = getParentIndex(parentIndex);
		}
	}
	
	private void bubbleDown(int index){

		int lowerChildIndex = getLowerChildIndex(index);
		
		while(lowerChildIndex < counter && (array[index] > array[lowerChildIndex])){
			
			switchValues(index, lowerChildIndex);
			
			index = lowerChildIndex;
			lowerChildIndex = getLowerChildIndex(lowerChildIndex);
		}
	}
	
	private int getParentIndex(int index){
		
		return (index -1)/2;
	}
	
	private void switchValues(int index1, int index2){
		
		int temp = array[index2];
		array[index2] = array[index1];
		array[index1] = temp;
	}

	private int getLeftChildIndex(int index){
		return (index*2)+1;
	}
	
	private int getRightChildIndex(int index){
		return (index*2)+2;
	}
	
	private int getLowerChildIndex(int index){
		
		int lowerValueIndex = -1;
		
		int leftValue = array[getLeftChildIndex(index)];
		int rightValue = array[getRightChildIndex(index)];
		
		if(rightValue == 0){
			return leftValue;
		}
		if(leftValue == 0){
			return rightValue;
		}
		
		if(leftValue >= rightValue){
			lowerValueIndex = getRightChildIndex(index);
		}else if(leftValue < rightValue){
			lowerValueIndex = getLeftChildIndex(index);
		}
		
		return lowerValueIndex;
	}

	
}
