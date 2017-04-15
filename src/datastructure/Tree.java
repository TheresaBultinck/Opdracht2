package datastructure;

public class Tree<T> {
	
	private TreeNode<T> root; 
	
	public Tree(TreeNode<T> node){
		root = node;
	}
	
	public TreeNode<T> getRoot(){
		return root;
	}
	
	//Interface: ooit een element van maken? 
	private class TreeNode<T>{
		
		private TreeNode<T> parent = null; 
		private LinkedList<TreeNode<T>> children = new LinkedList<TreeNode<T>>();
		private T element;
		
		public TreeNode(T e){
			this.element = e;
		}
		
		/**
		 * Adds a child to a linked list of children
		 * @param child 
		 * @return true if a parent has been added and thus also a child and 
		 * false if it failed to add a parent and thus no child
		 */
		public boolean addChild(TreeNode<T> child){
			if (child.addParent(this)){
				children.add(child);
				return true; 
			}
			return false;
		}
		
		/**
		 * Adds a parent
		 * @param parent
		 * @return true if there isn't yet a parent and false if there is already a parent present
		 */
		public boolean addParent(TreeNode<T> parent){
			if(this.isRoot()){
				this.parent = parent;
				return true;
			}
			return false;
		}
		
		/**
		 * Check if there is a parent
		 * @return true if there is no parent and false if there is already a parent
		 */
		public boolean isRoot(){
			return parent == null;
		}
	}
}
