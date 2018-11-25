package datastructure.trie;

public class MyTrieNode {
	
	MyTrieNode[] children = new MyTrieNode[26];

	boolean isWord;
	
	MyTrieNode getNode(char c){
		return children[c - 'a'];
	}
}
