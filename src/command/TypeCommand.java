package command;

import javax.swing.JTextArea;

public class TypeCommand implements Command {

	private String before;
	private String after;
	private JTextArea textArea;
	
	public TypeCommand(String before, String after, JTextArea textArea){
		this.before = before;
		this.after = after;
		this.textArea = textArea;
	}
	
	@Override
	public void undo(){
		textArea.setText(before);
	}
	
	@Override
	public void redo(){
		textArea.setText(after);
	}
}
