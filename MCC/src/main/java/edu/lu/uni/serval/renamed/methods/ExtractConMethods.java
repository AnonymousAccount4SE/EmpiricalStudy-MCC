package edu.lu.uni.serval.renamed.methods;

import edu.lu.uni.serval.MainParser;
import edu.lu.uni.serval.akka.JavaFile.getter.JavaFileGetter;
import edu.lu.uni.serval.dlMethods.DataPreparer4Con;
import edu.lu.uni.serval.git.exception.GitRepositoryNotFoundException;
import edu.lu.uni.serval.git.exception.NotValidGitRepositoryException;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class ExtractConMethods {
    public static void main(String [] args) throws GitAPIException, NotValidGitRepositoryException, GitRepositoryNotFoundException, IOException {
        ResetGitRepos resetGitRepos = new ResetGitRepos();
        MainParser mainParser = new MainParser();
        /**
         * roll all the repos back to initial commit
         */

        Map<String,Integer> reposAndLongestDuration=null;
        try {
//			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(basePath + "methodInfoAndBodyMap.txt"));
            ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream( "/tmp/reposAndLongestDuration.txt"));
            reposAndLongestDuration = (Map<String,Integer>) ois1.readObject();
//			success1000MethodInfoAndBodyMap = (HashMap<String, String>) ois1.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
        resetGitRepos.rollAllReposToSpecificCommit(reposAndLongestDuration);

        /**
         * extract all the methods from initial commit
         */
//        String allJavaFilesFile = Configuration4Con.JAVA_FILES_FILE;

//        JavaFileGetter4Testing.main(null);
//        mainParser.parseMethodsWithMultipleThreads(allJavaFilesFile);
        /**
         * filter the methods which has been renamed on any commits
         * also the body keeps untouched.
         */
        DataPreparer4Con.main(args);

    }
}
