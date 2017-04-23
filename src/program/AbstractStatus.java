package program;

/**
 * Class that gives the position of the error 
 * @author Theresa Bultinck
 *
 */
public class AbstractStatus {
	
	private StatusProperty prop;
	private int position;
	
	public AbstractStatus (int pos, StatusProperty prop){
		setStatusProperty(prop);
		this.position = pos; 
	}
	
	public int getStartPosition(){
		return position;
	}
	
	public int getEndPosition(){
		return position + 2;
	}
	
	public StatusProperty getStatusProperty(){
		return prop;
	}
	
	private void setStatusProperty (StatusProperty prop){
		this.prop = prop;
	}
	
	@Override
	public String toString(){
		return prop.toString() + " on " + position;
	}
}
