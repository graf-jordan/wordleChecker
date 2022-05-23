import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
public class Dictionary {
    public HashSet<String> wordList;

    public Dictionary() throws FileNotFoundException { //load a hash list with all words in the constructor
        wordList = new HashSet<String>();
        File txt = new File("../wordleChecker/src/src/dict.txt");
        Scanner txtFile = new Scanner(txt);
        while (txtFile.hasNext()) {
            wordList.add(txtFile.nextLine().trim()); //trim just in case there are any spaces - I didn't want to check :)
        }
        txtFile.close();

        String[] keptWords = new String[wordList.size()];
        int removeCounter = 0;
        Iterator<String> iter = wordList.iterator();
        while(iter.hasNext()) {
            String check = iter.next();
            if (check.length() == 5) { //remove all non 5 character words
                keptWords[removeCounter] = check; //use an array for ConcurrentModificationException
                removeCounter++;
            }
        }
        wordList.clear();
        for (int i = 0; i <= removeCounter; i++) { //load the kept words
            wordList.add(keptWords[i]);
        }
    }
    public void printValidWords() {
        int outputLine = 0;
        for (String k : wordList) {
            System.out.print(k + ", ");
            outputLine++;
            if (outputLine == 10) {
                System.out.println();
                outputLine = 0;
            }
        }
    }
}
