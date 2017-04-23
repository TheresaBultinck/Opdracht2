package command;

import datastructure.LinkedList;

/**
 * Class that saves the commands given by the user.
 * @author Theresa Bultinck
 */
public class CommandStack {
	
	private LinkedList<Command> commands = new LinkedList<>();
	private LinkedList<Command>.ListNode<Command> pointer;
	
	/**
	 * Perform the command submitted by the user.
	 * @param command
	 */
	public void doCommand(Command command) {
		pointer = commands.add(command);
	}

	/**
	 * Checks if you can undo.
	 * @return true if you can undo and false if it isn't possible to use the undo method.
	 */
	public boolean canUndo() {
		return !commands.isEmpty() && pointer != null;
	}
	
	/**
	 * Method to remove the last character before your pointer. 
	 */
	public void undo() {
		if(canUndo()) {
			pointer.getElement().undo();
			pointer = pointer.getPrev();
		}
	}
	
	/**
	 * Checks if it is able to redo. 
	 * @return
	 */
	public boolean canRedo() {
		return !commands.isEmpty() && (pointer == null || pointer.hasNext());
	}
	
	/**
	 * Method to remove everything that is already typed before your pointer.
	 */
	public void redo() {
		if(canRedo()) {
			pointer = pointer == null ? commands.head() : pointer.getNext();
			pointer.getElement().redo();
		}
	}
}
