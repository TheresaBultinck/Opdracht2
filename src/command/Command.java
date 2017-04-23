package command;

/**
 * Interface for the implementation of the undo and redo button.
 * @author Theresa Bultinck
 *
 */
public interface Command {
	
	public void undo();
	
	public void redo();
	
}
