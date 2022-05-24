import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
public class Dictionary {
    public HashSet<String> wordList;
    public String[] keptWords;
    public HashSet<String> tempRemovers;

    //this constructs creates a new hash set including all the words in the English language
    //then, it iterates through the hash set, loading all five-letter words into an array
    //finally, it clears the word list, and fills it with the five-letter words inn the array
    public Dictionary() throws FileNotFoundException {
        wordList = new HashSet<String>(); //load a hash list with all words in the constructor
        File txt = new File("../wordleChecker/src/src/dict.txt");
        Scanner txtFile = new Scanner(txt);
        while (txtFile.hasNext()) {
            wordList.add(txtFile.nextLine().trim()); //trim just in case there are any spaces - I didn't want to check :)
        }
        txtFile.close();

        keptWords = new String[wordList.size()]; //create an iterator and load
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
    public void printValidWords() { //print all the words in the current list with nice formatting
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
    public void parseWord(String word, int[] arr) { //cycle through words by character, removing or keeping those that are incorrect
        tempRemovers = new HashSet<>();
            for (int k = 0; k < arr.length; k++) { //cycle through particular words
                if(arr[k] == 1) { //if the letter is grey
                    for (int j = 0; j <keptWords.length; j++) {
                        char tempLetter = word.charAt(k);

                        String parseable = String.valueOf(tempLetter);
                        String removeThis = keptWords[j];
                        boolean checkAlreadyIn = false;
                        for (int i = 0; i < 5; i++) {
                            char alreadyGreen = word.charAt(i);
                            if (tempLetter == alreadyGreen && arr[i] == 3) {
                                checkAlreadyIn = true;
                                break;
                            }
                        }
                        if (removeThis != null && !checkAlreadyIn) {
                            if (removeThis.contains(parseable) && removeThis != null) {
                                tempRemovers.add(removeThis);
                            }
                        }
                    }
                    wordList.removeAll(tempRemovers);
                }
                if(arr[k] == 2) { //is word is yellow
                    for (int j = 0; j <keptWords.length; j++) {
                        char tempLetter = word.charAt(k);
                        String parseable = String.valueOf(tempLetter);
                        String removeThis = keptWords[j];
                        if (removeThis != null) {
                            if (!removeThis.contains(parseable) && removeThis != null) {
                                tempRemovers.add(removeThis);
                            }
                        }
                    }
                    wordList.removeAll(tempRemovers);
                }
                if(arr[k] == 3) { //if green
                    for (int j = 0; j < keptWords.length; j++) {
                        char tempLetter = word.charAt(k);
                        String parseable = String.valueOf(tempLetter);
                        String removeThis = keptWords[j];
                        if (removeThis != null) {
                            char tempLetter2 = removeThis.charAt(k);
                            if (tempLetter != tempLetter2 && removeThis != null) {
                                tempRemovers.add(removeThis);
                            }
                        }
                    }
                    wordList.removeAll(tempRemovers);
                    tempRemovers.clear();
                }
            }
        }

    public HashSet<String> getWordList() {
        return wordList;
    }
    public void setWordList(HashSet<String> list) {
        wordList = list;
    }
}
