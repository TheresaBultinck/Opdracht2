package datastructure;

public class Tree<T> {
	
	private TreeNode<T> root; 
	
	public Tree(TreeNode<T> node){
		root = node;
	}
	
	public TreeNode<T> getRoot(){
		return root;
	}
	
}
