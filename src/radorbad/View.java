package radorbad;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class View extends JFrame {

	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	public final static int OFFSET = 10;
	
	public View(ViewModel viewModel){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rad or Bad");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setLayout(null);
		for(JComponent comp : viewModel.getComponents()){
			add(comp);
		}	
	}
}
