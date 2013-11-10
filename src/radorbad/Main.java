package radorbad;

import java.io.File;
import java.io.IOException;

public class Main{

	public static void main(String[] args) throws IOException{
		IVCSSnippetSource snippetSource = new GitSnippetSource(new File(args[0]));
		
		Model model = new Model(snippetSource);
		
		ViewModel viewModel = new ViewModel(model);
		
		new View(viewModel);
	}
}
