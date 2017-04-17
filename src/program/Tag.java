package program;

public class Tag {
	
	private String name;
	private int offset;
	private boolean isOpen = true;
	
	public Tag(int offset){
		this.offset = offset;
	}
	
	/**
	 * If it is an opening tag ('<'), isOpen is set to true and if it is an closing tag ('<\') 
	 * isOpen is set to false. 
	 */
	public void close() {
		isOpen = false;
	}

	public void setName(String text) {
		name = text;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public boolean isClose() {
		return !isOpen;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return isOpen ? "Open: " + name : "Close: " + name;
	}
	
	@Override
	public boolean equals(Object t){
		return ((Tag)t).getName().equals(name);
	}
	
}
