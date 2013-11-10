package radorbad;

import java.util.HashMap;


public class Model {
	
	private IVCSSnippetSource snippetSource;
	private HashMap<Long, Integer> scores;
	
	public Model(IVCSSnippetSource snippetSource) {
		this.snippetSource = snippetSource;
		scores = new HashMap<Long, Integer>();
	}
	
	public SnippetData getSnippet() {
		SnippetData snippet = snippetSource.getSnippet(5); 
		if (!scores.containsKey(snippet.hash)) {
			scores.put(snippet.hash, 0);
		}
		return snippet;
	}
	
	public void upVote(long id)
	{
		scores.put(id, scores.get(id) + 1);
	}
	
	public void downVote(long id)
	{
		scores.put(id, scores.get(id) - 1);
	}
}
