package org.bit.util.evaluation;

import java.text.DecimalFormat;

public class IREvaluation {
    public static void main(String [] args){
        //original
        //41	28	    17	    10	    4	    2
        //449	2916	4700	6327	7082	7421
        //5	    18	    29	    36	    42	    44
        //7661	5194	3410	1783	1028	689

        //newTestData
        //127	79	    44	    21	    8	    6
        //743	3518	5391	7048	7477	7675
        //18	66	    101	    124	    137	    139
        //7367	4592	2719	1062	633	    435

//        TP:2351
//        FN:808
//        TN:54811
//        FP:120133

//        TP:2574
//        FN:585
//        TN:46996
//        FP:127948

//        TP:2623
//        FN:536
//        TN:45000
//        FP:129944

//        TP:2634
//        FN:525
//        TN:44268
//        FP:130676

//        TP:2643
//        FN:516
//        TN:44076
//        FP:130868

//        TP:2645
//        FN:514
//        TN:44028
//        FP:130916

//        TP:2552
//        FN:607
//        174944
//        TN:55002
//        FP:119942
        IREvaluation evaluation = new IREvaluation(1247,
                112933,
                1912,
                62011

        );
        evaluation.evaluate1();
        evaluation.evaluate2();

    }
    public static void evaluate(int TP, int TN, int FN, int FP){
        IREvaluation evaluation = new IREvaluation(TP,	TN,	FN,	FP );
        evaluation.evaluate1();
        evaluation.evaluate2();
    }

    public static String evaluate_fscore(int TP, int TN, int FN, int FP){
        IREvaluation evaluation = new IREvaluation(TP,	TN,	FN,	FP );
        double incon_f = evaluation.evaluate_FScore_1();
        double con_f = evaluation.evaluate_FScore_2();
        return incon_f+","+con_f;
    }

    public static double evaluate_acc(int TP, int TN, int FN, int FP){
        IREvaluation evaluation = new IREvaluation(TP,	TN,	FN,	FP );
        double acc = evaluation.evaluate_FScore_3();
        return acc;
    }
    public static String evaluate_con(int TP, int TN, int FN, int FP){
        IREvaluation evaluation = new IREvaluation(TP,	TN,	FN,	FP );
        String acc = evaluation.evaluate_consistent();
        return acc;
    }
    public static String evaluate_incon(int TP, int TN, int FN, int FP){
        IREvaluation evaluation = new IREvaluation(TP,	TN,	FN,	FP );
        String acc = evaluation.evaluate_inconsistent();
        return acc;
    }

    private static final DecimalFormat FORMAT = new DecimalFormat("0.000");

    private int truePositives;
    private int trueNegatives;
    private int falsePositives;
    private int falseNegatives;

    private double accuracy;
    private double precision;
    private double recall;
    private double f1_measure;

    public IREvaluation(int truePositives, int trueNegatives, int falseNegatives, int falsePositives) {
        super();
        this.truePositives = truePositives;
        this.trueNegatives = trueNegatives;
        this.falsePositives = falsePositives;
        this.falseNegatives = falseNegatives;
    }

    public void evaluate1() {
        String target = "inconsistent method name checking";
        accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
        precision = (double) this.truePositives / (this.truePositives + this.falsePositives);
        recall = (double) this.truePositives / (this.truePositives + this.falseNegatives);
        f1_measure = (double) 2 * precision*recall / (precision + recall);
        outputResults1(target);
    }
    public double evaluate_FScore_1() {
//        String target = "inconsistent method name checking";
        accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
        precision = (double) this.truePositives / (this.truePositives + this.falsePositives);
        recall = (double) this.truePositives / (this.truePositives + this.falseNegatives);
        f1_measure = (double) 2 * precision*recall / (precision + recall);
//        outputResults1(target);
        return f1_measure;
    }
    public void evaluate2() {
        String target = "consistent method name checking";
        accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
        precision = (double) this.trueNegatives / (this.trueNegatives + this.falseNegatives);
        recall = (double) this.trueNegatives / (this.trueNegatives + this.falsePositives);
        f1_measure = (double) 2 * precision*recall / (precision + recall);
        outputResults1(target);
    }
    public double evaluate_FScore_2() {
//        String target = "inconsistent method name checking";
        accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
        precision = (double) this.trueNegatives / (this.trueNegatives + this.falseNegatives);
        recall = (double) this.trueNegatives / (this.trueNegatives + this.falsePositives);
        f1_measure = (double) 2 * precision*recall / (precision + recall);
//        outputResults1(target);
        return f1_measure;
    }

    public double evaluate_FScore_3() {
//        String target = "consistent method name checking";
        accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
        precision = (double) this.trueNegatives / (this.trueNegatives + this.falseNegatives);
        recall = (double) this.trueNegatives / (this.trueNegatives + this.falsePositives);
        f1_measure = (double) 2 * precision*recall / (precision + recall);
//        outputResults1(target);
        return accuracy;
    }
    public String evaluate_consistent() {
//        String target = "consistent method name checking";
        accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
        precision = (double) this.trueNegatives / (this.trueNegatives + this.falseNegatives);
        recall = (double) this.trueNegatives / (this.trueNegatives + this.falsePositives);
        f1_measure = (double) 2 * precision*recall / (precision + recall);
//        outputResults1(target);
        return precision+","+recall;
    }
    public String evaluate_inconsistent() {
//        String target = "inconsistent method name checking";
        accuracy = (double) (this.truePositives + this.trueNegatives) / (this.truePositives + this.trueNegatives + this.falsePositives + this.falseNegatives);
        precision = (double) this.truePositives / (this.truePositives + this.falsePositives);
        recall = (double) this.truePositives / (this.truePositives + this.falseNegatives);
        f1_measure = (double) 2 * precision*recall / (precision + recall);
//        outputResults1(target);
        return precision+","+recall;
    }

    private void outputResults(String target) {
        System.out.println("=============="+target+"==============");

//        System.out.println("Precision:  " + FORMAT.format(precision * 100) + "%");
//        System.out.println("Recall:     " + FORMAT.format(recall * 100) + "%");
        System.out.println("F1-measure: " + FORMAT.format(f1_measure * 100) + "%");
        System.out.println("Accuracy:   " + FORMAT.format(accuracy * 100) + "%");
        System.out.println("==========================================");
    }

    private void outputResults1(String target) {
        System.out.println("=============="+target+"==============");

        System.out.println(FORMAT.format(precision * 100));
        System.out.println(FORMAT.format(recall * 100) );
        System.out.println(FORMAT.format(f1_measure * 100) );
        System.out.println(FORMAT.format(accuracy * 100) );
        System.out.println("==========================================");
    }

}
