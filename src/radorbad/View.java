package radorbad;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class View extends JFrame {

	private final static int WIDTH=640;
	private final static int HEIGHT=480;
	
	public View(ViewModel vm, Model m){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new FlowLayout());
		for(JComponent comp : ViewModel.components){
			this.add(comp);
		}
		
	}
}
