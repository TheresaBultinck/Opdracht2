package command;

import datastructure.LinkedList;

public class CommandStack {
	
	private LinkedList<Command> commands = new LinkedList<>();
	private int nextPointer = 0;
	
	public void doCommand(Command command) {
		nextPointer++;
		
		commands.add(command);
		
		command.execute();
	}

	
	public boolean canUndo() {
		return nextPointer > 0;
	}
	
	public void undo() {
		if(canUndo()) {
			Command undoCommand = commands.last();
			
			
			undoCommand.undo();
		}
	}
	
	public boolean canRedo() {
		return nextPointer < commands.getSize();
	}
	
	public void redo() {
		if(canRedo()) {
			
		}
	}
	
}
