package org.bit.util.functions;

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.evaluation.EvaluateApproach;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class RemoveDuplicates {



    public static void main(String [] args) throws FileNotFoundException {
//        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\JavaRepos\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\DL_Data\\RenamedMethods\\";
//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
//        removeDuplicatesFromTwoFile(basePath);
//        removeDuplicates_NameBody(basePath);
//        removeDuplicates_ClassNameBody(basePath);
//        CalDuplicatesOfCorrect(basePath);
    }

    private static void CalDuplicatesOfCorrect(String basePath) throws FileNotFoundException {

        ArrayList<Integer> inconsistentSuccessArrayList = new ArrayList<>();
        ArrayList<Integer> consistentSuccessArrayList = new ArrayList<>();
        EvaluateApproach.getPredictSuccess(basePath,inconsistentSuccessArrayList,consistentSuccessArrayList);
        System.out.println(inconsistentSuccessArrayList.size());
        System.out.println(consistentSuccessArrayList.size());
        System.out.println(inconsistentSuccessArrayList);


        String methodInfoFile = basePath + "DL_Data\\RenamedMethods\\MethodInfo.txt";
        String methodTokenFile = basePath + "DL_Data\\RenamedMethods\\MethodTokens.txt";
        ArrayList<String> infos = getLines(methodInfoFile);
        ArrayList<String> tokens = getLines(methodTokenFile);
        System.out.println(infos.size());
        System.out.println(tokens.size());
        HashSet<String> hashSet = new HashSet<>();
        for(int i=0;i<inconsistentSuccessArrayList.size();i++){
            String info = infos.get(inconsistentSuccessArrayList.get(i));
            String token = tokens.get(inconsistentSuccessArrayList.get(i));
            String className = info.split(":")[2];
            String name = info.split(":")[3];
            String newLine = className +":" + name+":"+token;
//            System.out.println(newLine);
            hashSet.add(newLine);
        }
        System.out.println(hashSet.size());


        String methodInfoFile1 = basePath + "DL_Data\\SelectedData\\SelectedMethodInfo.txt";
        String methodTokenFile1 = basePath + "DL_Data\\SelectedData\\SelectedMethodTokens.txt";
        ArrayList<String> infos1 = getLines(methodInfoFile1);
        ArrayList<String> tokens1 = getLines(methodTokenFile1);
        System.out.println(infos1.size());
        System.out.println(tokens1.size());
        HashSet<String> hashSet1 = new HashSet<>();
        for(int i=0;i<consistentSuccessArrayList.size();i++){
            String info = infos1.get(consistentSuccessArrayList.get(i)-3379);
            String token = tokens1.get(consistentSuccessArrayList.get(i)-3379);
            String className = info.split(":")[2];
            String name = info.split(":")[3];
            String newLine = className +":" + name+":"+token;
//            System.out.println(newLine);
            hashSet1.add(newLine);
        }
        System.out.println(hashSet1.size());

    }

    private static void removeDuplicates_ClassNameBody(String basePath) throws FileNotFoundException {
        String methodInfoFile = basePath + "TestMethodInfo.txt";
        String methodTokenFile = basePath + "TestMethodBodyTokens.txt";
        ArrayList<String> infos = getLines(methodInfoFile);
        ArrayList<String> tokens = getLines(methodTokenFile);
        System.out.println(infos.size());
        System.out.println(tokens.size());
        HashSet<String> hashSet = new HashSet<>();
        for(int i=1;i<infos.size();i++){
            String info = infos.get(i);
            String token = tokens.get(i-1);
            String className = info.split(":")[2];
            String name = info.split(":")[3];
            String newLine = className +":" + name+":"+token;
            System.out.println(newLine);
            hashSet.add(newLine);
        }
        System.out.println(hashSet.size());
    }

    private static void removeDuplicates_NameBody(String basePath) throws FileNotFoundException {
        String methodInfoFile = basePath + "DL_Data\\RenamedMethods\\MethodInfo.txt";
        String methodTokenFile = basePath + "DL_Data\\RenamedMethods\\MethodTokens.txt";
        String parsedMethodNamesFile = basePath + "DL_Data\\RenamedMethods\\ParsedMethodNames.txt";
//        String methodInfoFile = basePath + "DL_Data\\SelectedData\\SelectedMethodInfo.txt";
//        String methodTokenFile = basePath + "DL_Data\\SelectedData\\SelectedMethodTokens.txt";
        ArrayList<String> infos = getLines(methodInfoFile);
        ArrayList<String> tokens = getLines(methodTokenFile);
        System.out.println(infos.size());
        System.out.println(tokens.size());
        HashSet<String> hashSet = new HashSet<>();
        ArrayList<HashMap<String,String>> filteredList = new ArrayList<>();
        ArrayList<Integer> deleteList = new ArrayList<>();
        for(int i=0;i<infos.size();i++){
            String info = infos.get(i);
            String token = tokens.get(i);
            String name = info.split(":")[3];

            String newLine = name+":"+token;
//            System.out.println(newLine);
//            System.out.println(newLine);
            if(hashSet.contains(newLine)){
                System.out.println("already exist:"+newLine);
                deleteList.add(i);
            }
            else{
                hashSet.add(newLine);
                HashMap<String,String> map = new HashMap<>();
                map.put(info,token);
                filteredList.add(map);
            }


        }
        System.out.println(deleteList.size());
        System.out.println(hashSet.size());
        StringBuilder infosb = new StringBuilder();
        StringBuilder tokensb = new StringBuilder();
        StringBuilder parsedMethodNamesb = new StringBuilder();
        ArrayList<String> parsedMethodNames = ParserMethodNameMain.getLines(parsedMethodNamesFile);

        for(HashMap<String,String> map:filteredList){
            String info = map.keySet().iterator().next();
            String token = map.values().iterator().next();
            infosb.append(info+"\n");
            tokensb.append(token+"\n");
        }
        for(int i=0;i<parsedMethodNames.size();i++){
            if(deleteList.contains(i)){
                System.out.println(0);
            }
            else{
                parsedMethodNamesb.append(parsedMethodNames.get(i)+"\n");
            }

        }
//        FileHelper.outputToFile(basePath + "DL_Data\\SelectedData\\SelectedMethodInfo_1.txt",infosb,false);
//        FileHelper.outputToFile(basePath + "DL_Data\\SelectedData\\SelectedMethodTokens_1.txt",tokensb,false);

//        FileHelper.outputToFile(basePath + "DL_Data\\RenamedMethods\\MethodInfo_1.txt",infosb,false);
//        FileHelper.outputToFile(basePath + "DL_Data\\RenamedMethods\\MethodTokens_1.txt",tokensb,false);
        FileHelper.outputToFile(basePath + "DL_Data\\RenamedMethods\\ParsedMethodNames_1.txt",parsedMethodNamesb,false);
    }
    public static ArrayList<String> getLines(String filePath) throws FileNotFoundException {
        FileInputStream fileInputStream1 = new FileInputStream(filePath);
        Scanner scanner1 = new Scanner(fileInputStream1);
        ArrayList<String> list = new ArrayList<>();
        while(scanner1.hasNext()){
            String line = scanner1.nextLine();
            list.add(line);
        }
        return list;
    }

    public static void removeDuplicatesFromTwoFile(String basePath) throws FileNotFoundException {
        String apache = basePath + "apache.txt";
        String allApacheRepositories = basePath + "allApacheRepositories.txt";
        FileInputStream fileInputStream1 = new FileInputStream(apache);
        Scanner scanner1 = new Scanner(fileInputStream1);
        FileInputStream fileInputStream2 = new FileInputStream(allApacheRepositories);
        Scanner scanner2 = new Scanner(fileInputStream2);
        ArrayList<String> apacheList = new ArrayList<>();
        while(scanner1.hasNext()){
            String line = scanner1.nextLine();
            apacheList.add(line);
        }
        int index =0;
//        System.out.println(apacheList);
        while(scanner2.hasNext()){
            String line = scanner2.nextLine();
            if(!apacheList.contains(line)){
                System.out.println(line);
                index++;
            }
        }
        System.out.println(index);
    }
    // remove after parsedMethodName/ContextTokens
    public static void removeDuplicates(String basePath) throws FileNotFoundException {
        String NameTokens = basePath + "parsedMethodNameTokens.txt";
        String ContextTokens = basePath + "parsedMethodContextTokens.txt";
        ArrayList<String> nameTokensList = ParserMethodNameMain.getLines(NameTokens);
        ArrayList<String> contextTokensList = ParserMethodNameMain.getLines(ContextTokens);
        HashSet<String> set = new HashSet<>();
        int setSize = set.size();
        for(int i=0;i<nameTokensList.size();i++){
            System.out.print(i+":");
            String nameToken = nameTokensList.get(i);
            String contextToken = contextTokensList.get(i);
            String token = nameToken+":"+contextToken;
            set.add(token);
            if(setSize<set.size()){
//                System.out.println("    keep:"+token);
                System.out.println(token);
            }
//            else{
//                System.out.println("    duplicate:"+token);
//            }
            setSize = set.size();

        }
        System.out.println(nameTokensList.size());
        System.out.println(set.size());
        StringBuilder nameTokensb = new StringBuilder();
        StringBuilder contetxtTokensb = new StringBuilder();
        for(String token:set){
            String nameToken = token.split(":")[0];
            String contextToken = token.split(":")[1];
            nameTokensb.append(nameToken+"\n");
            contetxtTokensb.append(contextToken+"\n");
        }

//        FileHelper.outputToFile(basePath + "parsedMethodNameTokens_unique_withLineNumber.txt",nameTokensb,false);
//        FileHelper.outputToFile(basePath + "parsedMethodContextTokens_unique_withLineNumber.txt",contetxtTokensb,false);
    }
}
