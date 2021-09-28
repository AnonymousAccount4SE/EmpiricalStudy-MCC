package edu.lu.uni.serval.renamed.methods;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Collect renamed methods from the commit history of a Java repo.
 * 
 * @author kui.liu
 *
 */
public class AddedMethodsCollector4Jar {


	
	public static void main(String[] args) {

	}

	public static void parseAddedMethods(List<String> projects,String JavaReposNamesFile, String COMMIT_DIFF_PATH, String Added_METHODS_PATH){
		final String INPUT_DATA_PATH =  COMMIT_DIFF_PATH;
		final String OUTPUT_DATA_PATH = Added_METHODS_PATH;
		String JAVA_REPOS_PATH = JavaReposNamesFile.substring(0,JavaReposNamesFile.lastIndexOf(File.separator));
		List<String> projectList = CommitDiffs.readList(JavaReposNamesFile);

		for(String projectName: projects){
//			String projectName = projectList.get(index);// projectPath.getName();
			String filePath = INPUT_DATA_PATH + projectName;
			System.out.println("filePath:"+filePath);
			if (!new File(filePath).exists()) {
				System.err.println("Run the CommitDiff.jar first!");
			} else {
				String outputPath = OUTPUT_DATA_PATH + projectName + "/";
				parseAddedMethods(filePath, outputPath);
			}
		}


	}

	private static void parseAddedMethods(String inputProject, String outputPath) {
		ActorSystem system = null;
		ActorRef gitTravelActor = null;
		try {
			system = ActorSystem.create("methodNames-system");
			gitTravelActor = system.actorOf(ParseActor4AddedCommits.props(inputProject, outputPath), "parse-actor");
			gitTravelActor.tell("BEGIN", ActorRef.noSender());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
