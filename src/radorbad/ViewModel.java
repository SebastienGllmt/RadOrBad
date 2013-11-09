package radorbad;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

public class ViewModel {
	
	public static final ArrayList<JComponent> components = new ArrayList<JComponent>(){
		{
			this.add(new JButton("Rad"));
			this.add(new JButton("Bad"));
		}
	};
}
