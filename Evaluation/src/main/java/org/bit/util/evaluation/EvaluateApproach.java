package org.bit.util.evaluation;

import com.github.javaparser.utils.CollectionStrategy;
import edu.lu.uni.serval.utils.FileHelper;
import edu.lu.uni.serval.utils.MapSorter;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;
public class EvaluateApproach {
    public static void main(String [] args) throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\PredictedResults_original\\";
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\";

//        filterActualBuggyNames();

//        calculateSameFirstToken();

//        calculateFirstTokenIsGetOrSet(basePath);

//        getTop5SimilarNames(basePath);

//        getPredictSucOrFailNumberOfGetSet(basePath);
//        getPredictSucOrFailNumber(basePath);

    }



    public static void getPredictSuccess(String basePath,ArrayList<Integer> inconsistentSuccessArrayList,ArrayList<Integer> consistentSuccessArrayList) throws FileNotFoundException {
        String resultFile = basePath + "real_more_Top40.txt";
        FileInputStream fis1 = new FileInputStream(resultFile);

        ArrayList<String> inconsistentgetsetSuccessArrayList = new ArrayList<>();
        ArrayList<String> consistentgetsetSuccessArrayList = new ArrayList<>();

        HashMap<String,Integer> successFirstTokenMap = new HashMap<>();
        HashMap<String,Integer> failFirstTokenMap = new HashMap<>();
        Scanner scanner1 = new Scanner(fis1);
        int index=0;
        while(scanner1.hasNext()) {
            String line = scanner1.nextLine();
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");

            int firstCommaIndex = line.indexOf(",");
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String firstToken = "";
            if (firstCommaIndex == -1) {
                firstToken = line.substring(leftSquareBracketsIndex + 1, rightSquareBracketsIndex);
            } else {
                firstToken = line.substring(leftSquareBracketsIndex + 1, firstCommaIndex);
            }
            String originalTag = line.substring(originalTagIndex + "originalTag:".length(), originalTagIndex + "originalTag:".length() + 1);
            String predictedTag = line.substring(predictedTagIndex + "predictedTag:".length(), predictedTagIndex + "predictedTag:".length() + 1);


            if (originalTag.equals(predictedTag) && originalTag.equals("0")) {
                inconsistentSuccessArrayList.add(index);
            }
            if (originalTag.equals(predictedTag) && originalTag.equals("1")) {
                consistentSuccessArrayList.add(index);
            }
            index++;
        }

    }
    private static void getPredictSucOrFailNumber(String basePath) throws FileNotFoundException {
//        private static final DecimalFormat FORMAT = new DecimalFormat("0.000");
//        String resultFile = basePath + "real_more_Top40.txt";
        String resultFile = basePath + "Top1_PredictResult.txt";
        FileInputStream fis1 = new FileInputStream(resultFile);
        ArrayList<StringBuilder> successArrayList = new ArrayList<>();
        ArrayList<StringBuilder> failArrayList = new ArrayList<>();

        ArrayList<String> getSetArrayList = new ArrayList<>();
        ArrayList<String> getSet0ArrayList = new ArrayList<>();
        ArrayList<String> getSet1ArrayList = new ArrayList<>();

        ArrayList<String> inconsistentSuccessArrayList = new ArrayList<>();
        ArrayList<String> consistentSuccessArrayList = new ArrayList<>();
        ArrayList<String> inconsistentFailArrayList = new ArrayList<>();
        ArrayList<String> inconsistentgetsetSuccessArrayList = new ArrayList<>();
        ArrayList<String> consistentgetsetSuccessArrayList = new ArrayList<>();

        HashMap<String,Integer> successFirstTokenMap = new HashMap<>();
        HashMap<String,Integer> failFirstTokenMap = new HashMap<>();
        Scanner scanner1 = new Scanner(fis1);
        while(scanner1.hasNext()){
            String line = scanner1.nextLine();
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");

            int firstCommaIndex = line.indexOf(",");
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String firstToken = "";
            if(firstCommaIndex==-1){
                firstToken = line.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex);
            }
            else{
                firstToken = line.substring(leftSquareBracketsIndex+1,firstCommaIndex);
            }
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);


            if(originalTag.equals(predictedTag)&&originalTag.equals("0")){
                inconsistentSuccessArrayList.add(line);
            }
            if(originalTag.equals(predictedTag)&&originalTag.equals("1")){
                consistentSuccessArrayList.add(line);
            }
            if(!originalTag.equals(predictedTag)&&originalTag.equals("0")){
                inconsistentFailArrayList.add(line);
            }



            if(firstToken.equals("get")||firstToken.equals("set")){
                getSetArrayList.add(line);
//                System.out.println(line);
                if(originalTag.equals("0")){
                    getSet0ArrayList.add(line);
                }
                else{
                    getSet1ArrayList.add(line);
                }


                // predict successfully
                if(originalTag.equals(predictedTag)){
                    if(originalTag.equals("0")){
                        inconsistentgetsetSuccessArrayList.add(line);
                    }
                    else{
                        consistentgetsetSuccessArrayList.add(line);
                    }
//                    System.out.println(originalTag + ":"+predictedTag);
                    if(successFirstTokenMap.containsKey(firstToken)){
                        int value = successFirstTokenMap.get(firstToken);
                        successFirstTokenMap.put(firstToken,value+1);
                    }
                    else{
                        successFirstTokenMap.put(firstToken,1);
                    }
                    successArrayList.add(new StringBuilder().append(line).append("\n"));
                }
                // predict failed
                else{
                    if(failFirstTokenMap.containsKey(firstToken)){
                        int value = failFirstTokenMap.get(firstToken);
                        failFirstTokenMap.put(firstToken,value+1);
                    }
                    else{
                        failFirstTokenMap.put(firstToken,1);
                    }
                    failArrayList.add(new StringBuilder().append(line).append("\n"));
                }
            }

        }


        System.out.println("total get&set method numbers:"+getSetArrayList.size());
        System.out.println("consistent get&set method numbers:"+getSet1ArrayList.size());
        System.out.println("inconsistent get&set method numbers:"+getSet0ArrayList.size());

        System.out.println("consistent predicted success:"+consistentSuccessArrayList.size());
        System.out.println("inconsistent predicted success:"+inconsistentSuccessArrayList.size());

        System.out.println("consistent get set predicted success:"+consistentgetsetSuccessArrayList.size());
        System.out.println("inconsistent get set predicted success:"+inconsistentgetsetSuccessArrayList.size());
//        for(String s:inconsistentSuccessArrayList)
//        System.out.println(s);
//        System.out.println("total method numbers:"+Integer.sum(successArrayList.size(),failArrayList.size()));
//        System.out.println("predict succeeded:"+successArrayList.size());

//        for(StringBuilder s:successArrayList){
////            if(!s.toString().contains("get")&&!s.toString().contains("set"))
//            System.out.println(s);
//        }

//        System.out.println("predicted successfully token and number");
//        MapSorter<String, Integer> sorter = new MapSorter<String, Integer>();
//        Map<String,Integer> successMap = sorter.sortByValueDescending(successFirstTokenMap);
//        for(Map.Entry<String,Integer> entry :successMap.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
//        System.out.println("predicted failed token and number");
//        Map<String,Integer> failMap = sorter.sortByValueDescending(failFirstTokenMap);
//        for(Map.Entry<String,Integer> entry :failMap.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }

//        System.out.println("predict failed:"+failArrayList.size());
//        for(String s:getSetArrayList){
//            System.out.println(s);
//        }

//        System.out.println(failArrayList);
    }

    private static void getPredictSucOrFailNumberOfGetSet(String basePath) throws FileNotFoundException {
        String resultFile = basePath + "Output_1-10_Top40.txt";
        FileInputStream fis1 = new FileInputStream(resultFile);
        ArrayList<StringBuilder> successArrayList = new ArrayList<>();
        ArrayList<StringBuilder> failArrayList = new ArrayList<>();
        Scanner scanner1 = new Scanner(fis1);
        while(scanner1.hasNext()){
            String line = scanner1.nextLine();
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");

            int firstCommaIndex = line.indexOf(",");
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String firstToken = "";
            if(firstCommaIndex==-1){
                firstToken = line.substring(leftSquareBracketsIndex+1,rightSquareBracketsIndex);
            }
            else{
                firstToken = line.substring(leftSquareBracketsIndex+1,firstCommaIndex);
            }
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
//            System.out.println(originalTag + ":"+predictedTag);
            if(firstToken.equals("get")||firstToken.equals("set")){
//            if(firstToken.equals("is")){
                // predict successfully
//                System.out.println(line);
                if(originalTag.equals(predictedTag)){

                    successArrayList.add(new StringBuilder().append(line).append("\n"));
                }
                else{
                    failArrayList.add(new StringBuilder().append(line).append("\n"));
                }
            }

        }
        System.out.println("total method numbers of first token is get&set:"+Integer.sum(successArrayList.size(),failArrayList.size()));
        System.out.println("get&set predict succeeded:"+successArrayList.size());
//        System.out.println(successArrayList);

        System.out.println("get&set predict failed:"+failArrayList.size());
        System.out.println(failArrayList);


    }


    public static void getTop5SimilarNames(String basePath) throws FileNotFoundException {
        String similarNamesPath = basePath + "SimilarNamesByName_NameFeature_SubToken_1";

        int methodsNumber = 8255;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<methodsNumber;i++){

//            ArrayList<String> namesFirstTokenList = new ArrayList<>();
//            FileInputStream fis1 = new FileInputStream(similarBodiesPath + "/" +i +".txt");
            FileInputStream fis1 = new FileInputStream(similarNamesPath + "/" +i +".txt");

            Scanner scanner1 = new Scanner(fis1);
            int cnt =1;
            while(scanner1.hasNext()){
                String line1 = scanner1.nextLine();
                String [] splitArray = line1.split("@");
                String similarities = splitArray[splitArray.length-1];
                stringBuilder.append(similarities).append(" ");
                if(cnt == 5){
//                    System.out.println(stringBuilder);
                    stringBuilder.append("\n");
                    break;
                }
                cnt ++;
            }
            FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\original.txt",stringBuilder,false);
        }
    }
    public static void calculateFirstTokenIsGetOrSet(String basePath) throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\PredictedResults_4projectsDir\\";

//        String similarBodiesPath = basePath + "SimilarNamesByBody";
        String similarNamesPath = basePath + "SimilarNamesByName_NameFeature_SubToken_1";
//        ArrayList<String> bodiesFirstTokenList = new ArrayList<>();

//        int methodsNumber = 4961;
        int methodsNumber = 2805;
//        ArrayList<Integer> firstTokenIsGetOrSetOccurrenceNumbers = new ArrayList<>();
        int firstTokenIsGetOrSetOccurrenceNumbers =0;
        for(int i=0;i<methodsNumber;i++){

//            ArrayList<String> namesFirstTokenList = new ArrayList<>();
//            FileInputStream fis1 = new FileInputStream(similarBodiesPath + "/" +i +".txt");
            FileInputStream fis1 = new FileInputStream(similarNamesPath + "/" +i +".txt");

            Scanner scanner1 = new Scanner(fis1);
            while(scanner1.hasNext()){
                String line1 = scanner1.nextLine();
                String [] splitArray = line1.split("@");
                String firstToken = splitArray[1].replaceAll("\\[","").split(",")[0];
                if(firstToken.contains("get") || firstToken.contains("set") && !firstToken.contains("reset")
                        && !firstToken.contains("offset")){
                    System.out.println(firstToken);
                    firstTokenIsGetOrSetOccurrenceNumbers++;
                }
                break;
            }
//            tokenOccurrenceNumbers.add(Collections.frequency(namesFirstTokenList, originalNamesFirstTokenList.get(i)));
//            tokenOccurrenceNumbers.add(Collections.frequency(namesFirstTokenList.subList(0,40), originalNamesFirstTokenList.get(i)));
//            System.out.println(namesFirstTokenList);
        }
        System.out.println(firstTokenIsGetOrSetOccurrenceNumbers);

    }
    public static void calculateSameFirstToken() throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\PredictedResults_4projectsDir\\";
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\PredictedResults_original\\";
//        String similarBodiesPath = basePath + "SimilarNamesByBody";
        String similarNamesPath = basePath + "SimilarNamesByName_NameFeature_SubToken_1";
//        ArrayList<String> bodiesFirstTokenList = new ArrayList<>();

//        String methodNamePairsPath = basePath + "methodNamePairs";
        String methodNamePairsPath = basePath + "parsedMethodNames.txt";
        ArrayList<String> originalNamesFirstTokenList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(methodNamePairsPath);
        Scanner scanner = new Scanner(fis);
        while(scanner.hasNext()){
            String line1 = scanner.nextLine();
            String [] splitArray = line1.split("@");
            String firstToken = splitArray[0].split(",")[0];
            originalNamesFirstTokenList.add(firstToken);
        }
        System.out.println(originalNamesFirstTokenList.size());

        int methodsNumber = 2805;
//        int methodsNumber = 4961;
        ArrayList<Integer> tokenOccurrenceNumbers = new ArrayList<>();
        for(int i=0;i<methodsNumber;i++){

            ArrayList<String> namesFirstTokenList = new ArrayList<>();
            FileInputStream fis1 = new FileInputStream(similarNamesPath + "/" +i +".txt");
            Scanner scanner1 = new Scanner(fis1);
            while(scanner1.hasNext()){
                String line1 = scanner1.nextLine();
                String [] splitArray = line1.split("@");
                String firstToken = splitArray[1].replaceAll("\\[","").split(",")[0];
                namesFirstTokenList.add(firstToken);
            }
//            tokenOccurrenceNumbers.add(Collections.frequency(namesFirstTokenList, originalNamesFirstTokenList.get(i)));
            tokenOccurrenceNumbers.add(Collections.frequency(namesFirstTokenList.subList(0,40), originalNamesFirstTokenList.get(i)));
//            System.out.println(namesFirstTokenList);
        }
        System.out.println(tokenOccurrenceNumbers);
        System.out.println(Collections.frequency(tokenOccurrenceNumbers,0));

    }
    public static void filterActualBuggyNames() throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\PredictedResults_4projectsDir\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\PredictedResults_original\\";
        String methodNamePairsPath = basePath + "methodNamePairs";
//        String methodNamePairsPath = basePath + "ParsedMethodNames.txt";
        FileInputStream fis1 = new FileInputStream(methodNamePairsPath);
        Scanner scanner1 = new Scanner(fis1);
        while (scanner1.hasNext()) {
            String line = scanner1.nextLine();
            String [] splitArray = line.split("@");
            String buggyName = splitArray[0].toLowerCase();
            String fixedName = splitArray[1].toLowerCase();
            String [] arrayListBuggyName = buggyName.split(",");
            ArrayList<String> arrayList_buggyName = new ArrayList<>();
            ArrayList<String> arrayList_fixedName = new ArrayList<>();
            Collections.addAll(arrayList_buggyName,arrayListBuggyName);

            String [] arrayListFixedName = fixedName.split(",");
            Collections.addAll(arrayList_fixedName,arrayListFixedName);

            ArrayList<String> newList = new ArrayList<>();
            newList.clear();
            for(String s:arrayList_buggyName){
                if(arrayList_fixedName.contains(s)){
                    continue;
                }
                else{
                    newList.add(s);
                }
            }
            if(newList.size()==arrayList_buggyName.size()){
                System.out.println(buggyName+"@"+fixedName);
            }
            newList.clear();


        }
    }
}
