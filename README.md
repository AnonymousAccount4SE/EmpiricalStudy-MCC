# Deep Learning Based Identification of Inconsistent Method Names: How Far Are We?

# Table Of Contents

- Data/ provides our **BenMark** and the detailed evaluation results of 10-fold cross-validation.

- ICML2016/ contains the address of the source code and instructions of reproducing the approaches proposed by Allamanis et al.

- ICSE2019/ contains the address of the source code and instructions of reproducing the approaches proposed by Liu et al.

- ICSE2020/ contains the source code and environment list of reproducing the approaches proposed by Nguyen et al. (This directory contains all files that we have asked for the authors)

- MCC/ contains the source code for evaluation and construction of **BenMark**.


-------------------

# Reproduction Procedure

## Run LIU's [<sup>1</sup>](#liu)

To reproduce the performance of LIU's, one can find the source code and detailed procedures in https://github.com/SerVal-DTF/debug-method-name.

## Run MNIRE [<sup>2</sup>](#mnire)

Since Nguyen et al. did not make their source code available, we emailed them and got some instructions from them. Here are the reproducing precedures.

### Requirements

> **Hardware:** 

> OS: Ubuntu 18.04.1; CPU: 32 * Intel(R) Xeon(R) CPU E5-2620 v4 @ 2.10GHz; GPU: 4* TITAN RTX; RAM: 128GB   

> **Software:**
> Python-3.7.9;
> Tensorflow-2.2.0;
> And other requirements listed in ICSE2020/conda_environment_list.txt

### Procedures  
   1. Prepare the environment: 
   
   > conda install --yes --file ICSE2020/conda_environment_list.txt 

   2. Configure Jupyter notebook in your environment.
   
   3. Train and tune the model by running 

   > nnictl create --config ICSE2020/Tuning/config.yml
   
   4. Predict method names by running ICSE2020/predict.ipynb using the model generated from step 3.
   
   5. Get the final identification results 
   
   > java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.renamed.methods.ICSE2020_evaluate.java
   
## Run CAN [<sup>3</sup>](#can)

To reproduce the performance of Allamanis et al., one can find the source code and detailed procedures in https://groups.inf.ed.ac.uk/cup/codeattention.

## BenMark Construction

### Data Collection
   Collect the repositories listed in the Data/BenMark/AllReposNames.txt

   > e.g., git clone https://github.com/apache/odftoolkit.git
   
### Identifying Inconsistent Method Names
   
   1. Configure the testing repository path (e.g., Data/TestingRepos) and output path.
   2. Mining the commit history of each project and find all the renaming operations. Only keep the renaming in a commit where 
      * The method body keep untouched.
      * Both the method name and body keep untouched until the latest commit ever since the renaming.
   > cd debug-method-name/RenamedMethodsCollector
   
   
   > java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.renamed.methods.Main.java

### Identifying Consistent Method Names

   1. Roll each project to **n** commits ago (**n** is the largest duration between the creation time of a method and its first rename in each specific project.)
   
   > cd MCC
   
   > java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.renamed.methods.ResetGitRepos.java

   2. Configure the data path and extract all the methods in this snapshot commit.
   
   > cd debug-method-name/DebugMethodName
   
   > java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.MainParser.java

   3. Only keep the methods which are untouched until the latest commit.

   > cd MCC
   
   > java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.renamed.methods.DataPreparer4Con.java
   
### Using BenMark
	
	1. Data/BenMark/BenMark.txt contains the address of all the testing methods in **BenMark**. In addition, a label file and a name file for each method are included.
	
	2. Data/BenMark/AllReposNames.txt contains all the projects Liu et al. have collected. 
	
	3. Data/BenMark/RepoNamesForEachFold.txt contains the used projects for each fold.
	
	4. Users can construct training data from the remaining projects (excluded the projects in each fold) of AllReposNames.txt in any way they want.
	

# References
<div id="liu"></div>
- [1] K. Liu et al., "Learning to Spot and Refactor Inconsistent Method Names," 2019 IEEE/ACM 41st International Conference on Software Engineering (ICSE), 2019, pp. 1-12, DOI: 10.1109/ICSE.2019.00019.
<div id="mnire"></div>
- [2] Son Nguyen, Hung Phan, Trinh Le, and Tien N. Nguyen. 2020. Suggesting natural method names to check name consistencies. In <i>Proceedings of the ACM/IEEE 42nd International Conference on Software Engineering</i> (<i>ICSE '20</i>). Association for Computing Machinery, New York, NY, USA, 1372–1384. DOI:https://doi.org/10.1145/3377811.3380926
<div id="can"></div>
- [3] Allamanis, M., Peng, H., & Sutton, C. (2016, June). A convolutional attention network for extreme summarization of source code. In International conference on machine learning (pp. 2091-2100). PMLR.
