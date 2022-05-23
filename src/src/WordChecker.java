import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
public class WordChecker {

    public WordChecker() throws FileNotFoundException {
        HashSet<String> wordList = new HashSet<>();
        File txt = new File("/home/jordan/IdeaProjects/wordleChecker/src/src/dict.txt");
        Scanner txtFile = new Scanner(txt);
        while (txtFile.hasNext()) {
            wordList.add(txtFile.nextLine().trim());
        }
        txtFile.close();

        for (String str: wordList) {
            System.out.println(str + " ");
        }
    }
    public void check(String word) {

    }
}
