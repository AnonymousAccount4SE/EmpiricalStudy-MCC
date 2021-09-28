package edu.lu.uni.serval.renamed.methods;

import edu.lu.uni.serval.*;

import java.io.IOException;

public class ExtractInConMethods {
    public static void main(String []args) throws IOException {
        MainParser mainParser = new MainParser();
        /**
         * Mining Renamed Methods
         */
//        Main4Testing.main(args);

        /**
         * Get All Java Files From Testing Repos and Extract Methods From Latest Commit
         */
        String allJavaFilesFile = Configuration4InCon.JAVA_FILES_FILE;
//        JavaFileGetter4Testing.main(null);
        mainParser.parseMethodsWithMultipleThreads1(allJavaFilesFile,Configuration4InCon.TOKENIZED_METHODS_PATH);


        /**
         * Further Select Inconsistent Methods.
         * like filtering the token numbers more than 94
         * and the renamed methods whose body are changed.
         */
//        DataPreparer4InCon.main(args);
    }
}
