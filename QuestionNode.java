/*Programmer: Christine McIntee
  Date: August 4th 2023
  Program: Twenty Questions*/

//Constructs binary tree leaves and branches to 
//be used for a game of twenty questions.   
public class QuestionNode {
   //Fields
   public String data;
   public QuestionNode left;
   public QuestionNode right;
   
   //Constructs a leaf node (answer) with given data
   public QuestionNode(String data) {
      this(data, null, null);
   } //end leaf node constructor
   
   //Constructs a branch node (question) with given data
   public QuestionNode(String data, QuestionNode left, QuestionNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
   } //end branch node constructor
   
   //Determines if node is an answer/leaf
   public boolean isAnswer() {
      return this.left == (null) && this.right == (null);
   } //end isAnswer method
   
   //Returns node data as String
   public String getData() {
      return data;
   } //end getData method
   
} //end QuestionNode class