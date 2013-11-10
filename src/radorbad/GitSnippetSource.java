package radorbad;

import java.io.File;
import java.util.List;

import edu.nyu.cs.javagit.api.DotGit;
import edu.nyu.cs.javagit.api.commands.GitLogResponse;
import edu.nyu.cs.javagit.api.commands.GitLogResponse.CommitFile;

public class GitSnippetSource implements IVCSSnippetSource {

	private File repositoryDirectory = null;
	
	public GitSnippetSource(File repositoryDirectory)
	{
		this.repositoryDirectory = repositoryDirectory;
	}
	
	@Override
	public String getSnippet(int backlogLength) {
		DotGit dotGit = DotGit.getInstance(this.repositoryDirectory);
		
		List<GitLogResponse.Commit> commitLog = null;
		try {
			commitLog = dotGit.getLog();
		} catch (Exception e) {
			return null;
		}		
		
		int logLength = commitLog.size();
		int numBacklogEntires = (int)Math.max(logLength - backlogLength, 0);
		List<GitLogResponse.Commit> candidateCommits = commitLog.subList(logLength - numBacklogEntires, logLength);
				
		int chosenCommitIndex = (int)(Math.random() * Integer.MAX_VALUE) % candidateCommits.size();
		
		GitLogResponse.Commit chosenCommit = candidateCommits.get(chosenCommitIndex);
		
		List<CommitFile> commitFiles = chosenCommit.getFiles();
		if (commitFiles != null)
		{
			for (CommitFile file : commitFiles)
			{
				System.out.printf("%s: Added %d, Deleted %d\n", file.getName(), file.getLinesAdded(), file.getLinesDeleted());
			}
		}
		else
		{
			System.out.println("No files in commit.");
		}

//		List<CommitFile> candidateCommitFiles = chosenCommit.getFiles();
//		int chosenFileIndex = (int)(Math.random() * Integer.MAX_VALUE) % candidateCommitFiles.size();
//		CommitFile chosenFile = candidateCommitFiles.get(chosenFileIndex);
//				
		return null;
	}
}
