package radorbad;

public class GitSnippetSource implements IVCSSnippetSource {

	private String gitPath;
	
	public GitSnippetSource(String gitPath)
	{
		this.gitPath = gitPath;
	}
	
	@Override
	public String getSnippet() {
		return null;
	}

}
