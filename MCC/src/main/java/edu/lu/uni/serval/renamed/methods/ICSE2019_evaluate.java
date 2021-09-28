/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package edu.lu.uni.serval.renamed.methods;/*
 *   @author Michael
 *   @create 2021/1/6
 */

import edu.lu.uni.serval.utils.FileHelper;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class ICSE2019_evaluate {

    public static void main(String [] args) throws IOException {
//        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/RealRatioData_0.01_300_4/";
//        String basePath1 = "/home1/michael/BadMethodName/Output_TestingRepos/FurtherSplitData_0.01_300_4/";
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_2/realVectors/";
//        String filePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_inconsistent_correct.txt";
//        String filePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\Top1_PredictResult.txt";
//        String inconsistentFailFilePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\inconsistentFailAnalysis_Top1.txt";
//        String consistentFailFilePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/RealRatio/consistentFailAnalysis_Top1.txt";
//        String consistentFailFilePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_2/realVectors/consistentFailAnalysis_Top1.txt";
//        String consistentFailFilePath = basePath + "consistentFailAnalysis_Top1.txt";
//        String consistentSuccessFilePath = basePath + "consistentSuccessAnalysis_Top1.txt";
//        String consistentSuccessFilePath = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\consistentSuccessAnalysis_Top1.txt";
        String consistentSuccessPath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_2/realVectors/consistentSuccessAnalysis_Top1.txt";
//        String inconsistentFailFilePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\inconsistentFailAnalysis_Top1.txt";
        String analysis = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_2/realVectors/Top1_PredictResult.txt";
//        String inconsistentFailFilePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\inconsistentFailAnalysis_Top1.txt";
//        String inconsistentFailFilePath1 = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\Top1_PredictResult.txt";
//        String inconsistentFailFilePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessGetSetAnalysis_Top1.txt";
//        String inconsistentFailFilePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessShortMethodsAnalysis_Top1.txt";
//        ArrayList<Integer> indexList = getIndex(filePath);


        /*
        get Retrieved Names
         */
//        StringBuilder sb = new StringBuilder();
//        ArrayList<String> names = new ArrayList<>();
////        ArrayList<Integer> indexList = getFind_Index(consistentSuccessPath,names);
//        ArrayList<Integer> indexList = get_Index(analysis,names);
//        System.out.println(names.size());
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_2/realVectors/SimilarNamesByBody/";
//
////        getFirstTokenAndNumber(indexList,basePath);
//        ArrayList<String> retrievedNames = new ArrayList<>();
//        ArrayList<String> retrievedBodyNames = new ArrayList<>();
//        getRetrievedNames(indexList,basePath,retrievedBodyNames);
//        System.out.println(retrievedBodyNames.size());
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.01_300_2/SimilarNamesByBody/";
//        getRetrievedNames(indexList,basePath1,retrievedNames);
//        System.out.println(retrievedNames.size());
//
//        for(int i=0;i<retrievedBodyNames.size();i++){
//            sb.append(names.get(i)+"\t"+retrievedBodyNames.get(i)+"\t"+retrievedNames.get(i)+"\n");
//        }
//        FileHelper.outputToFile(basePath+"SplitAnalysis.txt",sb,false);
       /*
       get First token find*
        */
//        ArrayList<String> names = new ArrayList<>();
//        ArrayList<Integer> indexList = getFind_Index(consistentSuccessPath,names);
//        System.out.println(names.size());
//        int cnt1 = 0;
//        for(String name:names){
//            if(name.equalsIgnoreCase("find by thrift id")||name.equalsIgnoreCase("find by thrift id or throw")){
//                cnt1++;
//            }
//        }
//        System.out.println(cnt1);
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_2/realVectors/SimilarNamesByBody/";
//
////        getFirstTokenAndNumber(indexList,basePath);
//        ArrayList<String> retrievedNames = new ArrayList<>();
//        ArrayList<String> retrievedBodyNames = new ArrayList<>();
//        getRetrievedNames(indexList,basePath,retrievedBodyNames);
//        System.out.println(retrievedBodyNames.size());
//        int cnt = 0;
//        for(String name:retrievedBodyNames){
//            if(name.equalsIgnoreCase("find by thrift id")||name.equalsIgnoreCase("find by thrift id or throw")){
//                cnt++;
//            }
//        }
//        System.out.println(cnt);
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.01_300_2/SimilarNamesByBody/";
//        getRetrievedNames(indexList,basePath1,retrievedNames);
//        System.out.println(retrievedNames.size());

//        FileHelper.outputToFile(basePath+"SplitAnalysis.txt",sb,false);


//        System.out.println(indexList);
//        ArrayList<Integer> indexList = getIndex(consistentSuccessFilePath);
//        ArrayList<Integer> indexList = getIndex(consistentFailFilePath);
//        ArrayList<Integer> indexList = getShortMethodsIndex(inconsistentFailFilePath);
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
//        String basePath = "E:\\Workspace\\Evaluation_new\\0228_1_1_more\\";
//        String basePath = "/home1/likejun/Michael/icse2019DataSet/0724/";
//        String basePath = "/home1/michael/BadMethodName/Training/Balanced/";

//        String basePath = "/home1/michael/BadMethodName/Training/RealRatio/";
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/0.001_300_2/";
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/CNN_0.001/";
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/";
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/FurtherSplit/";
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/RealRatio/";
//        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData1/";



        /*
            0.01_300_4 716:716
         */
//        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/BodyVectors/0.01_300_4/OriginalBalancedData_0.01_300_4/";
//        String basePath1 = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/Eva/OriginalBalancedData/";
//        String SimilarNames = basePath1 + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath + "SimilarNamesByBody/";
//        String methodNamesFile = basePath1 + "TestMethodNames.txt";
//        String labelsFile = basePath1 + "TestLabels.txt";

//        /*
//            0.01_300_4 1432:1432
//         */
//        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/BodyVectors/0.01_300_4/BalancedData1_0.01_300_4/";
//        String basePath1 = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/Eva/BalancedData1/";
//        String SimilarNames = basePath1 + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath + "SimilarNamesByBody/";
//        String methodNamesFile = basePath1 + "TestMethodNames_new.txt";
//        String labelsFile = basePath1 + "TestLabels_new.txt";

        /*
            Cross-Project final 1432:1432
         */
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_88/NameVectors/Cross-Project/";
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_5/Cross-Project/";
//        String basePath2 = "/home1/michael/BadMethodName/Training/TestingData/Cross-Method/FinalVersion/";
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
//        String methodNamesFile = basePath2 + "TestMethodNames.txt";
//        String labelsFile = basePath2 + "TestLabels.txt";

                /*
            0.01_300_4 1402:1403
         */
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/BalancedData/";
//        String basePath = "/home1/michael/BadMethodName/debug-method-name-master/Output/NameVectors/";
//        String testPath = "/home1/michael/BadMethodName/debug-method-name-master/Data/TestData/";
//        String basePath1 = "/home1/michael/BadMethodName/debug-method-name-master/Output/BodyVectors/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/BalancedData/";
//
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
//        String methodNamesFile = testPath + "TestMethodNames.txt";
//        String labelsFile = testPath + "TestLabels.txt";




                /*
            0.001_300_4 1432:1432
         */
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/BalancedData/";
////        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/Eva/BalancedData1/";
//
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_6/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/output_word2vec_0.001_300_4/BalancedData/";
//
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
//        String methodNamesFile = basePath + "TestMethodNames_new.txt";
//        String labelsFile = basePath + "TestLabels_new.txt";


                   /*
            0.001_300_4 Cross-Method (remaining C)
         */
        String basePath = "/home1/michael/BadMethodName/Training/Tuning/word2vec/Cross-Method/NameVectors/Cross-Method1/";
        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_100/";
        String basePath2 = "/home1/michael/BadMethodName/Training/TestingData/ConsistentMethods/";
        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
        String methodNamesFile = basePath2 + "test_names.txt";
        String labelsFile = basePath2 + "TestLabels.txt";

           /*
            0.001_300_4 incon separately
         */
//        String basePath = "/home1/michael/BadMethodName/Training/Cross-Method/Corpus+CON/";
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_14/";
//        String basePath2 = "/home1/michael/BadMethodName/Training/TestingData/Cross-Method/";
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
//        String methodNamesFile = basePath2 + "TestMethodNames_INCON.txt";
//        String labelsFile = basePath2 + "TestLabels_INCON.txt";

        /*
            0.001_300_4 con separately
         */
//        String basePath = "/home1/michael/BadMethodName/Training/Cross-Method/Corpus+INCON/";
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_13/";
//        String basePath2 = "/home1/michael/BadMethodName/Training/TestingData/Cross-Method/";
////        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_2/";
//        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
//        String methodNamesFile = basePath2 + "TestMethodNames_CON.txt";
//        String labelsFile = basePath2 + "TestLabels_CON.txt";


        /*
            0.01_300_4 762:762
         */

//          String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/Eva/OriginalBalancedData/";
//          String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//          String SimilarBodies = basePath + "SimilarNamesByBody/";
//          String methodNamesFile = basePath + "TestMethodNames.txt";
//          String labelsFile = basePath + "TestLabels.txt";

          /*
             0.01 300 4 real ratio
           */
//        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/BodyVectors/0.01_300_4/RealRatio_0.01_300_4/";
//        String basePath1 = "/home1/michael/BadMethodName/Output_TestingRepos/RealRatioData_0.01_300_4/";
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/0.025_300_4/RealRatio/";
//        String basePath  = "/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_2/realVectors/";
//        String SimilarNames = basePath1 + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath + "SimilarNamesByBody/";
////        String methodNamesFile = basePath1 + "Eva/TestMethodNames.txt";
//        String methodNamesFile = basePath1 + "TestMethodNames.txt";
////        String labelsFile = basePath1 + "Eva/TestLabels.txt";
//        String labelsFile = basePath1 + "TestLabels.txt";

        /*
            0.001 300 4 further split
         */
//        String basePath = "/home1/michael/BadMethodName/Training/Tuning/0.025_300_4/RealRatio/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.001_300_8/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.001_300_2/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.01_300_8/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.01_300_2/";
//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/word2vec_0.01_300_2_3/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.025_300_4/";
////        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/word2vec/furtherSplit/0.025_300_8/";
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
//        String methodNamesFile = basePath + "TestMethodNames.txt";
//        String labelsFile = basePath + "TestLabels.txt";





        /*
            0.01 300 4 further split
         */

//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath1 + "SimilarNamesByBody/";
//        String methodNamesFile = basePath + "Eva/TestMethodNames.txt";
//        String labelsFile = basePath + "Eva/TestLabels.txt";

         /*
            0.01 300 4 further split < 88 tokens
         */
//        String basePath = "/home1/michael/BadMethodName/Output_TestingRepos/NewBalancedData/BodyVectors/0.01_300_4/FurtherSplit_0.01_300_4/";
//        String basePath2 = "/home1/michael/BadMethodName/Output_TestingRepos/RealRatioData_0.01_300_4/";
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1/";
//        String SimilarBodies = basePath + "SimilarNamesByBody/";
//        String methodNamesFile = basePath2 + "Eva/TestMethodNamesLT88tokens.txt";
//        String labelsFile = basePath2 + "Eva/TestLabelsLT88tokens.txt";

//        String basePath1 = "/home1/michael/BadMethodName/Training/Tuning/0.025_300_4/RealRatio/";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\1_1_more_unique\\";
//        String SimilarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1\\";

//        String SimilarBodies = basePath + "BodyVectors/0.001_300_4/SimilarNamesByBody/";
//        String SimilarBodies = basePath + "SimilarNamesByBody/";
//        String methodNamesFile = basePath + "TestMethodNames.txt";

//        String labelsFile = basePath + "TestLabels.txt";

//        String trainingPath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";

//        frequencyCorrelation(map);
//        IsPredictedCorrectly(indexList,SimilarNames,SimilarBodies);

        // 1:1 data
        evaluate_Top1(basePath,SimilarNames,SimilarBodies,methodNamesFile,labelsFile);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,5,labelsFile);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,10,labelsFile);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,20,labelsFile);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,30,labelsFile);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,40,labelsFile);

        //real data:
//        evaluate_Top1(SimilarNames,SimilarBodies,methodNamesFile);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,5);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,10);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,20);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,30);
//        evaluate_TopMore(SimilarNames,SimilarBodies,methodNamesFile,40);

        // why icse2019 consistent --> inconsistent
//        evaluateWhyIsWrong_icse2019(indexList,SimilarNames,SimilarBodies,methodNamesFile);

        //why icse2019 inconsistent --> consistent
//        evaluateWhyIsWrong_GetSet_icse2019(indexList,SimilarNames,SimilarBodies,inconsistentFailFilePath1);


        //why icse2019 getset work
//        evaluateWhyGetSetWorks_icse2019(indexList,SimilarNames,SimilarBodies);

        //why icse2019 short methods work
//        evaluateWhyShortMethodWorks_icse2019(indexList,SimilarNames,SimilarBodies);

        //why icse2019 fail
//        evaluateWhyItFails_icse2019(basePath);
    }


//    private static void evaluateWhyItFails_icse2019(String basePath) throws IOException {
//        String inconsisPath = basePath + "DL_Data\\RenamedMethods\\MethodTokens.txt";
//        HashMap<Integer,Integer> inconsistentMap = new HashMap<>();
//        ShortMethodsProcess.getIndexAndTokenNumbers(inconsisPath,inconsistentMap);
//        System.out.println(inconsistentMap.size());
//
//        String consisPath = basePath + "DL_Data\\SelectedData\\SelectedMethodTokens.txt";
//        HashMap<Integer,Integer> consistentMap = new HashMap<>();
//        ShortMethodsProcess.getIndexAndTokenNumbers(consisPath,consistentMap);
//        System.out.println(consistentMap.size());
//
//        String predictResults = basePath + "real_more_0110\\Top1_PredictResult.txt";
//        ArrayList<String> lines = ParserMethodNameMain.getLines(predictResults);
//        HashMap<Integer,Integer> indexAndResultMap = new HashMap<>();
//        for(String result:lines){
//            int index = result.indexOf("index:");
////            System.out.println(index);
//            int tabIndex = result.indexOf(" ");
////            System.out.println(tabIndex);
////            System.out.println(i);
//            int indice = Integer.valueOf(result.substring(index+6,tabIndex));
//            int originalTagIndex = result.indexOf("originalTag:");
//            int predictedTagIndex = result.indexOf("predictedTag:");
//            String originalTag = result.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
//            String predictedTag = result.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
//            if(originalTag.equals(predictedTag))
//                indexAndResultMap.put(indice,1);// predict successfully
//            else
//                indexAndResultMap.put(indice,0);
//        }
//        System.out.println(indexAndResultMap.size());
//
//        int cntGT80=0,cntFail=0;
//        for(int i=0;i<inconsistentMap.size();i++){
//            if(inconsistentMap.get(i)<10){
////                System.out.println(inconsistentMap.get(i));
//                cntGT80++;
//                if(indexAndResultMap.get(i)==0){
////                    System.out.println(indexAndResultMap.get(i));
//                    cntFail++;
//                }
//            }
//        }
//        System.out.println(cntGT80+","+cntFail);
//
//
//        cntGT80=0;cntFail=0;
//        int cnt = 0;
//        for(int i=0;i< consistentMap.size();i++){
//            if(indexAndResultMap.get(i+3379)==null){
//                cnt++;
////                System.out.println("not exist:"+indexAndResultMap.get(i));
//                continue;
//            }
//            if(consistentMap.get(i)<10){
////                System.out.println(consistentMap.get(i));
//                cntGT80++;
//                if(indexAndResultMap.get(i+3379)==0){
////                    System.out.println(indexAndResultMap.get(i));
//                    cntFail++;
//                }
//            }
//        }
//        System.out.println(cntGT80+","+cntFail);
//        System.out.println(cnt);
//    }

//    private static void evaluateWhyShortMethodWorks_icse2019(ArrayList<Integer> indexList, String similarNames,
//                                                             String similarBodies) throws IOException {
//        int cnt = 0;
//        StringBuilder nameSimSB = new StringBuilder();
//        StringBuilder bodySimSB = new StringBuilder();
//        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
//        String trainingBodyTokenPath = basePath + "TrainingMethodBodyTokens.txt";
//        HashMap<Integer,Integer> trainingBodyTokenMap = new HashMap<>();
//        ShortMethodsProcess.getIndexAndTokenNumbers(trainingBodyTokenPath,trainingBodyTokenMap);
////        System.out.println(trainingBodyTokenMap.size());
////        System.out.println(trainingBodyTokenMap);
//
////        System.out.println(indexList.size());
//        for(int index:indexList) {
//            String Name_txt = similarNames + index + ".txt";
//            String Body_txt = similarBodies + index + ".txt";
//            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
//            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);
//            String sim = list2.get(0).split("@")[2].trim();
////            if(sim.equals("nan")) continue;
////            getsetLines.append(list.get(0)+"\n");
//            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
//            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
//            String trainingIndex = list2.get(0).split("@")[0];
////            System.out.println(trainingIndex);
//            String NameSim = list1.get(0).split("@")[2];
//            String BodySim = list1.get(0).split("@")[2];
////            System.out.println(firstToken);
//            if(trainingBodyTokenMap.get(Integer.valueOf(trainingIndex))<10){
////            if((firstBodyToken.equalsIgnoreCase("get")&& firstNameToken.equalsIgnoreCase("get"))||
////                    (firstBodyToken.equalsIgnoreCase("set")&&firstNameToken.equalsIgnoreCase("set"))){
////                System.out.println(trainingBodyTokenMap.get(Integer.valueOf(trainingIndex)));
//                cnt++;
//                nameSimSB.append(NameSim+",");
//                bodySimSB.append(BodySim+",");
////                System.out.println(list1.get(0)+"\t"+list2.get(0));
//            }
//        }
//
//        System.out.println(cnt);
////        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessShortMethods_nameSim.txt",nameSimSB,false);
////        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessShortMethods_bodySim.txt",bodySimSB,false);
//    }

    private static void evaluateWhyGetSetWorks_icse2019(ArrayList<Integer> indexList, String similarNames,
                                                    String similarBodies) throws IOException {
        int cnt = 0;
        StringBuilder nameSimSB = new StringBuilder();
        StringBuilder bodySimSB = new StringBuilder();

        for(int index:indexList) {
            String Name_txt = similarNames + index + ".txt";
            String Body_txt = similarBodies + index + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);
            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;
//            getsetLines.append(list.get(0)+"\n");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
            String NameSim = list1.get(0).split("@")[2];
            String NBodySim = list1.get(0).split("@")[2];
//            System.out.println(firstToken);
            if((firstBodyToken.equalsIgnoreCase("get")&& firstNameToken.equalsIgnoreCase("get"))||
                    (firstBodyToken.equalsIgnoreCase("set")&&firstNameToken.equalsIgnoreCase("set"))){
                cnt++;
                nameSimSB.append(NameSim+",");
                bodySimSB.append(NBodySim+",");
//                System.out.println(list1.get(0)+"\t"+list2.get(0));
            }
        }

        System.out.println(cnt);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessGetSet_nameSim.txt",nameSimSB,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessGetSet_bodySim.txt",bodySimSB,false);
    }


    private static void evaluateWhyIsWrong_icse2019(ArrayList<Integer> indexList, String similarNames,
                                                    String similarBodies,String testMethodNamesPath) throws IOException {
        ArrayList<String> methodNameList = ParserMethodNameMain.getLines(testMethodNamesPath);

        int cnt1 = 0, cnt2 = 0, cnt3 = 0 ,cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int index:indexList) {
//            System.out.println("index:"+index);
            String methodName = methodNameList.get(index).trim();
            String Name_txt = similarNames + index + ".txt";
            String Body_txt = similarBodies + index + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);
            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;
            else{}
//            getsetLines.append(list.get(0)+"\n");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
//            System.out.println("firstNameToken:"+firstNameToken);
//            System.out.println("firstBodyToken:"+firstBodyToken);
//            System.out.println(firstToken);
            String firstTokenOfTestMethod = methodName.split(" ")[0];
            if(firstTokenOfTestMethod.equals(firstNameToken)){
//                System.out.println(firstTokenOfTestMethod+":"+firstNameToken+"\t"+firstBodyToken);
                sb.append(firstTokenOfTestMethod+":"+firstNameToken+"\t"+firstBodyToken+"\n");
                cnt1++;
            }
            else if(firstTokenOfTestMethod.equals(firstBodyToken)){
//                System.out.println(firstTokenOfTestMethod+":"+firstNameToken+"\t"+firstBodyToken);
                sb.append(firstTokenOfTestMethod+":"+firstNameToken+"\t"+firstBodyToken+"\n");
                cnt2++;
            }
            else{
//                System.out.println(firstTokenOfTestMethod+":"+firstNameToken+"\t"+firstBodyToken);
                sb.append(firstTokenOfTestMethod+":"+firstNameToken+"\t"+firstBodyToken+"\n");
                cnt3++;
            }
            cnt++;
//            if(cnt%5000==0){
//                FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\0228_real_more\\comparisonResults.txt",sb,true);
//                sb.setLength(0);
//            }
        }
        System.out.println("Same Name Number :"+ cnt1);
        System.out.println("Same Body Number :"+ cnt2);
        System.out.println("Both Not Same Number :" + cnt3);
        System.out.println("Sum:" + String.valueOf(cnt1+cnt2+cnt3));
        System.out.println("total:"+cnt);
//        FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\0228_real_more\\comparisonResults.txt",sb,true);
    }

    private static void evaluateWhyIsWrong_GetSet_icse2019(ArrayList<Integer> indexList, String similarNames,
                                                    String similarBodies,String resultPath) throws IOException {
        ArrayList<String> resultList = ParserMethodNameMain.getLines(resultPath);

        int cnt = 0;
        for(int index:indexList) {
//            System.out.println("index:"+index);
            String result = resultList.get(index);
            String Name_txt = similarNames + index + ".txt";
            String Body_txt = similarBodies + index + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);
            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;
//            getsetLines.append(list.get(0)+"\n");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
//            System.out.println("firstNameToken:"+firstNameToken);
//            System.out.println("firstBodyToken:"+firstBodyToken);
//            System.out.println(firstToken);
            if(!(firstBodyToken.equalsIgnoreCase("get")&& firstNameToken.equalsIgnoreCase("get"))&&!
                    (firstBodyToken.equalsIgnoreCase("set")&&firstNameToken.equalsIgnoreCase("set"))){
                cnt++;
                System.out.println(result+":"+list1.get(0)+"\t"+list2.get(0));
            }
        }
        System.out.println(cnt);
    }

    public static void evaluate_Top1(String basePath, String basePath1,String basePath2,String basePath3,String labelsFile)
            throws IOException {
//        StringBuilder getsetLines = new StringBuilder();
        ArrayList<String> methodNames = ParserMethodNameMain.getLines(basePath3);
        ArrayList<String> labels = ParserMethodNameMain.getLines(labelsFile);
        ArrayList<String> mark = new ArrayList<>();
        int TP=0,FP=0,FN=0,TN=0;
        for(int i=0;i<methodNames.size();i++){
            StringBuilder line = new StringBuilder();
            String Name_txt = basePath1 + i + ".txt";
            String Body_txt = basePath2 + i + ".txt";

            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);

            if(list1.size()==0||list2.size()==0) continue;

            String sim = list2.get(0).split("@")[2].trim();
//            System.out.println(sim);
            if(sim.equals("nan")) continue;
            line.append("index:"+i+" ");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
//            System.out.println(firstBodyToken);
//            System.out.println(firstNameToken);
            String methodName = methodNames.get(i);
            String [] splitArray = methodName.split(" ");
            line.append("[");
            for(int j=0;j< splitArray.length;j++){
                if(j!= splitArray.length-1)
                    line.append(splitArray[j]+",");
                else
                    line.append(splitArray[j]+"] ");
            }

            if(labels.get(i).trim().equals("0")){
                line.append("originalTag:0 ");
                if (!firstNameToken.equals(firstBodyToken)) {
                    line.append("predictedTag:0 "+"\n");
                    TP++;
                    mark.add("1");
                } else {
                    line.append("predictedTag:1 "+"\n");
                    FN++;
                    mark.add("0");
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
            else{
                line.append("originalTag:1 ");
                if (!firstNameToken.equals(firstBodyToken)) {
                    line.append("predictedTag:0 "+"\n");
                    FP++;
                    mark.add("0");
                } else {
                    line.append("predictedTag:1 "+"\n");
                    TN++;
                    mark.add("1");
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
//            System.out.println(line);
//            FileHelper.outputToFile("/home1/michael/BadMethodName/Training/RealRatio/Top1_PredictResult.txt",line,true);
//            FileHelper.outputToFile(basePath + "Top1_PredictResult.txt",line,true);

        }
//        int cnt = 0;
//        for(String s:mark){
//            if(s.equals("1"))
//                cnt++;
//        }
//        System.out.println(cnt);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home1/michael/BadMethodName/Training/Tuning/word2vec/word2vec_0.001_300_4_100/19SuccessOrFail.txt"));
            oos.writeObject(mark);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("TP:"+TP);
//        System.out.println("TN:"+TN);
//        System.out.println("FN:"+FN);
//        System.out.println("FP:"+FP);

        System.out.println(TP);
        System.out.println(TN);
        System.out.println(FN);
        System.out.println(FP);
    }

    public static void evaluate_TopMore(String basePath1,String basePath2,String basePath3,int k,String labelsFile)
            throws IOException {
//        StringBuilder getsetLines = new StringBuilder();
        ArrayList<String> methodNames = ParserMethodNameMain.getLines(basePath3);
        ArrayList<String> labels = ParserMethodNameMain.getLines(labelsFile);

        int TP=0,FP=0,FN=0,TN=0;
        for(int i=0;i<methodNames.size();i++){
            StringBuilder line = new StringBuilder();
            String Name_txt = basePath1 + i + ".txt";
            String Body_txt = basePath2 + i + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);

            HashSet<String> SimilarNameFirstTokenSet = new HashSet<>();
            HashSet<String> SimilarBodyFirstTokenSet = new HashSet<>();
            if(list1.size()==0||list2.size()==0) continue;

            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;

            line.append("index:"+i+" ");

            for(int m=0;m<k;m++){
                String firstNameToken = list1.get(m).split("@")[1].split(",")[0];
                String firstBodyToken = list2.get(m).split("@")[1].split(",")[0];
                SimilarNameFirstTokenSet.add(firstNameToken);
                SimilarBodyFirstTokenSet.add(firstBodyToken);
            }

            String methodName = methodNames.get(i);
            String [] splitArray = methodName.split(" ");
            line.append("[");
            for(int j=0;j< splitArray.length;j++){
                if(j!= splitArray.length-1)
                    line.append(splitArray[j]+",");
                else
                    line.append(splitArray[j]+"] ");
            }
            SimilarNameFirstTokenSet.retainAll(SimilarBodyFirstTokenSet);
            if(labels.get(i).equals("0")){
                line.append("originalTag:0 ");
                if (SimilarNameFirstTokenSet.isEmpty()) {
                    line.append("predictedTag:0 "+"\n");
                    TP++;
                } else {
                    line.append("predictedTag:1 "+"\n");
                    FN++;
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
            else{
                line.append("originalTag:1 ");
                if (SimilarNameFirstTokenSet.isEmpty()) {
                    line.append("predictedTag:0 "+"\n");
                    FP++;
                } else {
                    line.append("predictedTag:1 "+"\n");
                    TN++;
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
//            System.out.println(line);
//            FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\Top1_PredictResult.txt",line,true);

        }
        System.out.println(k);
        System.out.println(TP);
        System.out.println(TN);
        System.out.println(FN);
        System.out.println(FP);
    }

    public static void evaluate_Top1(String basePath1,String basePath2,String basePath3)
            throws IOException {
//        StringBuilder getsetLines = new StringBuilder();
        ArrayList<String> methodNames = ParserMethodNameMain.getLines(basePath3);

        int TP=0,FP=0,FN=0,TN=0;
        for(int i=0;i<methodNames.size();i++){
            StringBuilder line = new StringBuilder();
            String Name_txt = basePath1 + i + ".txt";
            String Body_txt = basePath2 + i + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);

            if(list1.size()==0||list2.size()==0) continue;

            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;
            line.append("index:"+i+" ");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
            String methodName = methodNames.get(i);
            String [] splitArray = methodName.split(" ");
            line.append("[");
            for(int j=0;j< splitArray.length;j++){
                if(j!= splitArray.length-1)
                    line.append(splitArray[j]+",");
                else
                    line.append(splitArray[j]+"] ");
            }

            if(i<3159){
                line.append("originalTag:0 ");
                if (!firstNameToken.equals(firstBodyToken)) {
                    line.append("predictedTag:0 "+"\n");
                    TP++;
                } else {
                    line.append("predictedTag:1 "+"\n");
                    FN++;
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
            else{
                line.append("originalTag:1 ");
                if (!firstNameToken.equals(firstBodyToken)) {
                    line.append("predictedTag:0 "+"\n");
                    FP++;
                } else {
                    line.append("predictedTag:1 "+"\n");
                    TN++;
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
//            System.out.println(line);
            FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\0228_real_more\\Top1_PredictResult.txt",line,true);

        }
//        System.out.println("TP:"+TP);
//        System.out.println("TN:"+TN);
//        System.out.println("FN:"+FN);
//        System.out.println("FP:"+FP);

        System.out.println(TP);
        System.out.println(TN);
        System.out.println(FN);
        System.out.println(FP);
    }

    public static void evaluate_TopMore(String basePath1,String basePath2,String basePath3,int k)
            throws IOException {
//        StringBuilder getsetLines = new StringBuilder();
        ArrayList<String> methodNames = ParserMethodNameMain.getLines(basePath3);

        int TP=0,FP=0,FN=0,TN=0;
        for(int i=0;i<methodNames.size();i++){
            StringBuilder line = new StringBuilder();
            String Name_txt = basePath1 + i + ".txt";
            String Body_txt = basePath2 + i + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);

            HashSet<String> SimilarNameFirstTokenSet = new HashSet<>();
            HashSet<String> SimilarBodyFirstTokenSet = new HashSet<>();
            if(list1.size()==0||list2.size()==0) continue;

            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;

            line.append("index:"+i+" ");

            for(int m=0;m<k;m++){
                String firstNameToken = list1.get(m).split("@")[1].split(",")[0];
                String firstBodyToken = list2.get(m).split("@")[1].split(",")[0];
                SimilarNameFirstTokenSet.add(firstNameToken);
                SimilarBodyFirstTokenSet.add(firstBodyToken);
            }

            String methodName = methodNames.get(i);
            String [] splitArray = methodName.split(" ");
            line.append("[");
            for(int j=0;j< splitArray.length;j++){
                if(j!= splitArray.length-1)
                    line.append(splitArray[j]+",");
                else
                    line.append(splitArray[j]+"] ");
            }
            SimilarNameFirstTokenSet.retainAll(SimilarBodyFirstTokenSet);
            if(i<3159){
                line.append("originalTag:0 ");
                if (SimilarNameFirstTokenSet.isEmpty()) {
                    line.append("predictedTag:0 "+"\n");
                    TP++;
                } else {
                    line.append("predictedTag:1 "+"\n");
                    FN++;
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
            else{
                line.append("originalTag:1 ");
                if (SimilarNameFirstTokenSet.isEmpty()) {
                    line.append("predictedTag:0 "+"\n");
                    FP++;
                } else {
                    line.append("predictedTag:1 "+"\n");
                    TN++;
//                System.out.println(firstNameToken + ":" + firstBodyToken);
                }
            }
//            System.out.println(line);

        }
//        System.out.println("TP:"+TP);
//        System.out.println("TN:"+TN);
//        System.out.println("FN:"+FN);
//        System.out.println("FP:"+FP);
        System.out.println(TP);
        System.out.println(TN);
        System.out.println(FN);
        System.out.println(FP);
    }
    public static void IsPredictedCorrectly(ArrayList<Integer> indexList,String basePath1,String basePath2) throws IOException {
//        StringBuilder getsetLines = new StringBuilder();
        int cnt = 0;
        for(int index:indexList) {
            String Name_txt = basePath1 + index + ".txt";
            String Body_txt = basePath2 + index + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);
            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;
//            getsetLines.append(list.get(0)+"\n");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
//            System.out.println(firstToken);
            if (!firstNameToken.trim().equals(firstBodyToken.trim())) {
                cnt++;
            } else {
//                System.out.println(firstNameToken + ":" + firstBodyToken);
            }
        }
        System.out.println(cnt);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetNamePredictResult.txt",getsetLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetBodyPredictResult.txt",getsetLines,false);
    }

    public static void getRetrievedNames(ArrayList<Integer> indexList,String basePath,ArrayList<String> retrievedNames) throws IOException {
        HashMap<String,Integer> firstTokenAndNumber = new HashMap<>();
        StringBuilder getsetLines = new StringBuilder();
        for(int index:indexList){
            String txt = basePath + index+".txt";
            ArrayList<String> list = ParserMethodNameMain.getLines(txt);
            getsetLines.append(list.get(0)+"\n");
            String name = list.get(0).split("@")[1].replaceAll(","," ");
            retrievedNames.add(name);
            String firstToken = list.get(0).split("@")[1].split(",")[0];
//            System.out.println(firstToken);
            if(firstTokenAndNumber.get(firstToken)==null){
                firstTokenAndNumber.put(firstToken,1);
            }
            else{
                int num = firstTokenAndNumber.get(firstToken);
                firstTokenAndNumber.put(firstToken,num+1);
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
//        System.out.println(list);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetNamePredictResult.txt",getsetLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetBodyPredictResult.txt",getsetLines,false);
    }
    public static void getFirstTokenAndNumber(ArrayList<Integer> indexList,String basePath) throws IOException {
        HashMap<String,Integer> firstTokenAndNumber = new HashMap<>();
        StringBuilder getsetLines = new StringBuilder();
        for(int index:indexList){
            String txt = basePath + index+".txt";
            ArrayList<String> list = ParserMethodNameMain.getLines(txt);
            getsetLines.append(list.get(0)+"\n");
            String firstToken = list.get(0).split("@")[1].split(",")[0];
//            System.out.println(firstToken);
            if(firstTokenAndNumber.get(firstToken)==null){
                firstTokenAndNumber.put(firstToken,1);
            }
            else{
                int num = firstTokenAndNumber.get(firstToken);
                firstTokenAndNumber.put(firstToken,num+1);
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println(list);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetNamePredictResult.txt",getsetLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\Top1_consistent_correct_getsetBodyPredictResult.txt",getsetLines,false);
    }
    public static ArrayList<Integer> get_Index(String filePath,ArrayList<String> names) throws IOException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        ArrayList<Integer> indexList = new ArrayList<>();

        for(String line:lines){
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");
            String Name = line.substring(leftSquareBracketsIndex+1,line.indexOf("]",leftSquareBracketsIndex+1)).replaceAll(","," ");

            int firstCommaIndex = line.indexOf(",");
            String firstToken = "";
            if(firstCommaIndex==-1){
                firstToken = line.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex);
            }
            else{
                firstToken = line.substring(leftSquareBracketsIndex+1,firstCommaIndex);
            }
            System.out.println(firstToken);
            int index = line.indexOf("index:");
//            System.out.println(index);
//            int tabIndex = line.indexOf("\t");
            int tabIndex = line.indexOf(" ");
//            System.out.println(tabIndex);
            int indice = Integer.valueOf(line.substring(index+6,tabIndex));
//            System.out.println(indice);
//            if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set"))
//            if(firstToken.equals("find")){
                indexList.add(indice);
                names.add(Name);
//            }

        }
//        for(int i:indexList){
//            System.out.println(i);
//        }
//        System.out.println(indexList.size());
        System.out.println(indexList.size());
        return indexList;
    }

    public static ArrayList<Integer> getFind_Index(String filePath,ArrayList<String> names) throws IOException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        ArrayList<Integer> indexList = new ArrayList<>();

        for(String line:lines){
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");
            String Name = line.substring(leftSquareBracketsIndex+1,line.indexOf("]",leftSquareBracketsIndex+1)).replaceAll(","," ");

            int firstCommaIndex = line.indexOf(",");
            String firstToken = "";
            if(firstCommaIndex==-1){
                firstToken = line.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex);
            }
            else{
                firstToken = line.substring(leftSquareBracketsIndex+1,firstCommaIndex);
            }
            System.out.println(firstToken);
            int index = line.indexOf("index:");
//            System.out.println(index);
//            int tabIndex = line.indexOf("\t");
            int tabIndex = line.indexOf(" ");
//            System.out.println(tabIndex);
            int indice = Integer.valueOf(line.substring(index+6,tabIndex));
//            System.out.println(indice);
//            if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set"))
            if(firstToken.equals("find")){
                indexList.add(indice);
                names.add(Name);
            }

        }
//        for(int i:indexList){
//            System.out.println(i);
//        }
//        System.out.println(indexList.size());
        System.out.println(indexList.size());
        return indexList;
    }


    public static ArrayList<Integer> getIndex(String filePath) throws IOException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        ArrayList<Integer> indexList = new ArrayList<>();

        for(String line:lines){
//            int leftSquareBracketsIndex = line.indexOf("[");
//            int rightSquareBracketsIndex = line.indexOf("]");
//
//            int firstCommaIndex = line.indexOf(",");
//            String firstToken = "";
//            if(firstCommaIndex==-1){
//                firstToken = line.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex);
//            }
//            else{
//                firstToken = line.substring(leftSquareBracketsIndex+1,firstCommaIndex);
//            }
//            System.out.println(firstToken);
            int index = line.indexOf("index:");
//            System.out.println(index);
//            int tabIndex = line.indexOf("\t");
            int tabIndex = line.indexOf(" ");
//            System.out.println(tabIndex);
            int indice = Integer.valueOf(line.substring(index+6,tabIndex));
//            System.out.println(indice);
//            if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set"))
                indexList.add(indice);
        }
//        for(int i:indexList){
//            System.out.println(i);
//        }
//        System.out.println(indexList.size());
        System.out.println(indexList.size());
        return indexList;
    }

//    public static ArrayList<Integer> getShortMethodsIndex(String filePath) throws IOException {
//        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\";
//        ArrayList<Integer> indexList = new ArrayList<>();
//        String consisPath = basePath + "real_more\\DL_Data\\SelectedData\\SelectedMethodTokens_1.txt";
//        HashMap<Integer,Integer> consistentMap = new HashMap<>();
//        ShortMethodsProcess.getIndexAndTokenNumbers(consisPath,consistentMap);
//        for(String line:lines){
//            int leftSquareBracketsIndex = line.indexOf("[");
//            int rightSquareBracketsIndex = line.indexOf("]");
//
//            int firstCommaIndex = line.indexOf(",");
//            String firstToken = "";
//            if(firstCommaIndex==-1){
//                firstToken = line.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex);
//            }
//            else{
//                firstToken = line.substring(leftSquareBracketsIndex+1,firstCommaIndex);
//            }
////            System.out.println(firstToken);
//            int index = line.indexOf("index:");
////            System.out.println(index);
////            int tabIndex = line.indexOf("\t");
//            int tabIndex = line.indexOf(" ");
////            System.out.println(tabIndex);
//            int indice = Integer.valueOf(line.substring(index+6,tabIndex));
////            System.out.println(indice);
////            if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set"))
//            if(consistentMap.get(indice-2303)<10)
//            indexList.add(indice);
//        }
////        for(int i:indexList){
////            System.out.println(i);
////        }
////        System.out.println(indexList.size());
//        System.out.println(indexList.size());
//        return indexList;
//    }
}
