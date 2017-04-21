package datastructure;

public class Tree<T> {
	
	private TreeNode<T> root; 
	
	public Tree(){
		root = null;
	}
	
	public Tree(T element){
		root = new TreeNode<>(element);
	}
	
	public TreeNode<T> setRoot(T rootElement) {
		if(!hasRoot()) 
			this.root = new TreeNode<>(rootElement);
		return root;
	}
	
	public boolean hasRoot() {
		return root != null;
	}

	public TreeNode<T> getRoot(){
		return root;
	}
	
	//Interface: ooit een element van maken? 
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
		 * @return true if a parent has been added and thus also a child and 
		 * false if it failed to add a parent and thus no child
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
			return "Node: " + element.toString() + "with children" + children.toString() + "parent: " + parent.toString();
 		}
	}
}