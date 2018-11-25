package datastructure.trie;

public class MyTrie {

	MyTrieNode root = new MyTrieNode();
	
	//time cost = O(N)
	//space cost = O(0)
	//N = word length
	public void addWord(String word){

		MyTrieNode node = root;
		
		if(word != null && word.length() > 0){
			
			for (int i = 0; i < word.length(); i++) {
				
				int character = word.toLowerCase().charAt(i) - 'a';

				MyTrieNode temp = node.children[character];

				if(temp == null)
					node.children[character] = new MyTrieNode();
				
				if(i == word.length() -1)
					node.children[character].isWord = true;

				node = node.children[character];
			}
		}
	}
	
	//time cost = O(N)
	//space cost = O(0)
	//N = string length
	public MyTrieNode searchNode(String string){
		
		if(string == null || string.length() == 0)
			return null;
		
		MyTrieNode node = root;
		
		for (int i = 0; i < string.length(); i++) {
			node = node.children[string.charAt(i) - 'a'];
			
			if(node == null)
				return null;
		}
		return node;
	}
	//time cost = O(N)
	//space cost = O(0)
	//N = word length
	public boolean hasWord(String word){

		MyTrieNode node = searchNode(word);
		
		return node != null && node.isWord;
	}
	
	//time cost = O(N*26)
	//space cost = O(0)
	//N = string length
	public  boolean hasWordRegex(String string){
		
		if(string == null || string.length() == 0)
			return false;
		
		return hasWordRegex(root, string);
	}
	
	//regex with character '+' representing any character.
	//TODO: implement the same with '*'
	private static boolean hasWordRegex(MyTrieNode node, String string){
		
		if(string == null || string.length() == 0 || node == null)
			return false;
		
		char character = string.charAt(0);

		if(character == '+'){
			MyTrieNode[] children = node.children;
			for (int i = 0; i < children.length; i++) {
				boolean found = hasWordRegex(node, (char)('a' + i) + string.substring(1, string.length()));
				if(found)
					return true;
			}
		}
		else{
			MyTrieNode child = node.getNode(character);
			
			if(string.length() == 1 && child != null && child.isWord)
				return true;
			else
				return hasWordRegex(child, string.substring(1, string.length()));
		}
		
		return false;
	}
	
	//time cost = O(N)
	//space cost = O(0)
	//N = prefix length
	public boolean hasPrefix(String prefix){

		return searchNode(prefix) != null;
	}
}
