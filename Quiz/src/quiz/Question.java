package quiz;

import java.util.ArrayList;
import java.util.List;

public class Question {
  
  String questionTitle;
  List<String> questionOptions = new ArrayList<String>();
  String answer;
  
  Question(String questionTitle, List<String> questionOptions, String answer) {
    this.questionTitle = questionTitle;
    this.questionOptions = questionOptions;
    this.answer = answer;
  }
  
  public String getQuestionTitle() {
    return questionTitle;
  }
  
  public List<String> getQuestionOptions() {
    return questionOptions;
  }

  public String getAnswer() {
    return answer;
  }

}
