package program;

public class Tag {
	
	private String name;
	private boolean isOpen = true;
	
	public void close() {
		isOpen = false;
	}
	public void setName(String text) {
		name = text;
	}
	
	
}
