package texed;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import datastructure.LinkedList;
import program.Status;
import program.TextParser;



/**
 * Simple GUI for a text editor.
 *
 */
public class Texed extends JFrame implements DocumentListener {
	private JTextArea textArea;
	private TextParser parser;
	private static final long serialVersionUID = 5514566716849599754L;
	/**
	 * Constructs a new GUI: A TextArea on a ScrollPane
	 */
	public Texed() {
		super();
		setTitle("Texed: simple text editor");
		setBounds(0, 0, 600, 600);
		textArea = new JTextArea(30,80);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		//Registration of the callback
		textArea.getDocument().addDocumentListener(this);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		parser = new TextParser();
	}

	/**
	 * Callback when changing an element
	 */
	public void changedUpdate(DocumentEvent ev) {
		System.out.println("chqnge");
	}

	/**
	 * Callback when deleting an element
	 */
	public void removeUpdate(DocumentEvent ev) {
		parser.remove(ev.getOffset());
	}

	/**
	 * Callback when inserting an element
	 */
	public void insertUpdate(DocumentEvent ev) {
		//Check if the change is only a single character, otherwise return so it does not go in an infinite loop
		if(ev.getLength() != 1) return;
		try {
			int offset = ev.getOffset();
			String typed = ev.getDocument().getText(offset, 1);
			if(Status.ERROR == parser.parse(typed,offset)) {
				System.out.println("error");
				this.textArea.setForeground(Color.RED);
				this.textArea.revalidate();
				this.textArea.repaint();
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// In the callback you cannot change UI elements, you need to start a new Runnable
		//SwingUtilities.invokeLater(new Task("foo"));
		
	}

	/**
	 * Runnable: change UI elements as a result of a callback
	 * Start a new Task by invoking it through SwingUtilities.invokeLater
	 */
	private class Task implements Runnable {
		private String text;
		
		/**
		 * Pass parameters in the Runnable constructor to pass data from the callback 
		 * @param text which will be appended with every character
		 */
		Task(String text) {
			this.text = text;
		}

		/**
		 * The entry point of the runnable
		 */
		public void run() {
			textArea.append(text);
		}
	}

	/**
	 * Entry point of the application: starts a GUI
	 */
	public static void main(String[] args) {
		new Texed();
	}

}
