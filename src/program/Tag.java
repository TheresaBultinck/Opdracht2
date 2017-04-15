package program;

public class Tag {
	
	private String name;
	private boolean isOpen = true;
	
	/**
	 * If it is an opening tag (<), isOpen is set to true and if it is an closing tag (>) 
	 * isOpen is set to false. 
	 */
	public void close() {
		isOpen = false;
	}

	public void setName(String text) {
		name = text;
	}
	
	
}
