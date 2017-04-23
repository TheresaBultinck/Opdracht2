package program;

/**
 * Defines a tag between two characters ('<' and '>').
 * @author Theresa Bultinck
 */
public class Tag {
	
	private String name;
	private int offset;
	private boolean isOpen = true;
	
	public Tag(int offset){
		this.offset = offset;
	}
	
	/**
	 * Default constructor
	 */
	public Tag(){
		this.offset = 0;
	}
	
	/**
	 * If it is an opening character ('<'), isOpen is set to true and if it is an closing character ('</') 
	 * isOpen is set to false. 
	 */
	public void close() {
		isOpen = false;
	}

	/**
	 * Change the name of the tag
	 * @param text
	 */
	public void setName(String text) {
		name = text;
	}
	
	/**
	 * @return true if the tag is open and false if the tag isn't opened
	 */
	public boolean isOpen() {
		return isOpen;
	}
	
	/**
	 * @return true if the tag is closed and false if the tag is still open
	 */
	public boolean isClose() {
		return !isOpen;
	}
	
	/**
	 * @return what is the name of the tag. For example "html", "body".
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the length of the tag
	 */
	public int getLength(){
		return this.name.length() + 2;
	}
	
	/**
	 * Position of the first character of the tag. 
	 * @return offset
	 */
	public int getStartPosition(){
		return offset; 
	}
	
	/**
	 * Position of the last character of the tag.
	 * @return
	 */
	public int getEndPosition(){
		return offset + getLength();
	}
	
	@Override
	public String toString() {
		return (isOpen ? "Open: " + name : "Close: " + name);
	}
	
	@Override
	public boolean equals(Object t){
		return ((Tag)t).getName().equals(name);
	}
	
	public void setOffset(int i){
		this.offset = i;
	}
}
