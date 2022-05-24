import java.util.Scanner;
import java.io.FileNotFoundException;
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Dictionary words = new Dictionary();
        askUserForInput();
        while (true) {
            String toCheck = getUserInput();
            boolean toEnd = isCorrect();
            if (toEnd) {
                break;
            }
            int[] temp = getWordInPosition(toCheck);
            words.parseWord(toCheck, temp);
            showUserWords(toCheck, words);
        }
    }
    //the following functions are used for interface abstraction, except for the very last one
    //the last one is simply for ensuring program does not crash when user enters string when asked for int
    public static void makeBoxHor() {
        String boxTop = "==========================================================================================";
        System.out.printf(boxTop + "\n");
    }
    public static void askUserForInput(){
        makeBoxHor();
        String userAsk = "Please enter a five letter word";
        userAsk += "\n";
        userAsk += "This program will try and guide you to a correct Wordle guess!";
        System.out.println(userAsk);
        makeBoxHor();
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
    public static int[] getWordInPosition(String word) { //function asks user for the Wordle values of each letter
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
    public static void showUserWords(String word, Dictionary words) { //function to print how many words they could guess from
        makeBoxHor();
        System.out.println("Using your inputted word of " + word + " you can select any of these words");
        makeBoxHor();
        words.printValidWords();
        System.out.println();
        makeBoxHor();
    }
    public static boolean isCorrect() { //ask user if the word they guessed was correct
        Scanner sc = new Scanner(System.in);
        makeBoxHor();
        String correct = "0";
        while(!correct.equals("y") && !correct.equals("n")) {
            System.out.println("Was your guess correct? y/n");
            correct = sc.nextLine();
            if (correct.equals("y")) {
                System.out.println("Congrats! good job on your Wordle!");
                makeBoxHor();
                return true;
            } else if (correct.equals("n")) {
                System.out.println("Alright! Let's keep trying!");
                makeBoxHor();
                return false;
            }
            System.out.println("Please enter y or n");
            correct = sc.nextLine();
        }
        return false;
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
