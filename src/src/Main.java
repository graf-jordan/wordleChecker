import java.util.Scanner;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        makeBoxHor();
        askUserForInput();
        makeBoxHor();
        WordChecker wc = new WordChecker();
        Dictionary words = new Dictionary();
        String toCheck = getUserInput();
        makeBoxHor();
        int[] temp = getWordInPosition(toCheck);
        makeBoxHor();
        words.parseWord(toCheck, temp);
        makeBoxHor();
        words.printValidWords();
    }
    public static void makeBoxHor() {
        String boxTop = "==========================================================================================";
        System.out.printf(boxTop + "\n");
    }
    public static void askUserForInput(){
        String userAsk = "Please enter a five letter word";
        userAsk += "\n";
        userAsk += "This program will try and guide you to a correct Wordle guess!";
        System.out.println(userAsk);
    }
    public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        String word = "0";
        while(word.length() != 5) {
            System.out.println("Please enter a 5 letter word:");
            word = sc.nextLine();
        }
        return word;
    }
    public static int[] getWordInPosition(String word) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What was the status of each letter? 1 = grey, 2 = yellow, 3 = green");
        int posCheck = 0;
        int[] wordValues = new int[5];
        System.out.println("Your guessed word is --- " + word + " ---");
        while(posCheck < 5) {
            System.out.println("Letter '" + word.charAt(posCheck) + "' was grey, yellow, or green?");
            wordValues[posCheck] = getCheckedInt();
            while (wordValues[posCheck] > 3 || wordValues[posCheck] < 1) { //for correct input
                System.out.println("Please enter 1,2,3! 1 for grey, 2 for yellow, 3 for green!");
                wordValues[posCheck] = getCheckedInt();
            }
            posCheck++;
        }
        return wordValues;
    }
    public static int getCheckedInt() { //small method to call upon to return an integer that only accepts int
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Oops! That doesn't look like an integer! Please try again.");
            sc.nextLine();
        }
        int intCheck = sc.nextInt();
        return intCheck;
    }
}
