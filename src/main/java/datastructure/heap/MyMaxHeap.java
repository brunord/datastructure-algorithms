package datastructure.heap;
/**
 * 
 * Max heap. Only positive integers greater than 0 is allowed, 
 * for implementation facilities reasons.
 * 
 * time cost:
 * push: logn
 * pop: 1
 * 
 *
 */
public class MyMaxHeap {
	
	int[] array;
	
	int counter;
	int size;
	
	public MyMaxHeap(int size){
		this.size = size;
		this.array = new int[size];
		this.counter = 0;
	}
	
	public MyMaxHeap(){
		this.size = 50; //default
		this.array = new int[size];
		this.counter = 0;
	}
	
	public int max(){
		return array[0];
	}
	
	public void put(int value){
		
		if(value > 0){
			
			array[counter] = value;
			
			bubbleUp(counter);
			
			counter++;
		}
	}
	
	public boolean isEmpty(){
		return counter == 0;
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
		
		while(parentIndex >= 0 && (array[index] > array[parentIndex])){
			
			switchValues(index, parentIndex);
			
			index = parentIndex;
			parentIndex = getParentIndex(parentIndex);
		}
	}
	
	private void bubbleDown(int index){

		int higherChildIndex = getHigherChildIndex(index);
		
		while(higherChildIndex < counter && (array[index] < array[higherChildIndex])){
			
			switchValues(index, higherChildIndex);
			
			index = higherChildIndex;
			higherChildIndex = getHigherChildIndex(higherChildIndex);
		}
	}
	
	private int getParentIndex(int index){
		
		return (index - 1)/2;
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
	
	private int getHigherChildIndex(int index){
		
		int higherValueIndex = -1;
		
		int leftValue = array[getLeftChildIndex(index)];
		int rightValue = array[getRightChildIndex(index)];
		
		if(leftValue >= rightValue){
			higherValueIndex = getLeftChildIndex(index);
		}else{
			higherValueIndex = getRightChildIndex(index);
		}
		
		return higherValueIndex;
	}
	
	public static void main(String[] args) {
		MyMaxHeap heap = new MyMaxHeap();
		
		heap.put(23);
		heap.put(45);
		heap.put(-1);
		heap.put(23);
		heap.put(0);
		heap.put(67);
		
		while(!heap.isEmpty())
			System.out.println(heap.pop());
		
	}
}
