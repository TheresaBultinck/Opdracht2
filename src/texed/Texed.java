package texed;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import command.CommandStack;
import command.TypeCommand;
import datastructure.LinkedList;
import program.AbstractStatus;
import program.TextParser;

/**
 * Simple GUI for a text editor.
 *
 */
public class Texed extends JFrame implements DocumentListener, ActionListener {
	private JTextArea textArea;
	private TextParser parser;
	private static final long serialVersionUID = 5514566716849599754L;
	final Highlighter hilit; 
	final Highlighter.HighlightPainter painter;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItemDo, menuItemUndo;
	private CommandStack commands;
	private String text;
	private boolean ignore = false;
	
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
		
		//undo-redo command stack
		commands = new CommandStack();
		createMenu();
		
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
	 * Constructs a new MenuBar for the Do/Undo items. 
	 */
	public void createMenu(){
		menuBar = new JMenuBar();
		
		menu = new JMenu("Do/Undo menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		
		menuItemUndo = new JMenuItem("Undo", KeyEvent.VK_T);
		menuItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		menuItemUndo.addActionListener(this);
		menu.add(menuItemUndo);
		
		menuItemDo = new JMenuItem("Do", KeyEvent.VK_T);
		menuItemDo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		menuItemDo.addActionListener(this);
		menu.add(menuItemDo);
		
		this.setJMenuBar(this.menuBar);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev){
		Object source = ev.getSource();
		if (source.equals(menuItemDo)){
			ignore = true;
			commands.redo();
		} else if (source.equals(menuItemUndo)) {
			ignore = true; 
			commands.undo();
		}
	}

	/**
	 * Callback when changing an element
	 */
	public void changedUpdate(DocumentEvent ev) {
		//do nothing
	}

	/**
	 * Callback when deleting an element
	 */
	public void removeUpdate(DocumentEvent ev) {
		try {
			String changed = text.substring(ev.getOffset(), ev.getOffset() + ev.getLength());
			if (!ignore){
				commands.doCommand(new TypeCommand(text, textArea.getText(), textArea));
				ignore = false;
			}
			text = textArea.getText();
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
			if (!ignore) {
				commands.doCommand(new TypeCommand(text, textArea.getText(), textArea));
				ignore = false;
			}
			this.text = textArea.getText();
			check(ev, changed);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if the tags are correctly opened and closed.
	 * @param ev
	 * @param typed
	 * @throws BadLocationException
	 */
	private void check(DocumentEvent ev, String typed) throws BadLocationException {
		//Evaluate only on >
		if (typed.equals(">")|| ev.getLength() > 1) {
			highlight(parser.parse(ev.getDocument().getText(0, ev.getDocument().getLength())));
		}
		//Auto complete
		else if (typed.equals("<")) {
			if (parser.hasParent()){
				SwingUtilities
					.invokeLater(new CompletionTask("/" + parser.getParent().getName() + ">",ev.getOffset() + 1));
			}
		}
	}
	
	/**
	 * Highlights the tag in which you made an error. For example: if you forgot to close
	 * the body tag and already closed the html.
	 * @param status
	 * @throws BadLocationException
	 */
	private void highlight (LinkedList<AbstractStatus> status) throws BadLocationException {
		hilit.removeAllHighlights();
		for (AbstractStatus s : status){
			System.out.println(s);
			hilit.addHighlight(s.getStartPosition(), s.getEndPosition(), painter);
		}
	}

	/**
	 * Runnable: change UI elements as a result of a callback
	 */
	
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
