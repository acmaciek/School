import java.util.ArrayList;
import java.util.Collections;

//Maciej Girek
//mgirek2

public class Question {
	private ArrayList<Answer> AnswerList; //Create list of answers
	private String question;
	private Double totalPoints;
	
	Question (String newQuestion) {
		question = newQuestion;
		AnswerList = new ArrayList<Answer>();
		totalPoints = 0.0;
	}
	
	void addAnswer(Answer a1) { //add answer to the list
		AnswerList.add(a1);
	}
	
	void print() { //print answers
		System.out.println(question);
		
		for(Answer d:AnswerList) {
            System.out.println(d.getAnswer());
        }
	}
	
	void selectAnswer(int position) { //set answer at given position to true
		for(Answer d:AnswerList) {
            d.setSelected(false);
        }
		AnswerList.get(position-1).setSelected(true);
	}
	
	void unselectAnswer(int position) { //set answer at given position to false
		AnswerList.get(position-1).setSelected(false);
	}
	
	void reorderAnswers() { //shuffle answers
		Collections.shuffle(AnswerList);
	}
	
	double getValue() { //count points for each answer
		totalPoints = 0.0;
		for(Answer d:AnswerList) { //Iterate through answer list to get points for each answer
            totalPoints += d.getValue(); // Add points to totalPoints
        }
		return totalPoints;
	}

}

