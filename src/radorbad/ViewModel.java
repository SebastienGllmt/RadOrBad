package radorbad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.PlainDocument;

import jsyntaxpane.DefaultSyntaxKit;

public class ViewModel {

	private static final int CODE_WIDTH = View.WIDTH - 2 * View.OFFSET;
	private static final int CODE_HEIGHT = (int) (View.HEIGHT / 1.25);

	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 50;
	private static final int BUTTON_XOFFSET = (int) (View.WIDTH / 3);

	private JEditorPane codeEditor;
	private Model model;
	private ArrayList<JComponent> components;

	long currentSnippetID = 0;

	private static final String DOWNVOTE = "Bad";
	private static final String UPVOTE = "Rad";

	private String lastVote;

	private void upVote() {
		lastVote = UPVOTE;
		model.upVote(currentSnippetID);
	}

	private void downVote() {
		lastVote = DOWNVOTE;
		model.downVote(currentSnippetID);
	}

	private void nextSnippet() {
		if (currentSnippetID != 0) {
			final String FORMAT = "Rads: %d\nBads: %d\n\nYou voted: %s\n";
			int numUpVotes = model.getNumUpVotes(currentSnippetID);
			int numDownVotes = model.getNumDownVotes(currentSnippetID);
			String message = String.format(FORMAT, numUpVotes, numDownVotes, lastVote);
			JOptionPane.showMessageDialog(null, message, "Results", JOptionPane.PLAIN_MESSAGE);
		}
		SnippetData snippet;
		do {
			snippet = model.getNextSnippet();
		} while (snippet.hash == currentSnippetID && snippet.hash != 0);

		codeEditor.setText(snippet.contents);
		currentSnippetID = snippet.hash;
	}

	public ViewModel(Model model) {
		this.model = model;

		components = new ArrayList<JComponent>();

		DefaultSyntaxKit.initKit();
		codeEditor = new JEditorPane();
		JScrollPane srcPane = new JScrollPane(codeEditor);
		codeEditor.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
		srcPane.setLocation(5, 5);
		srcPane.setSize(CODE_WIDTH, CODE_HEIGHT);
		components.add(srcPane);
		//codeEditor.setContentType("text/java");

		JButton radButton = new JButton("Rad");
		radButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upVote();
				nextSnippet();
			}
		});

		radButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		radButton.setLocation(BUTTON_XOFFSET - (BUTTON_WIDTH / 2), View.OFFSET + CODE_HEIGHT);
		components.add(radButton);

		JButton badButton = new JButton("Bad");
		badButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downVote();
				nextSnippet();
			}
		});

		badButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		badButton.setLocation(BUTTON_XOFFSET + (int) (1.5 * BUTTON_WIDTH), View.OFFSET + CODE_HEIGHT);
		components.add(badButton);

		nextSnippet();
	}

	public ArrayList<JComponent> getComponents() {
		return components;
	}
}
