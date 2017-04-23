package datastructure;

/**
 * Constructs a tree that consists of TreeNodes of generic type T
 * @author Theresa Bultinck
 * @param <T>
 */
public class Tree<T> {
	
	private TreeNode<T> root; 
	
	public Tree(){
		root = null;
	}
	
	public Tree(T element){
		root = new TreeNode<>(element);
	}
	
	/**
	 * After checked if there is a root in the tree, add a root if it returns false. If true don't add a root.
	 * @param rootElement
	 * @return the root of type TreeNode
	 */
	public TreeNode<T> setRoot(T rootElement) {
		if(!hasRoot()) 
			this.root = new TreeNode<>(rootElement);
		return root;
	}
	
	/**
	 * Checks if there is already a root in the tree
	 * @return true if there is a root and false if the tree is empty, so doesn't have a root yet
	 */
	public boolean hasRoot() {
		return root != null;
	}

	/**
	 * Returns the root of the tree
	 * @return the root of type TreeNode
	 */
	public TreeNode<T> getRoot(){
		return root;
	}
	
	/**
	 * Inner class TreeNode to construct the tree with.
	 * @author Theresa Bultinck
	 * @param <T>
	 */
	@SuppressWarnings("hiding")
	public class TreeNode<T>{
		
		private TreeNode<T> parent = null; 
		private LinkedList<TreeNode<T>> children = new LinkedList<TreeNode<T>>();
		private T element;
		
		public TreeNode(T e){
			this.element = e;
		}


		/**
		 * Adds a child to a linked list of children
		 * @param child 
		 * @return child that is added as a type of TreeNode
		 */
		public TreeNode<T> addChild(T child){
			TreeNode<T> childNode = new TreeNode<>(child);
			if (childNode.addParent(this)){
				children.add(childNode); 
			}
			return childNode;
		}
		
		/**
		 * Adds a parent
		 * @param parent
		 * @return true if there isn't yet a parent and false if there is already a parent present
		 */
		private boolean addParent(TreeNode<T> parent){
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
		
		/**
		 * Returns the parent of a certain child
		 * @return parent of the type TreeNode
		 */
		public TreeNode<T> getParent(){
			return parent;
		}
		
		public T getElement(){
			return element;
		}
		
		@Override
		public String toString(){
			if (parent == null){
				return "Node: " + element.toString() + "with children" + children.toString();
			}
			return "Node: " + element.toString() + "with children" + children.toString() + "parent: " 
				+ parent.toString();
 		}
	}
}