/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/6
 */

import com.sun.org.apache.xpath.internal.operations.Lt;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileNotFoundException;
import java.util.*;

public class ICSE2019_firstTokenNumber {

    public static void main(String [] args) throws FileNotFoundException {
//        getTrainingDataFirstTokenNumber();
//        getTrainingDataGetSetTokenIndex();

        getTestDataFirstTokenNumber();
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\";

        //first token number of inconsistent success results
//        String resultFile = basePath+"real_more_0110\\inconsistentSuccessAnalysis_Top1.txt";
//        String resultFile = basePath+"real_more_0110\\consistentFailAnalysis_Top1.txt";
//        String resultFile = basePath+"consistentSuccessAnalysis_Top1.txt";
//        HashMap<String,Integer> firstTokenAndNumber = getFirstTokenNumberOfResultFile(resultFile);
////        System.out.println(firstTokenAndNumber);
//        List<Map.Entry<String,Integer>> list = Utils.sortMap(firstTokenAndNumber);
//        System.out.println(list);

//        String parsedMethodNamesPath = basePath + "DL_Data\\RenamedMethods\\parsedMethodNames.txt";
//        String parsedMethodNamesPath = basePath + "real_more_0110\\inconsistentSuccessMethodNamePairAnalysisReport_Top1.txt";
//        String parsedMethodNamesPath = basePath + "real_more_0110\\inconsistentFailMethodNamePairAnalysisReport_Top1.txt";

        // get buggy-fixed pair first token not same number
//        getFirstTokenNotSameNumber(parsedMethodNamesPath);

//        getRetainAllTokenNumbers(parsedMethodNamesPath);

//        checkTop1NameSimIsSame(basePath);

//        getConsistentCorrectNameBodyFirstToken(basePath);
    }

    private static void getTestDataFirstTokenNumber() throws FileNotFoundException {
        String basePath = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\";
        String testMethodNames = basePath + "TestingMethodNames.txt";
        ArrayList<String> methodNamesList = ParserMethodNameMain.getLines(testMethodNames);

        String [] firstTokens = {"test","evaluate","serialize","invoke","map","iterator","encode","configure","can","merge","as","clone","make","generate","$","log","field","deep","supports","print","initialize","resolve","list","end","size","with","accept","append","apply","send","stop","from","load","contains","copy","register","run","reset","delete","handle","next","compare","validate","put","execute","convert","process","build","init","unset","on","start","check","do","close","parse","update","clear","new","visit","has","hash","equals","find","remove","read","write","add","to","create","is","set","get"};
        List firstTokenList = Arrays.asList(firstTokens);
        int cnt =0;
        for(String methodName:methodNamesList){
            String firstToken = methodName.split(" ")[0];
            System.out.println(firstToken);
            if(firstTokenList.contains(firstToken)){
                cnt++;
            }
        }
        System.out.println(methodNamesList.size());
        System.out.println(cnt);
    }

    private static void getConsistentCorrectNameBodyFirstToken(String basePath) throws FileNotFoundException {
        String resultFile = basePath + "consistentSuccessAnalysis_Top1.txt";
        String similarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1\\";
        String similarBodies = basePath + "SimilarNamesByBody\\";
        ArrayList<String> results = ParserMethodNameMain.getLines(resultFile);
        int nameCnt=0,bodyCnt=0;int cnt=0;
        System.out.println(results.size());
        for(String result:results){
            int index = Utils.getIndice(result);
            String firstToken = Utils.getFirstToken(result);
            String Name_txt = similarNames + index + ".txt";
            String Body_txt = similarBodies + index + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);
            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;
//            getsetLines.append(list.get(0)+"\n");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
            if(firstNameToken.equalsIgnoreCase("get")||firstNameToken.equalsIgnoreCase("set")){
//                System.out.println(index);
                nameCnt++;
            }
            if(firstBodyToken.equalsIgnoreCase("get")||firstBodyToken.equalsIgnoreCase("set")){
                bodyCnt++;
            }


        }
        System.out.println(nameCnt+":"+bodyCnt);
        System.out.println(cnt);
    }

    private static void checkTop1NameSimIsSame(String basePath) throws FileNotFoundException {
//        String resultFile = basePath + "real_more_0110\\Top1_PredictResult.txt";
        String resultFile = basePath + "consistentSuccessAnalysis_Top1.txt";
        String similarNames = basePath + "SimilarNamesByName_NameFeature_SubToken_1\\";
        String similarBodies = basePath + "SimilarNamesByBody\\";
        ArrayList<String> results = ParserMethodNameMain.getLines(resultFile);
        int nameCnt=0,bodyCnt=0;int cnt=0;
        System.out.println(results.size());
        for(String result:results){
            int index = Utils.getIndice(result);
            String firstToken = Utils.getFirstToken(result);
            String Name_txt = similarNames + index + ".txt";
            String Body_txt = similarBodies + index + ".txt";
            ArrayList<String> list1 = ParserMethodNameMain.getLines(Name_txt);
            ArrayList<String> list2 = ParserMethodNameMain.getLines(Body_txt);
            String sim = list2.get(0).split("@")[2].trim();
            if(sim.equals("nan")) continue;
//            getsetLines.append(list.get(0)+"\n");
            String firstNameToken = list1.get(0).split("@")[1].split(",")[0];
            String firstBodyToken = list2.get(0).split("@")[1].split(",")[0];
//            if(firstNameToken.equalsIgnoreCase(firstToken)){
//                System.out.println(index);
//                nameCnt++;
//            }
//            if(firstBodyToken.equalsIgnoreCase(firstToken)){
//                bodyCnt++;
//            }
            if(!firstNameToken.equalsIgnoreCase(firstToken)&&!firstBodyToken.equalsIgnoreCase(firstToken)&&firstNameToken.equalsIgnoreCase(firstBodyToken)){
                System.out.println(index);
                nameCnt++;
            }


        }
        System.out.println(nameCnt+":"+bodyCnt);
        System.out.println(cnt);
    }

    private static void getRetainAllTokenNumbers(String parsedMethodNamesPath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(parsedMethodNamesPath);
        int sameCnt =0,notSameCnt=0;
        double totalCnt = 0;
        int zeroCnt = 0;
        double LT50=0;
        for(String line:lines){
            String buggy = line.split("@")[0];
            String fixed = line.split("@")[1];
            String [] buggySplitArray = buggy.split(",");
            String [] fixedSplitArray = fixed.split(",");

//            List<String> buggyList = new ArrayList<>(Arrays.asList(buggySplitArray));
//            List<String> fixedList = new ArrayList<>(Arrays.asList(fixedSplitArray));
            List<String> buggyList = new ArrayList<>();
            List<String> fixedList = new ArrayList<>();
            for(String buggyOne:buggySplitArray){
                buggyList.add(buggyOne.toLowerCase());
            }
            for(String fixedOne:fixedSplitArray){
                fixedList.add(fixedOne.toLowerCase());
            }
            double sum = buggyList.size() + fixedList.size();
            buggyList.retainAll(fixedList);
            if(buggyList.size()==0) zeroCnt++;
            double sum_div2 = sum / 2;
            double sim = buggyList.size() / sum_div2;
//            System.out.println(sim);
//            System.out.println(buggyList.size());
            totalCnt+=sim;
//            if(buggyList.size()>2) zeroCnt++;
            if(sim>0.33) LT50++;
            if(sim>1) System.out.println(1.0);
            else System.out.println(sim);
        }

        System.out.println(zeroCnt);
        System.out.println("average coexist number:"+ totalCnt/(double)lines.size());
//        System.out.println("buggy-fixed:firstToken same:"+sameCnt);
//        System.out.println("buggy-fixed:firstToken not the same:"+notSameCnt);
//        System.out.println("Less than x:"+LT50/lines.size());
        System.out.println("Less than x:"+LT50);
    }

    private static void getFirstTokenNotSameNumber(String parsedMethodNamesPath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(parsedMethodNamesPath);
        int sameCnt =0,notSameCnt=0;
//        int
        for(String line:lines){
            String buggy = line.split("@")[0];
            String fixed = line.split("@")[1];
            String firstToken_buggy = buggy.split(",")[0];
            String firstToken_fixed = fixed.split(",")[0];
            if(firstToken_buggy.equals(firstToken_fixed)){
//                System.out.println(line);
                sameCnt++;
            }else{
//                System.out.println(line);
                notSameCnt++;
            }
        }
        System.out.println("buggy-fixed:firstToken same:"+sameCnt);
        System.out.println("buggy-fixed:firstToken not the same:"+notSameCnt);
    }


    public static HashMap<String,Integer> getFirstTokenNumberOfResultFile(String filePath) throws FileNotFoundException {
        ArrayList<String> results = ParserMethodNameMain.getLines(filePath);
        HashMap<String,Integer> firstTokenAndNumber = new HashMap<>();
        for(int i=0;i<results.size();i++) {
            String result = results.get(i);
//            System.out.println(result);
            String firstToken = Utils.getFirstToken(result);
            if(firstTokenAndNumber.get(firstToken)==null){
                firstTokenAndNumber.put(firstToken,1);
            }
            else{
                int num = firstTokenAndNumber.get(firstToken);
                firstTokenAndNumber.put(firstToken,num+1);
            }
        }
        return firstTokenAndNumber;
    }
    private static void getTrainingDataGetSetTokenIndex() throws FileNotFoundException {
        String filePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodInfo.txt";
        String bodyFilePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodBodyTokens.txt";
        ArrayList<String> bodyTokens = ParserMethodNameMain.getLines(bodyFilePath);
        ArrayList<Integer> indexes = getGetSetIndex(filePath);
        System.out.println(indexes.size());
        int cnt  =0;
        for(int index:indexes){
            String bodyToken = bodyTokens.get(index);
            String [] splitArray = bodyToken.split(" ");
            if(splitArray.length<15){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static ArrayList<Integer> getGetSetIndex(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        ArrayList<Integer> getSetIndex = new ArrayList<>();
        int index =0;
        int getCnt = 0;
        int setCnt = 0;
        for(String line : lines){
            String [] splitArray = line.split("@");
            String firstToken = splitArray[splitArray.length-1].split(",")[0];
//            System.out.println(firstToken);
            if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set")){
                if(firstToken.equalsIgnoreCase("get")){
                    getCnt++;
                }
                if(firstToken.equalsIgnoreCase("set")){
                    setCnt++;
                }
                getSetIndex.add(index);
            }
            index++;
        }
        System.out.println(getCnt+":"+setCnt);
        return getSetIndex;
    }
    public static void getTrainingDataFirstTokenNumber() throws FileNotFoundException {
        String filePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodInfo.txt";
//        String filePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodBodyTokens.txt";

        HashMap<String,Integer> firstTokenAndNumber = getFirstTokenNumber(filePath);
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(firstTokenAndNumber.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println(list);
    }
    public static HashMap<String,Integer> getFirstTokenNumber(String filePath) throws FileNotFoundException {
        int number = 0;
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        HashMap<String,Integer> firstTokenAndNumber = new HashMap<>();
        for(String line : lines){
            String [] splitArray = line.split("@");
            String firstToken = splitArray[splitArray.length-1].split(",")[0];
//            System.out.println(firstToken);
            if(firstTokenAndNumber.get(firstToken)==null){
                firstTokenAndNumber.put(firstToken,1);
            }
            else{
                int num = firstTokenAndNumber.get(firstToken);
                firstTokenAndNumber.put(firstToken,num+1);
            }
        }
        return firstTokenAndNumber;
    }
}
