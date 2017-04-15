package datastructure;

//Interface: ooit een element van maken? 
public class TreeNode<T>{
	
	private TreeNode<T> parent = null; 
	private LinkedList<TreeNode<T>> children = new LinkedList<TreeNode<T>>();
	private T element;
	
	public TreeNode(T e){
		this.element = e;
	}
	
	//duidelijk maken true/false case - gelukt om parent toe te voegen child toevoegen
	public boolean addChild(TreeNode<T> child){
		if (child.addParent(this)){
			children.add(child);
			return true; 
		}
		return false;
	}
	
	//duidelijk true/false uitleggen
	//als er nog geen parent is true en anders false
	public boolean addParent(TreeNode<T> parent){
		if(this.isRoot()){
			this.parent = parent;
			return true;
		}
		return false;
	}
	
	public boolean isRoot(){
		return parent == null;
	}
}
