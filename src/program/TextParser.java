package program;

import java.util.ArrayList;

public class TextParser {
	
	private String text = "";
	private Tag currentTag;
	private ArrayList<Tag> tags = new ArrayList<>();
	
	/**
	 * Method to parse through a typed String. 
	 * @param typed
	 */
	public void parse(String typed){
		if (typed.equals("<")) 
			currentTag = new Tag();
		else if (typed.equals("\\")) {
			currentTag.close();
		}
		else if (typed.equals(">")) {
			currentTag.setName(text);
			tags.add(currentTag);
			System.out.println(tags.toString());
			//store or do something with current tag
			currentTag = null;
			text = "";
		}
		else if(currentTag != null)
			text += typed;
	}
}
