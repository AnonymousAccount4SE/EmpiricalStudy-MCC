package edu.lu.uni.serval.renamed.methods;

import edu.lu.uni.serval.utils.FileHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main3 {
	/**
	 * Collect renamed methods for one single project.
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String outputPath = "/home1/michael/BadMethodName/Output_TestingRepos/InconsistentMethods/Fixed_RenameMethods/ignite/";
		mergeData(outputPath,500);
//		RenamedMethodsCollector.main(args); // Collect renamed methods.
		RenamedMethodsFilter.filteroutTyposByParsedMethodNames(outputPath);
	}
	public static void mergeData(String outputPath,int numberOfWorkers) throws IOException {
		String methodsFile = outputPath + "RenamedMethods.txt";
		String oldMethodNamesFile = outputPath + "OldMethodNames.txt";
		String newMethodNamesFile = outputPath + "NewMethodNames.txt";
		String sizesFile = outputPath + "Sizes.csv";
		String methodBodiesFile = outputPath + "MethodBodies.txt";
		FileHelper.deleteFile(methodsFile);
		FileHelper.deleteFile(methodBodiesFile);

		String path = outputPath + "RenamedMethods/";
		StringBuilder sizes = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		StringBuilder oldMethodNames = new StringBuilder();
		StringBuilder newMethodNames = new StringBuilder();
		int counter = 0;
		for (int i = 1; i <= numberOfWorkers; i ++) {
			if (!new File(path + "RenamedMethods_" + i + ".txt").exists()) continue;
			FileInputStream fis = new FileInputStream(path + "RenamedMethods_" + i + ".txt");
			Scanner scanner = new Scanner(fis);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] elements = line.split(":");
				if (elements.length == 11) {
					String[] tokens = elements[10].split(" ");
					sizes.append(tokens.length).append("\n");
					methods.append(line).append("\n");
					oldMethodNames.append(elements[4]).append("\n");
					newMethodNames.append(elements[5]).append("\n");
					counter ++;
					if (counter % 2000 == 0) {
						FileHelper.outputToFile(methodsFile, methods, true);
						methods.setLength(0);
					}
				} else {
					System.err.println(line);
				}
			}

			scanner.close();
			fis.close();

			FileHelper.outputToFile(methodBodiesFile, FileHelper.readFile(outputPath + "MethodBodies/MethodBodies_" + i + ".txt"), true);
		}

		if (methods.length() > 0) {
			FileHelper.outputToFile(methodsFile, methods, true);
			methods.setLength(0);
		}
		FileHelper.outputToFile(sizesFile, sizes, false);
		sizes.setLength(0);
		FileHelper.outputToFile(oldMethodNamesFile, oldMethodNames, false);
		oldMethodNames.setLength(0);
		FileHelper.outputToFile(newMethodNamesFile, newMethodNames, false);
		newMethodNames.setLength(0);

		FileHelper.deleteDirectory(path);
		FileHelper.deleteDirectory(outputPath + "MethodBodies/");
	}

}
