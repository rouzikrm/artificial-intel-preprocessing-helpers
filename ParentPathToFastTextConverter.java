import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

        // took this from https://github.com/deeplearning4j/dl4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/dataexamples/ImagePipelineExample.java
        // DIRECTORY STRUCTURE:
        //Images in the dataset have to be organized in directories by class/label.
        //In this example there are ten images in three classes
        //Here is the directory structure
        //                                    parentDir
        //                                  /    |     \
        //                                 /     |      \
        //                            labelA  labelB   labelC
        //
        //Set your data up like this so that labels from each label/class live in their own directory
        //And these label/class directories live together in the parent directory

        // fasttext format: __LABEL__<CLASS> <one line of data>
        // fasttext format: __LABEL__<CLASS> <one line of data>

public class ParentPathToFastTextConverter {
      public static void main(String[] args) throws IOException {
        File parentDirectory = new File("<path to your train folder>/train");
        File file = new File("<destination-fasttext-formated-file>/fasttext-formatted.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file,true);
        for(File subFile : parentDirectory.listFiles()){
            if (!subFile.isDirectory())
                continue;
            System.err.println(subFile.getAbsolutePath());
            for (File dataFile: subFile.listFiles()){
                System.out.println(dataFile.getAbsolutePath());
                System.out.println("================Content======================");
                Scanner scanner = new Scanner(dataFile);
                String [] pathArray = dataFile.getAbsolutePath().split("/");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    writer.write("__label__"+pathArray[pathArray.length-2]+" "+line+"\n");
                    System.out.println(">>>>>>>>>>>>>>>>Content<<<<<<<<<<<<<<<<<<<<<<");
                }
            }
        }
        writer.flush();
        writer.close();
    }
}
