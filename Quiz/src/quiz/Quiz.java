package quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Quiz {
  static Scanner keyboard = new Scanner(System.in);
  static QuestionPaper questionPaper = new QuestionPaper();
  /**
   * 
   * @param args ..
   */
  public static void main(String[] args) {
    boolean stopQuiz = false;
    System.out.println("\nWelcome to Quiz.\n");
    while (!stopQuiz) {
      System.out.println("1) Add a question to the quiz.");
      System.out.println("2) Remove a question to the quiz.");
      System.out.println("3) Run the quiz.");
      System.out.println("4) Show the quiz question paper.");
      System.out.println("5) quit.");
      
      int choice = keyboard.nextInt();
      
      switch (choice) {
        case 1:
          String questionTitle = "";
          System.out.print("Question : ");
          questionTitle = keyboard.next();
          questionTitle += keyboard.nextLine();
          List<String> questionOptions = new ArrayList<String>();
          System.out.println("\nEnter Options(-1 to end):");
          String option = "1";
          char number = 'a';
          while (!option.equals("-1")) {
            System.out.print(number + ") ");
            //keyboard.next();
            option = keyboard.next();
            if (number == 'a' && option.equals("-1")) {
              System.err.println("\nAt least one option should be given");
              option = "1";
              
            } else if (!option.equals("-1")) {
              number++;
              questionOptions.add(option);
            }
            
          }
          System.out.print("Answer : ");
          String answer = keyboard.next();
          System.out.println();
          while (questionOptions.size() < (int)(answer.toCharArray()[0] - 96)) {
            //System.out.println(questionOptions.size() + " < " + (answer.toCharArray()[0] - 96));
            System.err.println("answer should be one the options.");
            System.out.print("Answer : ");
            answer = keyboard.next();
          }
          questionPaper.addQuestion(new Question(questionTitle, questionOptions, answer));
          break;
        case 2:
          System.out.print("Question number: ");
          int questionIndex = keyboard.nextInt();
          questionPaper.removeQuestion(questionIndex);
          break;
        case 3:
          new Quiz().runQuiz();
          break;
        case 4:
          for (Question question : questionPaper.getQuestionPaper()) {
            System.out.println("------------------------");
            System.out.println("Question: " + question.getQuestionTitle());
            System.out.println("Options: " + question.getQuestionOptions());
            System.out.println("Answer: " + question.getAnswer());
          }
          System.out.println("------------------------\n");
          break;

        default:
          stopQuiz = true;
          System.out.println("Thanks for using, Bye.");
          break;
      }
      
    }
    
  }
  
  /**
   * quiz logic.
   */
  public void runQuiz() {
    boolean submit = false;
    int score = 0;
    int answeredQuestions = 0;
    List<Integer> indexList = randomset(questionPaper.getQuestionPaper().size());
    while (indexList.size() > answeredQuestions  && !submit) {
      for (Integer integer : indexList) {
        if (indexList.get(indexList.indexOf(integer)) != -1) {
          
          System.out.println((indexList.indexOf(integer) + 1) + ") " 
              + questionPaper.getQuestionPaper().get(integer).getQuestionTitle());
          
          char number = 'a';
          System.out.print("\t");
          for (String option : questionPaper.getQuestionPaper().get(integer).getQuestionOptions()) {
            System.out.print(number++ + ") " + option + "\t");
          }
          
          System.out.println("\n\t<skip> -> k \t <submit> -> s");
          String answer = questionPaper.getQuestionPaper().get(integer).getAnswer();
          System.out.print(">");
          String response = keyboard.next();
          if (answer.equals(response)) {
            score = score + 1;
            indexList.set(indexList.indexOf(integer), -1);
            answeredQuestions = answeredQuestions + 1;
          } else if (response.equals("s")) {
            System.out.println("You submitted the quiz \n");
            submit = true;
            break;
          } else if (response.equals("k")) {
            //
          } else {
            indexList.set(indexList.indexOf(integer), -1);
            answeredQuestions = answeredQuestions + 1;
          }
        }
      }
    }
    
    System.out.println("your score is " + score);
    
  }
  
  /**
   * random unique numbers.
   */
  public static List<Integer> randomset(int size) {
    Random random = new Random();
    List<Integer> randomSet = new ArrayList<Integer>();
    while (randomSet.size() < size) {
      int number;
      number = random.nextInt(size) + 0;
      if (!randomSet.contains(number)) {
        randomSet.add(number);
      }
      
    }
    return randomSet;
  }
}
