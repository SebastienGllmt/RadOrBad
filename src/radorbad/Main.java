package radorbad;

import java.io.File;
import java.io.IOException;

public class Main {

	private static void ShowUsage() {
		System.err.println("Usage: <.git path> <history size>");
		System.exit(1);
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			ShowUsage();
		}

		int depth = -1;
		try {
			depth = Integer.parseInt(args[1]);
			if (depth <= 0) {
				ShowUsage();
			}
		} catch (Exception e) {
			ShowUsage();
		}

		IVCSSnippetSource snippetSource = new GitSnippetSource(new File(args[0]));

		Model model = new Model(snippetSource, depth);

		ViewModel viewModel = new ViewModel(model);

		new View(viewModel);
	}
}
