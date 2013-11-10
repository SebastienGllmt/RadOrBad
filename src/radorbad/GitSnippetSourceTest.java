package radorbad;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class GitSnippetSourceTest {

	@Test
	public void testGetSnippet() throws IOException {
		GitSnippetSource snippetSource = new GitSnippetSource(new File(".git"));
		String s = snippetSource.getSnippet(5);
		System.out.println("Snippet: " + s);
		assertNotNull(s);
	}
}
