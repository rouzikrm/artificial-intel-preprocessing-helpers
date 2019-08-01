import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// just opposite of https://github.com/rouzikrm/artificial-intel-preprocessing-helpers/blob/master/ParentPathToFastTextConverter.java
public class FastTextToParentPathLabelingFormat {
    private static String FASTTEXT_PATTERN = "(__label__)\\w+\\s";

    public static void main(String[] args) throws IOException {
        HashMap<String, List<String>> mappedContent = new HashMap<String, List<String>>();
        Pattern fasttextPattern = Pattern.compile(FASTTEXT_PATTERN);
        File file = new File("/Users/rouzbeh/gitrepo/fasttext/fastText-0.9.1/digicomment.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = fasttextPattern.matcher(line);
            while (matcher.find()) {
                String matchedGroup = matcher.group();
                new File(matchedGroup.trim()).mkdir();
                if (mappedContent.get(matchedGroup) == null) {
                    mappedContent.put(matchedGroup, new ArrayList<String>());
                }
                mappedContent.get(matchedGroup).add(matcher.replaceAll(""));
            }
        }
        for (Map.Entry<String,List<String>> entry : mappedContent.entrySet()){
            FileWriter writer = new FileWriter("./"+entry.getKey().trim()+"/"+entry.getKey().trim()+".txt",true);
            for (String line : entry.getValue()){
                writer.write(line+"\n");
            }
            writer.flush();
            writer.close();
        }
    }
}
