package command;

/**
 * Interface for the implementation of the undo and redo button.
 * @author Theresa
 *
 */
public interface Command {
	
	public void undo();
	
	public void redo();
	
}
