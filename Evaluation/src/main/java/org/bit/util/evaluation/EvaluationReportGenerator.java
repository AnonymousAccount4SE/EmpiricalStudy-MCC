package org.bit.util.evaluation;

import edu.lu.uni.serval.utils.FileHelper;
import org.bit.util.functions.ShortMethodsProcess;
import org.bit.util.functions.Utils;
import org.bit.util.javaparser.ParserMethodNameMain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EvaluationReportGenerator {
    public static void main (String [] args) throws FileNotFoundException {


//        generateNegativeSampleAnalysisReport(2303);
//        generateInconsistentFailAnalysisReport(2303);
    //        generateNegativeSampleNamePairAnalysisReport();
//          generatePositiveSampleAnalysisReport();
//        String filePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\InconsistentAnalysis_Top40.txt";
//        String filePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentAnalysis_Top40.txt";
//        String filePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentAnalysis_Top1.txt";
        String filePath = "E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\Top1_PredictResult.txt";
//        String filePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\consistentSuccessAnalysis_Top1.txt";
//        String filePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\Top1_PredictResult.txt";
//        String filePath = "E:\\Workspace\\Evaluation_new\\icse2020\\real\\inconsistent\\";

//        generateInconsistentSuccessAnalysisReport(filePath);

//        generateInconsistentFailAnalysisReport(filePath);

//        generateConsistentSuccessAnalysisReport(filePath);

        generateConsistentFailAnalysisReport(filePath);

//        generateConsistentSuccessGetSetAnalysisReport(filePath);

//        generateConsistentSuccessShortMethodsAnalysisReport(filePath);

//        generateConsistentSuccessNotGetSetMethodsAnalysisReport(filePath);

//        icse2020_generateConsistentSuccessGetSetMethodsAnalysisReport(filePath);
//        icse2020_generateConsistentFailGetSetMethodsAnalysisReport(filePath);

//        icse2020_generateAllGetSetResultsMethods(filePath);
    }

    private static void icse2020_generateAllGetSetResultsMethods(String filePath) throws FileNotFoundException {
        String allResult = filePath + "allResult.txt";
        ArrayList<String> allResultList = ParserMethodNameMain.getLines(allResult);
        StringBuilder sb = new StringBuilder();
        for(String s:allResultList){
            String firstToken = s.split(" ")[0];
            if(firstToken.equals("get")||firstToken.equals("set")){
                sb.append(s+"\n");
                System.out.println(s);
            }
        }
        FileHelper.outputToFile(filePath + "allGetSetResultsMethods.txt",sb,false);
    }

    private static void icse2020_generateConsistentFailGetSetMethodsAnalysisReport(String filePath) throws FileNotFoundException {
        String correctFile = filePath + "false.txt";
        ArrayList<String> correctList = ParserMethodNameMain.getLines(correctFile);
        StringBuilder sb = new StringBuilder();
        for(String s:correctList){
            String firstToken = s.split(":")[1].split(" ")[0];
            if(firstToken.equals("get")||firstToken.equals("set")){
                sb.append(s+"\n");
                System.out.println(s);
            }
        }
        FileHelper.outputToFile(filePath + "ConsistentFailGetSetMethodsAnalysisReport.txt",sb,false);
    }
    private static void icse2020_generateConsistentSuccessGetSetMethodsAnalysisReport(String filePath) throws FileNotFoundException {
        String correctFile = filePath + "correct.txt";
        ArrayList<String> correctList = ParserMethodNameMain.getLines(correctFile);
        StringBuilder sb = new StringBuilder();
        for(String s:correctList){
            String firstToken = s.split(":")[1].split(" ")[0];
            if(firstToken.equals("get")||firstToken.equals("set")){
                sb.append(s+"\n");
                System.out.println(s);
            }
        }
        FileHelper.outputToFile(filePath + "ConsistentSuccessGetSetMethodsAnalysisReport.txt",sb,false);
    }

    private static void generateConsistentSuccessNotGetSetMethodsAnalysisReport(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        StringBuilder notGetSetLines = new StringBuilder();
//        int cnt =0;
        for(String line : lines){
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            int firstCommaIndex = line.indexOf(",");
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");
            String firstToken = "";
            if (firstCommaIndex == -1) {
                firstToken = line.substring(leftSquareBracketsIndex + 1, rightSquareBracketsIndex);
            } else {
                firstToken = line.substring(leftSquareBracketsIndex + 1, firstCommaIndex);
            }
//            if(cnt>=2303){
                if(originalTag.equals(predictedTag)){
                    if(!firstToken.equalsIgnoreCase("get")&&!firstToken.equalsIgnoreCase("set"))
                        notGetSetLines.append(line+"\n");
                }
//            }
//            cnt++;
        }
        System.out.println(notGetSetLines);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top40.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top1.txt",correctLines,false);
        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\consistentSuccessNotGetSetAnalysis_Top1.txt",notGetSetLines,false);
    }

    public static void generateNegativeSampleNamePairAnalysisReport() throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
//        String resultsContent = basePath + "real_more_Top40.txt";
//        String resultsContent = basePath + "real_more_0110\\Top1_PredictResult.txt";
        String resultsContent = basePath + "real_more_0110\\inconsistentSuccessAnalysis_Top1.txt";
//        String resultsContent = basePath + "real_more_0110\\inconsistentFailAnalysis_Top1.txt";

        String parsedMethodNamesPath = basePath + "DL_Data\\RenamedMethods\\parsedMethodNames.txt";
        ArrayList<String> pairs = ParserMethodNameMain.getLines(parsedMethodNamesPath);
        StringBuilder outputContent = new StringBuilder();
        ArrayList<String> results = ParserMethodNameMain.getLines(resultsContent);
        for(String result:results){
            int index = Utils.getIndice(result);
            outputContent.append(pairs.get(index)+"\n");
        }
        FileHelper.outputToFile(basePath+"real_more_0110\\inconsistentSuccessMethodNamePairAnalysisReport_Top1.txt",outputContent,false);
//        FileHelper.outputToFile(basePath+"real_more_0110\\inconsistentFailMethodNamePairAnalysisReport_Top1.txt",outputContent,false);
//        FileHelper.outputToFile(basePath+"InconsistentAnalysis_Top40.txt",outputContent,false);
    }
    private static void generateConsistentFailAnalysisReport(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        StringBuilder correctLines = new StringBuilder();
        int cnt =0;
        for(String line : lines){
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            if(cnt>=3159){
                if(!originalTag.equals(predictedTag)){
                    correctLines.append(line+"\n");
                }
            }
            cnt++;
        }
        System.out.println(correctLines);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top40.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top1.txt",correctLines,false);
        FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\0228_real_more\\consistentFailAnalysis_Top1.txt",correctLines,false);
    }

    private static void generateConsistentSuccessShortMethodsAnalysisReport(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        StringBuilder correctLines = new StringBuilder();

        String basePath = "D:\\BIT\\BadMethodName\\debug-method-name-master\\Data\\TrainingData\\";
        String trainingBodyTokenPath = basePath + "TrainingMethodBodyTokens.txt";
        HashMap<Integer,Integer> trainingBodyTokenMap = new HashMap<>();
        ShortMethodsProcess.getIndexAndTokenNumbers(trainingBodyTokenPath,trainingBodyTokenMap);
        System.out.println(trainingBodyTokenMap.size());

        int cnt =0;
        for(String line : lines){
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            int firstCommaIndex = line.indexOf(",");
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");
            String firstToken = "";
            if (firstCommaIndex == -1) {
                firstToken = line.substring(leftSquareBracketsIndex + 1, rightSquareBracketsIndex);
            } else {
                firstToken = line.substring(leftSquareBracketsIndex + 1, firstCommaIndex);
            }
            if(cnt>=2303){
                if(originalTag.equals(predictedTag)){
                    if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set"))
                        correctLines.append(line+"\n");
                }
            }
            cnt++;
        }
        System.out.println(correctLines);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top40.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top1.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessGetSetAnalysis_Top1.txt",correctLines,false);
        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\consistentSuccessGetSetAnalysis_Top1.txt",correctLines,false);
    }

    private static void generateConsistentSuccessGetSetAnalysisReport(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        StringBuilder correctLines = new StringBuilder();
        int cnt =0;
        for(String line : lines){
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            int firstCommaIndex = line.indexOf(",");
            int leftSquareBracketsIndex = line.indexOf("[");
            int rightSquareBracketsIndex = line.indexOf("]");
            String firstToken = "";
            if (firstCommaIndex == -1) {
                firstToken = line.substring(leftSquareBracketsIndex + 1, rightSquareBracketsIndex);
            } else {
                firstToken = line.substring(leftSquareBracketsIndex + 1, firstCommaIndex);
            }
            if(cnt>=3379){
                if(originalTag.equals(predictedTag)){
                    if(firstToken.equalsIgnoreCase("get")||firstToken.equalsIgnoreCase("set"))
                    correctLines.append(line+"\n");
                }
            }
            cnt++;
        }
        System.out.println(correctLines);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top40.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top1.txt",correctLines,false);
        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\real_more_0110\\consistentSuccessGetSetAnalysis_Top1.txt",correctLines,false);
    }


    private static void generateConsistentSuccessAnalysisReport(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        StringBuilder correctLines = new StringBuilder();
        int cnt =0;
        for(String line : lines){
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            if(cnt>=3159){
                if(originalTag.equals(predictedTag)){
                    correctLines.append(line+"\n");
                }
            }
            cnt++;
        }
        System.out.println(correctLines);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top40.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top1.txt",correctLines,false);
        FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\consistentSuccessAnalysis_Top1.txt",correctLines,false);
    }

    private static void generateInconsistentFailAnalysisReport(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        StringBuilder failedLines = new StringBuilder();
        int cnt =0;
        for(String line : lines){
            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            if(cnt<3159){
                if(!originalTag.equals(predictedTag)){
                    failedLines.append(line+"\n");
                }
            }
            cnt++;
        }
        System.out.println(failedLines);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top40.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top1.txt",correctLines,false);
        FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\inconsistentFailAnalysis_Top1.txt",failedLines,false);
    }


    private static void generateInconsistentSuccessAnalysisReport(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = ParserMethodNameMain.getLines(filePath);
        StringBuilder failedLines = new StringBuilder();
        int cnt =0;
        for(String line : lines){

            int originalTagIndex = line.indexOf("originalTag:");
            int predictedTagIndex = line.indexOf("predictedTag:");
            String originalTag = line.substring(originalTagIndex+"originalTag:".length(),originalTagIndex+"originalTag:".length()+1);
            String predictedTag = line.substring(predictedTagIndex+"predictedTag:".length(),predictedTagIndex+"predictedTag:".length()+1);
            if(cnt<3159){
                if(originalTag.equals(predictedTag)){
                    failedLines.append(line+"\n");
                }
            }
            cnt++;
        }
        System.out.println(failedLines);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top40.txt",correctLines,false);
//        FileHelper.outputToFile("D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\consistentCorrectAnalysis_Top1.txt",correctLines,false);
        FileHelper.outputToFile("E:\\Workspace\\Evaluation_new\\icse2019\\0228_real_more\\inconsistentSuccessAnalysis_Top1.txt",failedLines,false);
    }

    private static void generatePositiveSampleAnalysisReport() throws FileNotFoundException {

//        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more\\";
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\";
//        String resultsContent = basePath + "real_more_Top40.txt";
//        String resultsContent = basePath + "real_more_Top1.txt";
        String resultsContent = basePath + "real_more_unique\\consistentSuccessNotGetSetAnalysis_Top1.txt";
        ArrayList<String> methodInfoList = new ArrayList<>();

//        String selectedMethodInfoPath = basePath + "DL_Data\\SelectedData\\SelectedMethodInfo.txt";
        String selectedMethodInfoPath = basePath + "real_more\\DL_Data\\SelectedData\\SelectedMethodInfo_1.txt";
        FileInputStream fis4 = new FileInputStream(selectedMethodInfoPath);
        Scanner scanner4 = new Scanner(fis4);
        ArrayList<String> selectedMethodInfoList = new ArrayList<>();
        while(scanner4.hasNext()){
            String line4 = scanner4.nextLine();
            selectedMethodInfoList.add(line4.substring(line4.indexOf("@")+1));
        }


        FileInputStream fis = new FileInputStream(resultsContent);
        Scanner scanner = new Scanner(fis);
        StringBuilder outputContent = new StringBuilder();
//        int cnt = -1;
        while (scanner.hasNextLine()) {
//            cnt ++;
            String line = scanner.nextLine();
            int index = Utils.getIndice(line);
//            if(cnt >= 2303){
            if(index >= 2303){
                outputContent.append(line).append("\n");


//                String [] splitArray = selectedMethodInfoList.get(cnt-2303).split(":");
                String [] splitArray = selectedMethodInfoList.get(index-2303).split(":");
                String projectName = splitArray[0];
                String methodBodiesPath = basePath + "real_more\\DL_Data\\SelectedData\\" + projectName +"\\method_bodies.txt";
                String methodName = splitArray[3];
                StringBuilder methodBodies = new StringBuilder();
                FileInputStream fis3 = new FileInputStream(methodBodiesPath);
                Scanner scanner3 = new Scanner(fis3);
                String methodNameLine = "";
                while(scanner3.hasNext()){
                    String line1 = scanner3.nextLine();
//                    if(line1.contains(":")&&line1.contains(".")){
//                        methodNameLine = line1;
//                        break;
//                    }
                    if(line1.contains(" "+methodName+"(")&&!line1.contains(";")){
                        methodNameLine = line1;
                        break;
                    }
                }
                System.out.println("methodNameLine:"+methodNameLine);
                outputContent.append("\n"+methodNameLine+"\n");
                while(scanner3.hasNext()){
                    String line2 = scanner3.nextLine();
                    if(!line2.contains("METHOD_BODY")){
                        outputContent.append(line2+"\n");
                    }
                    else break;
                }
            }
        }
//        System.out.println(outputContent);
//        FileHelper.outputToFile(basePath+"consistentAnalysis_Top40.txt",outputContent,false);
        FileHelper.outputToFile(basePath+"real_more_unique\\consistentSuccessNotGetSetAnalysisReport_Top1.txt",outputContent,false);
    }

    public static void generateNegativeSampleAnalysisReport(int renamedMethodsNumber) throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\";
//        String resultsContent = basePath + "real_more_Top40.txt";
        String resultsContent = basePath + "Top1_PredictResult.txt";

        String parsedMethodNamesPath = basePath + "DL_Data\\RenamedMethods\\parsedMethodNames.txt";
        FileInputStream fis1 = new FileInputStream(parsedMethodNamesPath);
        Scanner scanner1 = new Scanner(fis1);
        ArrayList<String> parsedMethodNameList = new ArrayList<>();
        while(scanner1.hasNext()){
            parsedMethodNameList.add(scanner1.nextLine());
        }

        String methodInfoPath = basePath + "DL_Data\\RenamedMethods\\MethodInfo.txt";
        FileInputStream fis2 = new FileInputStream(methodInfoPath);
        Scanner scanner2 = new Scanner(fis2);
        ArrayList<String> methodInfoList = new ArrayList<>();
        while(scanner2.hasNext()){
            String line = scanner2.nextLine();
            methodInfoList.add(line);

        }
        System.out.println("methodInfoList:"+methodInfoList.size());

        String methodTokensPath = basePath + "DL_Data\\RenamedMethods\\MethodTokens.txt";
        FileInputStream fis4 = new FileInputStream(methodTokensPath);
        Scanner scanner4 = new Scanner(fis4);
        ArrayList<String> methodTokenList = new ArrayList<>();
        while(scanner4.hasNext()){
            String line4 = scanner4.nextLine();
            methodTokenList.add(line4);
        }
        System.out.println("methodTokenList:"+methodTokenList.size());

        FileInputStream fis = new FileInputStream(resultsContent);
        Scanner scanner = new Scanner(fis);
        StringBuilder outputContent = new StringBuilder();
        int cnt = -1;

        while (scanner.hasNextLine()) {
            cnt ++;
            String line = scanner.nextLine();
            int indice = Utils.getIndice(line);
            if(indice < renamedMethodsNumber){
                outputContent.append(line).append("\n");
                outputContent.append("\n"+parsedMethodNameList.get(indice)+"\n");
                outputContent.append("\n"+methodInfoList.get(indice)+"\n");
                outputContent.append("\n"+methodTokenList.get(indice)+"\n");

                String [] splitArray = methodInfoList.get(indice).split(":");
                String projectName = splitArray[0];
                String methodBodiesPath = basePath + "RenamedMethods\\" + projectName +"\\ActualRenamed\\MethodBodies.txt";
                String methodName = splitArray[splitArray.length-3];
                StringBuilder methodBodies = new StringBuilder();
                FileInputStream fis3 = new FileInputStream(methodBodiesPath);
                Scanner scanner3 = new Scanner(fis3);
                String methodNameLine = "";
                int LineNumber =0;
                while(scanner3.hasNext()){
                    String line1 = scanner3.nextLine();
                    if(line1.contains(methodName)){
                        methodNameLine = line1;
                        break;
                    }
                    LineNumber ++;


                }
                outputContent.append("\n"+methodNameLine+"\n");
                while(scanner3.hasNext()){
                    String line2 = scanner3.nextLine();
                    if(!line2.contains("METHOD_BODY")){
                        outputContent.append(line2+"\n");

                    }
                    else break;
                }



            }

        }
        FileHelper.outputToFile(basePath+"InconsistentAnalysisReport_Top1.txt",outputContent,false);
//        FileHelper.outputToFile(basePath+"InconsistentAnalysis_Top40.txt",outputContent,false);
    }

    public static void generateInconsistentFailAnalysisReport(int renamedMethodsNumber) throws FileNotFoundException {
        String basePath = "D:\\BIT\\BadMethodName\\evaluation\\icse2019\\real_more_unique\\";
//        String resultsContent = basePath + "real_more_Top40.txt";
        String resultsContent = basePath + "inconsistentFailAnalysis_Top1.txt";

        String parsedMethodNamesPath = basePath + "DL_Data\\RenamedMethods\\parsedMethodNames_1.txt";
        FileInputStream fis1 = new FileInputStream(parsedMethodNamesPath);
        Scanner scanner1 = new Scanner(fis1);
        ArrayList<String> parsedMethodNameList = new ArrayList<>();
        while(scanner1.hasNext()){
            parsedMethodNameList.add(scanner1.nextLine());
        }

        String methodInfoPath = basePath + "DL_Data\\RenamedMethods\\MethodInfo_1.txt";
        FileInputStream fis2 = new FileInputStream(methodInfoPath);
        Scanner scanner2 = new Scanner(fis2);
        ArrayList<String> methodInfoList = new ArrayList<>();
        while(scanner2.hasNext()){
            String line = scanner2.nextLine();
            methodInfoList.add(line);

        }
        System.out.println("methodInfoList:"+methodInfoList.size());

        String methodTokensPath = basePath + "DL_Data\\RenamedMethods\\MethodTokens_1.txt";
        FileInputStream fis4 = new FileInputStream(methodTokensPath);
        Scanner scanner4 = new Scanner(fis4);
        ArrayList<String> methodTokenList = new ArrayList<>();
        while(scanner4.hasNext()){
            String line4 = scanner4.nextLine();
            methodTokenList.add(line4);
        }
        System.out.println("methodTokenList:"+methodTokenList.size());

        FileInputStream fis = new FileInputStream(resultsContent);
        Scanner scanner = new Scanner(fis);
        StringBuilder outputContent = new StringBuilder();
        int cnt = -1;
        int sameFirstTokenNumber = 0;
        while (scanner.hasNextLine()) {
            cnt ++;
            String line = scanner.nextLine();
            int indice = Utils.getIndice(line);
            if(indice < renamedMethodsNumber){
                outputContent.append(line).append("\n");
                outputContent.append("\n"+parsedMethodNameList.get(indice)+"\n");
                outputContent.append("\n"+methodInfoList.get(indice)+"\n");
                outputContent.append("\n"+methodTokenList.get(indice)+"\n");

                String pair = parsedMethodNameList.get(indice);
                String buggyNameFirstToken = pair.split("@")[0].split(",")[0];
                String fixedNameFirstToken = pair.split("@")[1].split(",")[0];
                System.out.println(buggyNameFirstToken);
                System.out.println(fixedNameFirstToken);
                if(buggyNameFirstToken.equalsIgnoreCase(fixedNameFirstToken)){
                    sameFirstTokenNumber++;
                }

                String [] splitArray = methodInfoList.get(indice).split(":");
                String projectName = splitArray[0];
                String methodBodiesPath = basePath + "RenamedMethods\\" + projectName +"\\ActualRenamed\\MethodBodies.txt";
                String methodName = splitArray[splitArray.length-3];
                StringBuilder methodBodies = new StringBuilder();
                FileInputStream fis3 = new FileInputStream(methodBodiesPath);
                Scanner scanner3 = new Scanner(fis3);
                String methodNameLine = "";
                int LineNumber =0;
                while(scanner3.hasNext()){
                    String line1 = scanner3.nextLine();
                    if(line1.contains(methodName)){
                        methodNameLine = line1;
                        break;
                    }
                    LineNumber ++;


                }
                outputContent.append("\n"+methodNameLine+"\n");
                while(scanner3.hasNext()){
                    String line2 = scanner3.nextLine();
                    if(!line2.contains("METHOD_BODY")){
                        outputContent.append(line2+"\n");

                    }
                    else break;
                }



            }

        }
        System.out.println(sameFirstTokenNumber);
//        FileHelper.outputToFile(basePath+"InconsistentFailAnalysisReport_Top1.txt",outputContent,false);
//        FileHelper.outputToFile(basePath+"InconsistentAnalysis_Top40.txt",outputContent,false);
    }
}
