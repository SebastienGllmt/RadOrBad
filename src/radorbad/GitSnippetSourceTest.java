package radorbad;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class GitSnippetSourceTest {

	@Test
	public void testGetSnippet() {
		GitSnippetSource snippetSource = new GitSnippetSource(new File("."));
		String s = snippetSource.getSnippet(5);
		System.out.println("Snippet: " + s);
		assertNotNull(s);
	}
}