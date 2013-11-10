package radorbad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import jsyntaxpane.DefaultSyntaxKit;

public class ViewModel {

	private static final int CODE_WIDTH = View.WIDTH - 2 * View.OFFSET;
	private static final int CODE_HEIGHT = View.HEIGHT / 2;

	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 50;
	private static final int BUTTON_XOFFSET = 200;
	private static final int BUTTON_YOFFSET = 100;
	private static JEditorPane codeEditor;
	
	public static final ArrayList<JComponent> components = new ArrayList<JComponent>() {
		{
			DefaultSyntaxKit.initKit();
			codeEditor = new JEditorPane();
			JScrollPane srcPane = new JScrollPane(codeEditor);
			srcPane.setLocation(5, 5);
			srcPane.setSize(200, 200);
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
			radButton.setLocation(BUTTON_XOFFSET - (BUTTON_WIDTH >> 1), View.OFFSET + CODE_HEIGHT + BUTTON_YOFFSET);
			this.add(radButton);

			JButton badButton = new JButton("Bad");
			badButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("BAD");
				}
			});
			badButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
			badButton.setLocation(BUTTON_XOFFSET + (int) (1.5 * BUTTON_WIDTH), View.OFFSET + CODE_HEIGHT + BUTTON_YOFFSET);
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
