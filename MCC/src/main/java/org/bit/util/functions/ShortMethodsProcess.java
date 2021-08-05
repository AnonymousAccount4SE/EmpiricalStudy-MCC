/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.bit.util.functions;/*
 *   @author Michael
 *   @create 2021/1/3
 */
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
public class ShortMethodsProcess {

    public static void main(String [] args) throws FileNotFoundException {
        processShortMethods2019();
        String path ="D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\TrainingMethodBodyTokens.txt";
        HashMap<Integer,Integer> map = new HashMap<>();
//        calAverageOfTrainingData(path,map);
//        calTokenLT10TrainingData(path,map);
        // calculate token numbers of method name
//        processShortMethods2020("inconsistent");
//        processShortMethods2020("consistent");


    }

    private static void calTokenLT10TrainingData(String path, HashMap<Integer, Integer> map) throws FileNotFoundException {
        getIndexAndTokenNumbers(path,map);
        Collection<Integer> set = map.values();
        double sum =0;
        for(int i:set){
            if(i<10){
                sum++;
            }
        }
        System.out.println(sum);
    }

    private static void processShortMethods2020(String type) throws FileNotFoundException {
        String nameTokensPath = "D:\\BIT\\BadMethodName\\evaluation\\icse2020\\"+type+"\\parsedMethodNameTokens_unique.txt";
        ArrayList<String> nameTokens = ParserMethodNameMain.getLines(nameTokensPath);
        ArrayList<Integer> indexAndTokenNumber = new ArrayList<>();

        for(String nameToken :nameTokens){
            String [] splitArray = nameToken.split(" ");
            indexAndTokenNumber.add(splitArray.length);
        }
//        System.out.println(indexAndTokenNumber);
        for(int num : indexAndTokenNumber){
            if(num > 8){
                System.out.println(num);
            }
        }
        System.out.println(indexAndTokenNumber.size());
    }

    private static void calAverageOfTrainingData(String path, HashMap<Integer, Integer> map) throws FileNotFoundException {
        getIndexAndTokenNumbers(path,map);
        Collection<Integer> set = map.values();
        double sum =0;
        for(int i:set){
            sum+=i;
        }
        System.out.println(sum/2116413);
    }

    public static void processShortMethods2019() throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\";
        String inconsisPath = basePath + "real_more\\DL_Data\\RenamedMethods\\MethodTokens_1.txt";
        HashMap<Integer,Integer> inconsistentMap = new HashMap<>();
        getIndexAndTokenNumbers(inconsisPath,inconsistentMap);
//        System.out.println(inconsistentMap.size());

        String consisPath = basePath + "real_more\\DL_Data\\SelectedData\\SelectedMethodTokens_1.txt";
        HashMap<Integer,Integer> consistentMap = new HashMap<>();
        getIndexAndTokenNumbers(consisPath,consistentMap);
//        System.out.println(consistentMap.size());
//        System.out.println(inconsistentMap);

//        String Top40Result = basePath + "real_more_Top40.txt";
//        String Top1Result = basePath + "real_more_0110\\Top1_PredictResult.txt";
//        String Top1Result = basePath + "real_more_unique\\Top1_PredictResult.txt";
        String Top1Result = basePath + "real_more_unique\\consistentSuccessNotGetSetAnalysis_Top1.txt";
//        ArrayList<String> results = getLines(Top40Result);
        ArrayList<String> results = getLines(Top1Result);

        ArrayList<String> shortInconsistentCorrectMethods = new ArrayList<>();
        ArrayList<String> shortConsistentCorrectMethods = new ArrayList<>();
        ArrayList<String> shortInconsistentMethods = new ArrayList<>();
        ArrayList<String> shortConsistentMethods = new ArrayList<>();

        for(int i=0;i<results.size();i++){
            String result = results.get(i);
//            System.out.println(result);
            int indice = Utils.getIndice(result);
            int originalTagIndex = result.indexOf("originalTag:");
            int predictedTagIndex = result.indexOf("predictedTag:");
            String originalTag = result.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = result.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            if(indice<2303){

                if(inconsistentMap.get(indice)<15){
//                        System.out.println(i+":"+inconsistentMap.get(i));
                    shortInconsistentMethods.add(result);

                    if(originalTag.equals(predictedTag)){
                        shortInconsistentCorrectMethods.add(result);
                    }
                }
            }
            else{

                if(consistentMap.get(indice-2303)<30){
//                        System.out.println(i+":"+consistentMap.get(i));
                    shortConsistentMethods.add(result);

                    if(originalTag.equals(predictedTag)){
                        shortConsistentCorrectMethods.add(result);
                    }
                }
            }
        }
        System.out.println("short Methods ===Consistent:"+shortConsistentMethods.size());
        System.out.println("short Methods ===Inconsistent:"+shortInconsistentMethods.size());
        System.out.println("short Methods ===Consistent Correct:"+shortConsistentCorrectMethods.size());
        System.out.println("short Methods ===Inconsistent Correct:"+shortInconsistentCorrectMethods.size());
//        System.out.println(shortConsistentCorrectMethods);
//        for(String s:shortConsistentCorrectMethods)
//        System.out.println(s);
    }
    public static ArrayList<String> getLines(String basePath) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(basePath);
        Scanner scanner1 = new Scanner(fileInputStream);
        while(scanner1.hasNext()){
            lines.add(scanner1.nextLine());
        }
        return lines;
    }
    public static void getIndexAndTokenNumbers(String filePath,HashMap<Integer,Integer> map) throws FileNotFoundException {
        ArrayList<String> lines = getLines(filePath);
//        System.out.println(lines.size());
        int index = 0;
        for(String line:lines){
            String [] splitArray = line.split(" ");
            map.put(index,splitArray.length);
            index++;
        }
    }
}
