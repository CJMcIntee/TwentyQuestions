/*Programmer: Christine McIntee
  Date: August 4th 2023
  Program: Twenty Questions*/
   
import java.util.*; //Scanner
import java.io.*; //PrintStream
  
//Allows the user to play a game of twenty questions, load or save
//different games, and add more objects to the game.
public class QuestionTree {
   //Fields
   private QuestionNode overallRoot;
   private UserInterface ui;
   private int games;
   private int wins;
   
   //Constructs QuestionTree with a root of "computer"
   public QuestionTree(UserInterface ui) {
      overallRoot = new QuestionNode("computer");
      this.ui = ui;
      games = 0;
      wins = 0;
   } //end QuestionTree constructor
    
   //Plays game and counts number of times played
   public void play() {
      overallRoot = playGame(overallRoot);
      games++;
   } //end play method
   
   //Guesses the object, counts number of wins, adds data to tree if program loses
   private QuestionNode playGame(QuestionNode node) {
      //Base case (stops guessing if program wins or loses)
      if (node.isAnswer()) {
         ui.print("Would your object happen to be " + node.getData() + "?");
         //Program wins
         if (ui.nextBoolean()) {
            ui.println("I win!");
            wins++;
         //Program loses
         } else {
            node = learn(node);
         }
      //Recursive call (traverses through tree)
      } else { 
         ui.print(node.getData());
         if (ui.nextBoolean()) {
            node.left = playGame(node.left);
         } else {
            node.right = playGame(node.right);
         }
      }
      return node;
   } //end playGame method
   
   //Adds new object to game/tree
   private QuestionNode learn(QuestionNode node) {
      ui.print("I lose. What is your object?");
      //New answer
      QuestionNode newNode = new QuestionNode(ui.nextLine());
      ui.println("Type a yes/no question to distinguish your");
      ui.print("object from " + node.getData() + ":");
      //New question
      String newQ = ui.nextLine();
      ui.print("And what is the answer for your object?");
      return ui.nextBoolean() ?
         new QuestionNode(newQ, newNode, node):
         new QuestionNode(newQ, node, newNode);
   } //end learn method
          
   //Saves the current tree state to the user's selected file
   public void save(PrintStream output) {
      if (output == null) {
         throw new IllegalArgumentException();
      }
      saveTree(overallRoot, output);
   } //end save method
   
   //Prints the contents of the tree as answers and questions
   private void saveTree(QuestionNode overallRoot, PrintStream output) {
      //If data is an answer
      if (overallRoot.isAnswer()) {
         output.print("A:"); 
         output.println(overallRoot.data);
      //If data is a question
      } else {
         output.print("Q:");
         output.println(overallRoot.data);
         saveTree(overallRoot.left, output);
         saveTree(overallRoot.right, output); 
      }   
   } //end saveTree method
   
   //Replaces the current tree by loading a different tree from chosen file
   public void load(Scanner input) {
      while(input.hasNextLine()) {
         overallRoot = loadTree(input); 
      }
   } //end load method
    
   //Reads data from chosen file and loads new tree
   private QuestionNode loadTree(Scanner input) {
      QuestionNode node = null; 
      if (input.hasNext())
      //Split each line of text at the ":" character 
         {String[] data = input.nextLine().split(":", 2);
         //If data is an answer 
         if (data[0].equals("A")) { 
            node = new QuestionNode(data[1]);
         //If data is a question
         } else { 
            node = new QuestionNode(data[1], loadTree(input), loadTree(input));
         }
      }
      return node;
   } //end loadTree method
   
   //Returns total number of games played
   public int totalGames() {
      return games;
   } //end totalGames method
   
   //Returns total number of games won
   public int gamesWon() {
      return wins;
   } //end gamesWon method

} //end QuestionTree class