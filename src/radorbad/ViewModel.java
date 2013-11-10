package radorbad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.text.PlainDocument;

import jsyntaxpane.DefaultSyntaxKit;

public class ViewModel {

	private static final int CODE_WIDTH = View.WIDTH - 2 * View.OFFSET;
	private static final int CODE_HEIGHT = (int)(View.HEIGHT / 1.25);

	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 50;
	private static final int BUTTON_XOFFSET = (int)(View.WIDTH/3);
	private static JEditorPane codeEditor;
	
	@SuppressWarnings("serial")
	public static final ArrayList<JComponent> components = new ArrayList<JComponent>() {
		{
			DefaultSyntaxKit.initKit();
			codeEditor = new JEditorPane();
			JScrollPane srcPane = new JScrollPane(codeEditor);
			codeEditor.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
			srcPane.setLocation(5, 5);
			srcPane.setSize(CODE_WIDTH, CODE_HEIGHT);
			this.add(srcPane);
			//codeEditor.setContentType("text/java");
			
			codeEditor.setText("public static void main(String[] args) {\n}");


			JButton radButton = new JButton("Rad");
			radButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("RAD");
				}
			});
			radButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
			radButton.setLocation(BUTTON_XOFFSET - (BUTTON_WIDTH/2), View.OFFSET + CODE_HEIGHT);
			this.add(radButton);

			JButton badButton = new JButton("Bad");
			badButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("BAD");
				}
			});
			badButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
			badButton.setLocation(BUTTON_XOFFSET + (int) (1.5 * BUTTON_WIDTH), View.OFFSET + CODE_HEIGHT);
			this.add(badButton);
		}
	};

	public static boolean addSnippet(IVCSSnippetSource snippetSource) {
		String text = snippetSource.getSnippet(5);
		if (text == null) {
			return false;
		} else {
			codeEditor.setText(text);
			return true;
		}
	}
}
