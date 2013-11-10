package radorbad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class ViewModel {
	
	public static final ArrayList<JComponent> components = new ArrayList<JComponent>(){
		{
			JTextField code = new JTextField("Bob");
			this.add(code);
			JButton radButton = new JButton("Rad");
			radButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("RAD");
				}
			});
			this.add(radButton);
			
			JButton badButton = new JButton("Bad");
			badButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("BAD");
				}
			});
			this.add(badButton);
		}
	};
}
