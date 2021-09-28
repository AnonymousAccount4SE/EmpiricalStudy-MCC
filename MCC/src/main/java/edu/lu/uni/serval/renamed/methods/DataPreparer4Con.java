package edu.lu.uni.serval.renamed.methods;

import edu.lu.uni.Configuration4Con;
import edu.lu.uni.Configuration4InCon;
import edu.lu.uni.serval.dlMethods.DataInitializer4Con;
import edu.lu.uni.serval.utils.FileHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Prepare data for deep learning of methods.
 * @author kui.liu
 *
 */
public class DataPreparer4Con {
	private static final int QUANTITY = 1; // the number of methods of which names start with the same token.
	private static final int MIN_SIZE = 0; // The minimum size of vectors.

	public static void main(String[] args) throws IOException {
		List<String> projects = readProjects();
//		List<String> projects = readProjects1111();
		for (String project : projects) {
			String inputPath = Configuration4Con.TOKENIZED_METHODS_PATH  + project + "/";
			String renamedMethodsPath = Configuration4Con.RENAMED_METHODS_PATH + project + "/";
//			String outputPath1 = Configuration.SELECTED_DATA_PATH  +  project + "/";
			String outputPath2 = Configuration4Con.SELECTED_RENAMED_DATA_PATH + project + "/";

			/**
			 * Prepare data:
			 * 
			 * Figure out the common first tokens of all method names. Select the methods of
			 * which method names start with these common first tokens.
			 */
			DataInitializer4Con dataInit = new DataInitializer4Con();
			dataInit.QUANTITY = QUANTITY;
			dataInit.MIN_SIZE = MIN_SIZE;
			dataInit.inputPath = inputPath;
//			dataInit.outputPath1 = outputPath1;
			dataInit.outputPath2 = outputPath2;
			dataInit.renamedMethodsPath = renamedMethodsPath;
//			System.out.println("----------enter initializeData");
			dataInit.initializeData();
//			System.out.println("----------enter selectMethod");
			dataInit.selectMethod();
//			dataInit.selectPositiveMethod();
		}
		
//		DataMerger.merge();
	}
//	
//	public static void main(String[] args) throws IOException {
////		List<String> projects = readProjects();
////		List<String> projects = readProjects1111();
////		for (String project : projects) {
//			String inputPath = Configuration.TOKENIZED_METHODS_PATH  + "/";
////			String renamedMethodsPath = Configuration.RENAMED_METHODS_PATH + project + "/ActualRenamed/";
//			String outputPath1 = Configuration.SELECTED_DATA_PATH  + "/";
////			String outputPath2 = Configuration.SELECTED_RENAMED_DATA_PATH + project + "/";
//
//			/**
//			 * Prepare data:
//			 * 
//			 * Figure out the common first tokens of all method names. Select the methods of
//			 * which method names start with these common first tokens.
//			 */
//			DataInitializer dataInit = new DataInitializer();
//			dataInit.QUANTITY = QUANTITY;
//			dataInit.MIN_SIZE = MIN_SIZE;
//			dataInit.inputPath = inputPath;
//			dataInit.outputPath1 = outputPath1;
////			dataInit.outputPath2 = outputPath2;
////			dataInit.renamedMethodsPath = renamedMethodsPath;
////			System.out.println("----------enter initializeData");
//			dataInit.initializeData();
////			System.out.println("----------enter selectMethod");
//			dataInit.selectMethod();
////		}
//		
//		DataMerger.merge();
//	}

	public static List<String> readProjects() {
		List<String> projects = new ArrayList<>();
		String content = FileHelper.readFile(Configuration4Con.JAVA_REPO_NAMES_FILE);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				projects.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return projects;
	}
	
	public static List<String> readProjects1111() {
		List<String> projects = new ArrayList<>();
		File file = new File(Configuration4InCon.RENAMED_METHODS_PATH);
		if(file.isDirectory()){
			File [] fileList = file.listFiles();
			for( File f:fileList){
				projects.add(f.getName());
			}
		}
		
		return projects;
	}

}
