package radorbad;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class View extends JFrame {

	public final static int WIDTH=640;
	public final static int HEIGHT=480;
	public final static int OFFSET=10;
	
	public View(ViewModel vm, Model m){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setLayout(null);
		for(JComponent comp : ViewModel.components){
			this.add(comp);
		}
		
	}
}
