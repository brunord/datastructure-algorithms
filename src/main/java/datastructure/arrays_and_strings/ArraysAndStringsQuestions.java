package datastructure.arrays_and_strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraysAndStringsQuestions {
	
	//Implement an algorithm to determine if a string has all unique characters. 
	public static boolean hasAllUniqueCharacters(String string){
		
		//time cost=O(N)
		//space cost=O(N)
		//N=string length
		
		Map<Integer, Boolean> charMap = new HashMap<Integer, Boolean>();
		
		for(char character: string.toCharArray()){
			if(charMap.containsKey(character))
				return false;
			else
				charMap.put((int)character, true);
		}
		return true;
	}
	
	//What if you can not use additional data structures?
	public static boolean hasAllUniqueCharactersWithoutExtraSpace(String string){
		
		//time cost=O(N^2)
		//space cost=O(0)
		//N=string length
		
		for (int i = 0; i < string.length(); i++) {
			for (int j = 0; j < i; j++) {
				if(string.charAt(j) == string.charAt(i))
					return false;
			}
		}
		return true;
	}
	
	//Write code to reverse a C-Style String. (C-String means that “abcd” is represented 
	//as five characters, including the null character.)
	public static char[] reverse(char[] cString){
		
		//time cost=O(N/2)
		//space cost=O(0)
		//N=string length
		
		if(cString == null || cString.length < 2  )
			return cString;
		
		int length = cString.length;

		for (int i = 0; i < (length - 1)/2; i++) {
			char temp = cString[length-2-i];
			cString[length-2-i] = cString[i];
			cString[i]=temp;
		}
		
		return cString;
	}
	
	//Design an algorithm and write code to remove the duplicate characters in a string 
	//without using any additional buffer. NOTE: One or two additional variables are fine. 
	//An extra copy of the array is not.
	//FOLLOW UP
	//Write the test cases for this method.
	public static char[] removeDuplicates(char[] string){
		
		//time cost=O(N^2)
		//space cost=O(0)
		//N=string length
		
		if(string == null || string.length < 2)
			return string;
		
		int reverseRunnerIndex = string.length-1;
		int currentIndex = string.length-1;
		
		while(reverseRunnerIndex >= 0){
			
			boolean duplicate = false;
			for (int i = 0; i < reverseRunnerIndex; i++) {
				if(string[reverseRunnerIndex] == string[i]){
					duplicate = true;
					break;
				}
			}
			
			if(!duplicate){
				string[currentIndex] = string[reverseRunnerIndex];
				currentIndex--;
			}
			reverseRunnerIndex--;
		}
		
		return Arrays.copyOfRange(string, currentIndex+1, string.length);
	}
	
	//Write a method to decide if two strings are anagrams or not.
	public static boolean areAnagrams(char[] str1, char[] str2){
		
		//time cost=O(2x(N log N) + N)
		//space cost=O(N)
		//N=string length
		
		if(str1 == null || str2 == null || str1.length != str2.length)
			return false;
		
		Arrays.sort(str1); //time cost=O(n log n)
		Arrays.sort(str2);//time cost=O(n log n)
		
		return Arrays.equals(str1, str2); //time cost=O(n)
	}
	
	//Write a method to decide if two strings are anagrams or not.
	public static boolean areAnagrams2(char[] str1, char[] str2){
		
		//time cost=O(2N)
		//space cost=O(256)
		//N=string length
		
		//solution more efficient but less elegant than the last one
		
		if(str1 == null || str2 == null || str1.length != str2.length)
			return false;
		
		int[] charSet = new int[256]; //assuming both are ascii strings. otherwise, bigger arrray.
		
		for (int i = 0; i < str1.length; i++) {
			charSet[str1[i]]++;
			charSet[str2[i]]--;
		}
		
		for (int i = 0; i < str1.length; i++) {
			if(charSet[str1[i]] != 0)
				return false;
		}
		
		return true;
	}
	
	//Write a method to replace all spaces in a string with ‘%20’.
	public static char[] replaceSpaces(char[] string){
		
		//time cost=O(2N)
		//space cost worst case=O(3N)
		//N=string length
		
		if(string == null)
			return string;
		
		int countSpaces = 0;
		
		for (int i = 0; i < string.length; i++) {//time cost=O(N)
			if(string[i] == ' ')
				countSpaces++;
		}
		
		char[] newString = new char[string.length + (2*countSpaces)];

		int currentIndex = newString.length-1;
		
		for (int i = string.length-1; i >= 0; i--) {//time cost=O(N)
			if(string[i] == ' '){
				newString[currentIndex] = '0';
				newString[currentIndex-1] = '2';
				newString[currentIndex-2] = '%';
				currentIndex = currentIndex - 3;
			}else{
				newString[currentIndex] = string[i];
				currentIndex--;
			}
		}
		
		return newString;
	}
	
	//Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0.
	public static void fillWithZero(int[][] matrix){
		
		//time cost=O(MxN * 2)
		//space cost=O(N + M)
		
		Map<Integer, Boolean> colZero = new HashMap<Integer, Boolean>();
		Map<Integer, Boolean> rowZero = new HashMap<Integer, Boolean>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				int col = i;
				int row = j;
				
				if(!colZero.containsKey(col) && !rowZero.containsKey(row)){
					if(matrix[col][row] == 0){
						colZero.put(col, true);
						rowZero.put(row, true);
					}
				}
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(colZero.containsKey(i) || rowZero.containsKey(j))
					matrix[i][j] = 0;
			}
		}
	}
	
	//Assume you have a method isSubstring which checks if one word is a substring of another. 
	//Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only 
	//one call to isSubstring (i.e., “waterbottle” is a rotation of “erbottlewat”).
	public static boolean isRotation(String str1, String str2){
		
		if(str1 == null || str2 == null || str1.length() != str2.length())
			return false;
		
		String temp = str1 + str1;
		
		return temp.contains(str2);
	}
	
	//Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, 
	//write a method to rotate the image by 90 degrees.
	public static int[][] rotate(int[][] matrix){
		
		//time cost=O(NxN)
		//space cost=O(NXN)
		
		int n = matrix.length;
		
		int[][] matrix2 = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix2[n - 1 - j][i] = matrix[i][j];
			}
		}
		
		return matrix2;
	}
	
	// Can you do this in place?
	public static void rotateInPlace(int[][] matrix){
		
		//time cost=O(N)
		//space cost=O(0)
		
		int n = matrix.length;
		
		int stopColIndex = -1;
		int stopRowIndex = -1;
		if(n%2==0){
			stopColIndex = (n+1)/2;
			stopRowIndex = (n+1)/2;
		}else{
			stopColIndex = (n)/2;
			stopRowIndex = (n+1)/2;
		}
		
		for (int i = 0; i < stopColIndex; i++) {
			for (int j = 0; j < stopRowIndex; j++) {
				
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = temp;
			}
		}
	}
}
