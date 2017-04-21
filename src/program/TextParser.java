package program;


import datastructure.Tree;
import datastructure.Tree.TreeNode;

public class TextParser {
	
	private Tree<Tag> tagTree;
	private Tree<Tag>.TreeNode<Tag> currentParent = null;
	
	public LinkedList<AbstractStatus> parse(String text){
		tagTree = new Tree<>();
		currentParent = null;
		return parse(text, 0, new LinkedList<AbstractStatus>());
	}
	/**
	 * Method to parse through a typed String. 
	 * @param typed
	 */
	private LinkedList<AbstractStatus> parse(String subs, int offset,LinkedList<AbstractStatus> stati) {
		
		int startIndex = subs.indexOf("<", 0);
		int endIndex = subs.indexOf(">",0);
		
		if (startIndex < 0 && endIndex < 0){
			return stati; 
		}
		else if (startIndex < 0){
			stati.add(new AbstractStatus(offset, StatusProperty.ERROR));
			return stati;
		}
		else if (endIndex < 0) {
			stati.add(new AbstractStatus(offset, StatusProperty.ERROR));
			return stati;
		}
		else if (endIndex < startIndex ){
			stati.add(new AbstractStatus(offset, StatusProperty.ERROR));
			return stati;
		}
		else {
			Tag tag = new Tag();
			if (subs.substring(startIndex+1, startIndex+2).equals("/")){
				tag.setOffset(startIndex+offset+1);
				tag.setName(subs.substring(startIndex+2,endIndex));
				tag.close();
			}
			else {
				tag.setOffset(startIndex+offset+1);
				tag.setName(subs.substring(startIndex+1, endIndex));
			}
			AbstractStatus status = addToTree(tag);
			if (StatusProperty.ERROR == status.getStatusProperty()) {
				stati.add(status);
			}
			return parse(subs.substring(endIndex+1),offset+endIndex+1, stati);
		}
	}
	
	private AbstractStatus addToTree(Tag t) {
		if(!tagTree.hasRoot() && t.isOpen()) {
			this.currentParent = tagTree.setRoot(t);
		}
		else if (tagTree.hasRoot() && this.currentParent == null) {
			return new Status(t, StatusProperty.ERROR);
		}
		else if (t.isOpen()){
			this.currentParent = currentParent.addChild(t); 
		}
		else if (t.isClose() && t.equals(currentParent.getElement()) {
			this.currentParent = currentParent.getParent();
		}
		else {
			return new Status(t, StatusProperty.ERROR);
		}
		return new Status(t, StatusProperty.OK);
	}

	public Tag getParent(){
		return currentParent.getElement();
	}
	
	public boolean hasParent(){
		return currentParent != null;
	}
}
