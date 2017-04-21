package texed;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import datastructure.LinkedList;
import program.AbstractStatus;
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
	final Highlighter hilit; 
	final Highlighter.HighlightPainter painter;
	
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
		
		hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
		textArea.setHighlighter(hilit);
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
		try {
			System.out.println("Removed");
			String changed = ev.getDocument().getText(ev.getLength(), ev.getOffset());
			System.out.println("Changed");
			check(ev, changed);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Callback when inserting an element
	 */
	public void insertUpdate(DocumentEvent ev) {
		try {
			String changed = ev.getDocument().getText(ev.getOffset(), ev.getLength());
			check(ev, changed);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void check(DocumentEvent ev, String typed) throws BadLocationException {
		//Evaluate only on >
		if (typed.equals(">")|| ev.getLength() > 1) {
			System.out.println("Check");
			highlight(parser.parse(ev.getDocument().getText(0, ev.getDocument().getLength())));
		}
		//Auto complete
		else if (typed.equals("<")) {
			if (parser.hasParent()){
				System.out.println(parser.getParent().getName());
				SwingUtilities.invokeLater(new CompletionTask("/" + parser.getParent().getName() + ">",ev.getOffset()+1));
			}
		}
	}
	
	private void highlight (LinkedList<AbstractStatus> status) throws BadLocationException {
		hilit.removeAllHighlights();
		for (AbstractStatus s : status){
			System.out.println(s);
			hilit.addHighlight(s.getStartPosition(), s.getEndPosition(), painter);
		}
	}

	/**
	 * Runnable: change UI elements as a result of a callback
	 * Start a new Task by invoking it through SwingUtilities.invokeLater
	 */
	@SuppressWarnings("unused")
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

	private class CompletionTask implements Runnable {
		String completion;
		int position;
		
		CompletionTask(String completion, int position){
			this.completion = completion;
			this.position = position;
		}
		
		public void run(){
			textArea.insert(completion, position);
			textArea.setCaretPosition(position + completion.length());
			textArea.moveCaretPosition(position);
		}
	}
	/**
	 * Entry point of the application: starts a GUI
	 */
	public static void main(String[] args) {
		new Texed();
	}

}
