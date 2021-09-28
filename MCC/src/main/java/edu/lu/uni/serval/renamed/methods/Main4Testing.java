package edu.lu.uni.serval.renamed.methods;

import java.util.List;

public class Main4Testing {
	/**
	 * Collect renamed methods for testing repos.
	 * @param args
	 */
	public static void main(String[] args) {
//		CommitDiffs.main(null); // Collect all commits and generate previous java files and revised java files from Java repositories.

		List<String> projects = CommitDiffs.readList(Configuration4InCon.JAVA_REPO_NAMES_FILE);
//		List<String> projects = CommitDiffs.readList(Configuration4Training.JAVA_REPO_NAMES_FILE);
		System.out.println(projects);
		for (int i = 0; i < projects.size(); i ++) {
			args = new String[] {"" + i};
			RenamedMethodsCollector.main(args); // Collect renamed methods.
		}
	}
}
