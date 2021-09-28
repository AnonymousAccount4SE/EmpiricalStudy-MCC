package edu.lu.uni.serval.renamed.methods;

import java.util.List;

public class Main4Training {
	/**
	 * Collect renamed methods for testing repos.
	 * @param args
	 */
	public static void main(String[] args) {
		CommitDiffs4TrainingMethods.main(null); // Collect all commits and generate previous java files and revised java files from Java repositories.
		List<String> projects = CommitDiffs.readList(Configuration4Training.JAVA_REPO_NAMES_FILE);
		System.out.println(projects);
		for (int i = 0; i < projects.size(); i ++) {
			args = new String[] {"" + i};
			RenamedMethodsCollector4Training.main(args); // Collect renamed methods.
		}
	}
}
