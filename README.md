# Table Of Contents

- Data/ provides the training and test data for reproducing the approaches proposed by Liu et al. and Nguyen et al.

- ICSE2020/ contains the source code and environment list of reproducing the approaches proposed by Nguyen et al.

- MCC/ contains the source code for evaluation and data construction.

-------------------

# Reproducing Procedure

- Constructing NaturalData

   1. Collect the repositories listed in the Data/TestingRepos.txt, assume it is stored in the directory Data/JavaRepos
   
   2. Collect inconsistent methods
   
	  
      * java -jar Jars/CommitDiff.jar JavaReposPath ReposList OutputPath
      e.g., java -jar Jars/CommitDiff.jar Data/JavaRepos Data/JavaRepos/TestingRepos.txt ~/OutputPath_CommitDiffs
	  
      * java -jar Jars/RenamedMethodsCollector.jar ReposList CommitDiffPath OutputPath
      e.g., java -jar Jars/RenamedMethodsCollector.jar Data/JavaRepos/TestingRepos.txt ~/OutputPath_CommitDiffs/CommitDiffs ~/OutputPath_RenamedMethods
	  
   3. Collect consistent methods
   
      * put MCC/reposAndLongestDuration.txt to /tmp and run java -jar Jars/rollBackRepos.jar 
	  
      * java -jar Jars/JavaFileGetter.jar ReposList
      e.g., java -jar Jars/JavaFileGetter.jar Data/JavaRepos/TestingRepos.txt 
	  
      * java -jar Jars/MethodParser.jar allJavaFilesFile OutputPath
      e.g., java -jar Jars/MethodParser.jar ~/JavaFiles.txt ~/OutputPath_ParsedMethods
	  
      
- Reproducing LIU's

To reproduce the performance of LIU's, one can find the source code and detailed procedures in https://github.com/SerVal-DTF/debug-method-name.

- Reproducing MNIRE

   1. Prepare the environment: 
   
      * OS: Ubuntu 18.04.1; CPU: 32 * Intel(R) Xeon(R) CPU E5-2620 v4 @ 2.10GHz; GPU: 4* TITAN RTX; RAM: 128GB
	  
      * conda install --yes --file condalist.txt
	  
   2. Configure jupyter notebook in your environment
   
   3. Train the model by running ICSE2020/train.ipynb
   
   4. Predict method names by running ICSE2020/predict.ipynb using the model generated from step 3.
   
   5. Get the final identification result by running MCC/src/main/java/org/bit/util/functions/ICSE2020_evaluate.java
