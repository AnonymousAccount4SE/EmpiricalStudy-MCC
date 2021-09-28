/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package edu.lu.uni.serval.renamed.methods;

import edu.lu.uni.serval.git.exception.GitRepositoryNotFoundException;
import edu.lu.uni.serval.git.exception.NotValidGitRepositoryException;
import edu.lu.uni.serval.git.travel.GitRepository;
import edu.lu.uni.serval.utils.FileHelper;
import edu.lu.uni.serval.utils.MapSorter;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResetGitRepos {
    public static void main(String [] args) throws GitAPIException, IOException, NotValidGitRepositoryException, GitRepositoryNotFoundException {
        ResetGitRepos resetGitRepos = new ResetGitRepos();
        /**
         * roll all the repos back to specific commit
         */
//        resetGitRepos.rollAllReposToSpecificCommit(0);

        Map<String,Integer> reposAndLongestDuration=null;
        try {
//			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(basePath + "methodInfoAndBodyMap.txt"));
//            ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream( "/tmp/reposAndLongestDuration.txt"));
            ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream( args[0]));
            reposAndLongestDuration = (Map<String,Integer>) ois1.readObject();
//			success1000MethodInfoAndBodyMap = (HashMap<String, String>) ois1.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
        resetGitRepos.rollAllReposToSpecificCommit(reposAndLongestDuration);
        /**
         * roll specific repo back to specific commit
         */
//        resetGitRepos.rollRepoToSpecificCommit();
        /**
         * print the number of java files each repo contains
         */

//        resetGitRepos.printReposJavaFilesNumber();

        /**
         * get the number of java files each repo contains
         */
//        resetGitRepos.getReposJavaFilesNumber();
    }

    public void rollAllReposToSpecificCommit(Map<String,Integer> reposAndLongestDuration) throws NotValidGitRepositoryException, GitRepositoryNotFoundException, IOException, GitAPIException {
        List<String> projectList = readList(Configuration4InCon.JAVA_REPO_NAMES_FILE);
        List<String> errorRepoList = new ArrayList<>();
        for(String project:projectList){
            int longestDuration = reposAndLongestDuration.get(project);
            String gitRoot = Configuration4InCon.JAVA_REPOS_PATH + project + "/.git";
            GitRepository gitRepository = new GitRepository(gitRoot);
            gitRepository.open();

            try{
                List<RevCommit> commits = gitRepository.getAllCommits(false);
                Boolean isRollBack;
                isRollBack = rollBackPreRevision(gitRoot, commits.get(longestDuration).getId());
                System.out.println(longestDuration);
                System.out.println(isRollBack);

            }
            catch(org.eclipse.jgit.api.errors.CheckoutConflictException e){
                e.printStackTrace();
                errorRepoList.add(project);
                continue;
            }
            catch(org.eclipse.jgit.api.errors.NoHeadException e1){
                e1.printStackTrace();
                errorRepoList.add(project);
                continue;
            }
            catch(org.eclipse.jgit.api.errors.JGitInternalException e2){
                System.err.println(project);
                e2.printStackTrace();
                errorRepoList.add(project);
                continue;
            }
        }
        System.out.println(errorRepoList);
    }

    public void rollAllReposToSpecificCommit(int initialOrLatest) throws NotValidGitRepositoryException, GitRepositoryNotFoundException, IOException, GitAPIException {
        List<String> projectList = readList(Configuration4InCon.JAVA_REPO_NAMES_FILE);
        List<String> errorRepoList = new ArrayList<>();
        for(String project:projectList){
            String gitRoot = Configuration4InCon.JAVA_REPOS_PATH + project + "/.git";
            GitRepository gitRepository = new GitRepository(gitRoot);
            gitRepository.open();

            try{
                List<RevCommit> commits = gitRepository.getAllCommits(false);
                Boolean isRollBack;
                if(initialOrLatest==0){
                    isRollBack = rollBackPreRevision(gitRoot, commits.get(0).getId());
                }
                else{
                    isRollBack = rollBackPreRevision(gitRoot, commits.get(commits.size() - 1).getId());
                }
                System.out.println(isRollBack);


            }
            catch(org.eclipse.jgit.api.errors.CheckoutConflictException e){
                e.printStackTrace();
                errorRepoList.add(project);
                continue;
            }
            catch(org.eclipse.jgit.api.errors.NoHeadException e1){
                e1.printStackTrace();
                errorRepoList.add(project);
                continue;
            }
        }
        System.out.println(errorRepoList);
    }
    public void rollRepoToSpecificCommit() throws NotValidGitRepositoryException, GitRepositoryNotFoundException, IOException, GitAPIException {
//                String gitRoot = "/home1/michael/BadMethodName/ctakes/.git";
        String gitRoot = "/home1/michael/BadMethodName/JavaRepos/ofbiz/.git";
        GitRepository gitRepository = new GitRepository(gitRoot);
        gitRepository.open();
        List<RevCommit> commits = gitRepository.getAllCommits(false);
        System.out.println(commits.get(0).getId());
        System.out.println(commits.get(1).getId());
        System.out.println(commits.get(2).getId());
        System.out.println(commits.get(commits.size()-1).getId());
        System.out.println(commits.get(commits.size()-2).getId());
        Boolean isRollBack = rollBackPreRevision(gitRoot,commits.get(0).getId());
        System.out.println(isRollBack);
        ResetGitRepos resetGitRepos = new ResetGitRepos();
        int num = resetGitRepos.getEachRepoJavaFilesNumber("/home1/michael/BadMethodName/JavaRepos/ofbiz");
        System.out.println(num);
    }
    public void getReposJavaFilesNumber(){
        ArrayList<Integer> numList = new ArrayList<>();
        List<String> projectList = readList(Configuration4InCon.JAVA_REPO_NAMES_FILE);
        HashMap<String,Integer> reposAndJavaFileNumber = new HashMap<>();

        for(String project:projectList){
            String projectPath = Configuration4InCon.JAVA_REPOS_PATH + project;
//            System.out.println(projectPath);
            numList.add(getEachRepoJavaFilesNumber(projectPath));
            reposAndJavaFileNumber.put(project,getEachRepoJavaFilesNumber(projectPath));
        }
//        System.out.println(numList);
        int cnt = 0;
        for(int i:numList){
            if(i==0){
                cnt++;
            }
        }
        System.out.println(numList.size());
        System.out.println(cnt);
        Map<String,Integer> reposAndJavaFileNumber1 = new MapSorter().sortByValueAscending(reposAndJavaFileNumber);
//        for(Map.Entry<String,Integer> entry:reposAndJavaFileNumber1.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
        List<String> inValidReposList = new ArrayList<>();
        for(Map.Entry<String,Integer> entry:reposAndJavaFileNumber1.entrySet()){
            if(entry.getValue()==0){
                inValidReposList.add(entry.getKey());
            }
        }
        //serialized object
//        try {
////            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("success1000MethodInfoAndBodyMap.txt"));
////            ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("methodInfoAndBodyMap.txt"));
////            ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("successMethodInfoAndBodyMap.txt"));
////            ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("successGetterMethodInfoAndBodyMap.txt"));
//            ObjectOutputStream oos4 = new ObjectOutputStream(new FileOutputStream("/tmp/inValidReposList.txt"));
////            oos.writeObject(success1000MethodInfoAndBodyMap);
////            oos1.writeObject(methodInfoAndBodyMap);
////            oos2.writeObject(successMethodInfoAndBodyMap);
////            oos3.writeObject(successGetterMethodInfoAndBodyMap);
//            oos4.writeObject(inValidReposList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }
    public void printReposJavaFilesNumber(){
        List<String> projectList = readList(Configuration4InCon.JAVA_REPO_NAMES_FILE);
        for(String project:projectList){
            String projectPath = Configuration4InCon.JAVA_REPOS_PATH + project;
            printEachRepoJavaFilesNumber(projectPath);
        }
    }
    public static List<String> readList(String fileName) {
        List<String> list = new ArrayList<>();
        String content = FileHelper.readFile(fileName);
        BufferedReader reader = new BufferedReader(new StringReader(content));
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private void printEachRepoJavaFilesNumber(String filePath){
        List<File> list = FileHelper.getAllFiles(filePath,"java");
        System.out.println(list.size());
    }
    private int getEachRepoJavaFilesNumber(String filePath){
        List<File> list = FileHelper.getAllFiles(filePath,"java");
//        System.out.println(list.size());
        return list.size();
    }
    public static boolean rollBackPreRevision(String gitRoot, String revision) throws IOException, GitAPIException {

        Git git = Git.open(new File(gitRoot));

        Repository repository = git.getRepository();

        RevWalk walk = new RevWalk(repository);
        ObjectId objId = repository.resolve(revision);
        RevCommit revCommit = walk.parseCommit(objId);
//        String preVision = revCommit.getParent(0).getName();
        String thisVision = revCommit.getName();
        git.reset().setMode(ResetCommand.ResetType.HARD).setRef(thisVision).call();
        repository.close();
        return true;
    }

    public static boolean rollBackPreRevision(String gitRoot, ObjectId revision) throws IOException, GitAPIException {

        Git git = Git.open(new File(gitRoot));

        Repository repository = git.getRepository();

        RevWalk walk = new RevWalk(repository);
        RevCommit revCommit = walk.parseCommit(revision);
//        String preVision = revCommit.getParent(0).getName();
        String thisVision = revCommit.getName();
        git.reset().setMode(ResetCommand.ResetType.HARD).setRef(thisVision).call();
        repository.close();
        return true;
    }
}
