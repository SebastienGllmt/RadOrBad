package radorbad;

import java.io.File;
import java.io.IOException;

public class Main{

	public static void main(String[] args) throws IOException{
		Model m = new Model();
		ViewModel vm = new ViewModel();
		
		View v = new View(vm, m);
		File f = new File(".git");
		System.out.println(f.getAbsolutePath() + " " + f.exists());
		IVCSSnippetSource ss = new GitSnippetSource(new File(".git"));
		ViewModel.addSnippet(ss);
	}
}
