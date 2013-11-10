package radorbad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class GitSnippetSource implements IVCSSnippetSource {

	Repository repository;
	
	public GitSnippetSource(File repositoryDirectory) throws IOException
	{
		RepositoryBuilder builder = new RepositoryBuilder();
		repository = builder.setGitDir(repositoryDirectory).readEnvironment().findGitDir().build();
	}
	
	private static String Replicate(String s, int n)
	{
		return new String(new char[n]).replace("\0", s);
	}
	
	@SuppressWarnings("rawtypes")
	private static int Count(Iterable iterable)
	{
		int count = 0;
		for (Iterator it = iterable.iterator(); it.hasNext(); it.next())
		{
			count++;
		}
		return count;
	}
	
	private static List<DiffEntry> KeepSources(List<DiffEntry> diffs)
	{
		List<DiffEntry> entries = new ArrayList<DiffEntry>();
		for (DiffEntry entry : diffs)
		{
			String path = entry.getNewPath();
			int extensionIndex = path.lastIndexOf('.');
			if (extensionIndex != -1)
			{
				String extension = path.substring(extensionIndex);
				List<String> extensions = new ArrayList<>();
				extensions.add(".java");
				
				if (extensions.contains(extension))
				{
					entries.add(entry);
				}
			}
		}
		return entries;
	}
	
	@Override
	public SnippetData getSnippet(int backlogLength) {
		try {
			Git git = new Git(repository);
			int clampedBacklogLength = (int)Math.min(backlogLength + 1, Count(git.log().call()));
			DiffCommand diff = git.diff()
					.setOldTree(getTreeIterator(Constants.HEAD + Replicate("^", clampedBacklogLength)))
					.setNewTree(getTreeIterator(Constants.HEAD + "^"));
			List<DiffEntry> entries = KeepSources(diff.call());
			
			if (entries.size() == 0)
			{
				return new SnippetData("No commits found.", 0);
			}
			
			int chosenEntryIndex = (int)(Math.random() * Integer.MAX_VALUE) % entries.size();
			DiffEntry chosenEntry = entries.get(chosenEntryIndex);

			OutputStream out = new ByteArrayOutputStream();
			DiffFormatter diffFormater = new DiffFormatter(out);
			diffFormater.setRepository(repository);
			diffFormater.format(chosenEntry);
			
			return new SnippetData(out.toString(), chosenEntry.getNewId().hashCode());
		}
		catch(GitAPIException | IOException e)
		{
			return null;
		}
	}
	
	private AbstractTreeIterator getTreeIterator(String name)
			throws IOException {
		final ObjectId id = repository.resolve(name);
		if (id == null)
			throw new IllegalArgumentException(name);
		final CanonicalTreeParser p = new CanonicalTreeParser();
		final ObjectReader or = repository.newObjectReader();
		try {
			p.reset(or, new RevWalk(repository).parseTree(id));
			return p;
		} finally {
			or.release();
		}
	}
}
