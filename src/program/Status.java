package program;

public class Status extends AbstractStatus {
	
	private Tag tag;
	
	public Status(Tag tag, StatusProperty prop){
		super(tag.getStartPosition(), prop);
		this.tag = tag;
	}
	
	@Override
	public int getStartPosition(){
		return tag.getStartPosition() - 1;
	}
	
	@Override
	public int getEndPosition(){
		return tag.getEndPosition();
	}
	
	@Override 
	public String toString(){
		return tag.toString() + " " + super.toString();
	}
}
