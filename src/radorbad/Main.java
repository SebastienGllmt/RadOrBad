package radorbad;

public class Main{

	public static void main(String[] args){
		Model m = new Model();
		ViewModel vm = new ViewModel();
		
		View v = new View(vm, m);
	}
}
