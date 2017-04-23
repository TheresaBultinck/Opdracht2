package command;

import javax.swing.JTextArea;

/**
 * Class that implements that methods of the public interface Command.
 * @author Theresa Bultinck
 *
 */
public class TypeCommand implements Command {

	private String before;
	private String after;
	private JTextArea textArea;
	
	public TypeCommand(String before, String after, JTextArea textArea){
		this.before = before;
		this.after = after;
		this.textArea = textArea;
	}
	
	/**
	 * Method to remove the last character you typed.
	 */
	@Override
	public void undo(){
		textArea.setText(before);
	}
	
	/**
	 * Method to remove everything that was already typed in the editor. 
	 * So that you can start again. 
	 */
	@Override
	public void redo(){
		textArea.setText(after);
	}
}
