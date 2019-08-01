import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
