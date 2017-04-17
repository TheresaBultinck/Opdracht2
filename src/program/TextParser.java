package program;


import datastructure.Tree;
import datastructure.Tree.TreeNode;

public class TextParser {
	
	private String bufferText = "";
	private String fullText = "";
	private Tag currentTag;
	private Tree<Tag> tagTree = new Tree<Tag>();
	private TreeNode currentParent = null;
	private Status currentStatus = Status.OK;
	private int currentPosition = 0;
	
	/**
	 * Method to parse through a typed String. 
	 * @param typed
	 */
	public Status parse(String typed,int offset){
		if (typed.equals("<")) 
			currentTag = new Tag(offset);
		else if (typed.equals("\\")) {
			currentTag.close();
		}
		else if (typed.equals(">")) {
			currentTag.setName(bufferText);		
			addToTree();
			currentTag = null;
			bufferText = "";
		}
		else if(currentTag != null)
			bufferText += typed;
		
		fullText += typed;
		currentPosition = offset;
		return currentStatus;
		
	}
	
	 
	
	private void addToTree() {
		if(!tagTree.hasRoot() && currentTag.isOpen()) {
			currentParent = tagTree.setRoot(currentTag);
		}
		else if (currentTag.isOpen()) {
			currentParent = currentParent.addChild(currentTag);
		}
		else if (currentTag.isClose() && currentTag.equals(currentParent.getElement())){
			currentParent = currentParent.getParent(); 
		}
		else {
			currentStatus = Status.ERROR;
		}
	}



	public void remove(int offset) {
		String removed = fullText.substring(offset, offset+1);
		fullText = fullText.substring(0,offset) + fullText.substring(offset+1,fullText.length());
		
		if(removed.equals(">") && currentPosition == offset)
			currentStatus = Status.OK; 
		if (currentStatus == Status.OK)
			bufferText.re
		
	}
}
