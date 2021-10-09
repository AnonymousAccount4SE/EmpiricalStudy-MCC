package edu.lu.uni.serval.renamed.methods;

import java.io.File;
import java.util.List;

public class Main4AddedCommits {
	/**
	 * Collect added methods for testing repos.
	 * @param args
	 */
	public static void main(String[] args) {
//		CommitDiffs.main(null); // Collect all commits and generate previous java files and revised java files from Java repositories.
//		CommitDiffs4TrainingMethods.main(null); // Collect all commits and generate previous java files and revised java files from Java repositories.
//		List<String> projects = CommitDiffs.readList(Configuration4InCon.JAVA_REPO_NAMES_FILE);
		String JavaReposNamesFile = args[0];
		String COMMIT_DIFF_PATH = args[1];
		String Added_METHODS_PATH = args[2];
		List<String> projects = CommitDiffs.readList(JavaReposNamesFile);
		AddedMethodsCollector4Jar.parseAddedMethods(projects,JavaReposNamesFile,
				COMMIT_DIFF_PATH + File.separator,Added_METHODS_PATH + File.separator);
//		System.out.println(projects);
//		for (int i = 0; i < projects.size(); i ++) {
//			args1 = new String[] {"" + i};
//			AddedMethodsCollector4Jar.main(args1); // Collect renamed methods.
//		}
	}
}
