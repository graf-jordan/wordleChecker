import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        makeBoxHor();
        askUserForInput();
        makeBoxHor();
        String toCheck = getUserInput();
    }
    public static void makeBoxHor() {
        String boxTop = "==========================================================================================";
        System.out.printf(boxTop + "\n");
    }
    public static void askUserForInput(){
        String userAsk = "Please enter a five letter word";
        userAsk += "\n";
        userAsk += "This program will try and guide you to a correct World guess!";
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
}
