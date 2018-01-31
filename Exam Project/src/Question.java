import java.util.ArrayList;
import java.util.Collections;

//Maciej Girek
//mgirek2

public class Question {
	ArrayList<Answer> AnswerList;
	String question;
	Double totalPoints;
	
	Question (String newQuestion) {
		question = newQuestion;
		AnswerList = new ArrayList<Answer>();
		totalPoints = 0.0;
	}
	
	void addAnswer(Answer a1) {
		AnswerList.add(a1);
	}
	
	void print() {
		System.out.println(question);
		
		for(Answer d:AnswerList) {
            System.out.println(d.answer);
        }
	}
	
	void selectAnswer(int position) {
		AnswerList.get(position).answerChecked = true;
	}
	
	void unselectAnswer(int position) {
		AnswerList.get(position).answerChecked = false;
	}
	
	void reorderAnswers() {
		Collections.shuffle(AnswerList);
	}
	
	double getValue() {
		for(Answer d:AnswerList) {
            totalPoints += d.getValue();
        }
		return totalPoints;
	}

}

