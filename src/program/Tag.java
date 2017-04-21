package program;

public class Tag {
	
	private String name;
	private int offset;
	private boolean isOpen = true;
	
	public Tag(int offset){
		this.offset = offset;
	}
	
	public Tag(){
		this.offset = 0;
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
	
	public int getLength(){
		return this.name.length()+2;
	}
	
	public int getStartPosition(){
		return offset; 
	}
	
	public int getEndPosition(){
		return offset+getLength();
	}
	
	@Override
	public String toString() {
		return isOpen ? "Open: " + name : "Close: " + name;
	}
	
	@Override
	public boolean equals(Object t){
		return ((Tag)t).getName().equals(name);
	}
	
	public void setOffset(int i){
		this.offset = i;
	}
}
