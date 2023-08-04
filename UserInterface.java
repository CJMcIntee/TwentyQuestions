/*Programmer: Marty Stepp
  Date: February 20th 2010
  Edited by: Christine McIntee
  Date: August 4th 2023
  Program: Twenty Questions*/

/*Interface describing abstract user interaction operations,
  implemented by the graphical and text UIs for the game.
  QuestionTree interacts with the UI through this interface.*/
public interface UserInterface {

    /*Waits for the user to input a yes/no answer (by typing, clicking, etc.),
      and returns that answer as a boolean value (yes is true, no is false)*/
    boolean nextBoolean();

    /*Waits for the user to input a text value, and returns that answer as a String.
      Return the answer typed by the user as a String (empty string if no answer typed)*/
    String nextLine();
    
    //Displays the given output message to the user, assumes not null
    void print(String message);
    
    //Displays the given output message to the user, assumes not null
    //If the UI is a text UI, also inserts a line break (\n).
    void println(String message);

    //Various messages that are output by the user interface
    final String PLAY_AGAIN_MESSAGE = "Challenge me again?";
    final String SAVE_MESSAGE = "Shall I remember these games?";
    final String LOAD_MESSAGE = "Shall I recall our previous games?";
    final String SAVE_LOAD_FILENAME_MESSAGE = "What is the file name?";
    final String STATUS_MESSAGE = "Games played: %d\nI won: %d";
    final String BANNER_MESSAGE = "Think of an item, and I will guess it.";
    
} //end UserInterface