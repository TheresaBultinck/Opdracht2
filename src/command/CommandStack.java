package command;

import datastructure.LinkedList;

public class CommandStack {
	
	private LinkedList<Command> commands = new LinkedList<>();
	private LinkedList<Command>.ListNode<Command> pointer;
	
	public void doCommand(Command command) {
		pointer = commands.add(command);
	}

	public boolean canUndo() {
		return !commands.isEmpty() && pointer != null;
	}
	
	public void undo() {
		if(canUndo()) {
			pointer.getElement().undo();
			pointer = pointer.getPrev();
		}
	}
	
	public boolean canRedo() {
		return !commands.isEmpty() && (pointer == null || pointer.hasNext());
	}
	
	public void redo() {
		if(canRedo()) {
			pointer = pointer == null ? commands.head() : pointer.getNext();
			pointer.getElement().redo();
		}
	}
}
