/*Programmer: Marty Stepp
  Date: February 20th 2010
  Edited by: Christine McIntee
  Date: August 4th 2023
  Program: Twenty Questions*/
  
import java.io.*;
import java.util.Scanner;

//A basic text user interface for the 20 questions game
public class QuestionMain implements UserInterface {
    //Fields
    private Scanner console;
    private QuestionTree tree;

    //Main method
    public static void main(String[] args) {
        QuestionMain tq = new QuestionMain();
        tq.run();
    } //end main method
    
    //Constructs a text user interface and its question tree
    public QuestionMain() {
        console = new Scanner(System.in);
        tree = new QuestionTree(this);
    } //end constructor
    
    //Returns the user's response as a String
    public String nextLine() {
        return console.nextLine();
    } //end nextLine method

    //Prints the given string to the console
    public void print(String message) {
        System.out.print(message);
        System.out.print(" ");
    } //end print method

    //Prints the given string to the console
    public void println(String message) {
        System.out.println(message);
    } //end println method

    //Prints a blank line to the console
    public void println() {
        System.out.println();
    } //end println method

    /*Waits for the user to answer a yes/no question on the console and returns the
      user's response as a boolean (true for anything that starts with "y" or "Y")*/
    public boolean nextBoolean() {
        String answer = console.nextLine();
        return answer.trim().toLowerCase().startsWith("y");
    } //end nextBoolean method
    
    //Private helper for overall game(s) loop
    private void run() {
        println("Welcome to the game of 20 Questions!");
        load();
        //"Think of an item, and I will guess it in N tries."
        println("\n" + BANNER_MESSAGE);  
        do {
            //Play one complete game
            println();
            tree.play();
            print(PLAY_AGAIN_MESSAGE);
        //Prompt to play again
        } while (nextBoolean());
        //Print overall stats
        println("\n" + String.format(STATUS_MESSAGE, tree.totalGames(), 
                                                     tree.gamesWon()));
        save();
    } //end run method
    
    //Common code for asking the user if they want to save or load
    private void load() {
        print(LOAD_MESSAGE);
        if (nextBoolean()) {
            print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = nextLine();
            try {
                Scanner in = new Scanner(new File(filename));
                tree.load(in);
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    } //end load method
    
    //Common code for asking the user if they want to save or load
    private void save() {
        print(SAVE_MESSAGE);
        if (nextBoolean()) {
            print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = nextLine();
            try {
                PrintStream out = new PrintStream(new File(filename));
                tree.save(out);
                out.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    } //end save method
    
} //end QuestionMain class