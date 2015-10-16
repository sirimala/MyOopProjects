package quiz;

import java.util.ArrayList;
import java.util.List;


public class QuestionPaper {
  private static List<Question> questions = new ArrayList<Question>();
  private static List<Question> questionPaper ;
  
  public void addQuestion(Question question) {
    questions.add(question);
  }
  
  /**
   * 
   * @return a quesiton.
   */
  private Question getQestion(int index) {
    return questions.get(index);
  }
  
  /**
   * 
   * @return a question paper.
   */
  public List<Question> getQuestionPaper() {
    questionPaper = new ArrayList<Question>();
    int size = (questions.size() > 10) ? 10 : questions.size();
    for (int index = 0; index < size; index++) {
      questionPaper.add(getQestion(index));
      //subList will return empty it the list consist only one element.
      //questionPaper.subList(0, size); 
    }
    return questionPaper;
  }

  public void removeQuestion(int questionIndex) {
    questions.remove(questionIndex - 1);
    questionPaper.remove(questionIndex - 1);
  }

}
