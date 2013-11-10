package radorbad;

import java.util.HashMap;

public class Model {

	private static class Rating {
		public Rating(int numUpvotes, int numDownVotes) {
			this.numUpVotes = numUpvotes;
			this.numDownVotes = numDownVotes;
		}

		public int numUpVotes;
		public int numDownVotes;
	}

	private IVCSSnippetSource snippetSource;
	private HashMap<Long, Rating> ratings;
	private int historyDepth;

	public Model(IVCSSnippetSource snippetSource, int historyDepth) {
		this.snippetSource = snippetSource;
		ratings = new HashMap<>();
		this.historyDepth = historyDepth;
	}

	public SnippetData getNextSnippet() {
		SnippetData snippet = snippetSource.getSnippet(historyDepth);
		if (!ratings.containsKey(snippet.hash)) {
			ratings.put(snippet.hash, new Rating(0, 0));
		}
		return snippet;
	}

	public int getNumUpVotes(long id) {
		return ratings.get(id).numUpVotes;
	}

	public int getNumDownVotes(long id) {
		return ratings.get(id).numDownVotes;
	}

	public void upVote(long id) {
		ratings.get(id).numUpVotes++;
	}

	public void downVote(long id) {
		ratings.get(id).numDownVotes++;
	}
}
