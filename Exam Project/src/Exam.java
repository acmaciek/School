import java.util.ArrayList;
import java.util.Collections;

//Maciej Girek
//mgirek2

public class Exam {

	ArrayList<Question> QuestionList; //Create list of Questions
	String header; 
	double totalPoints;
	
	Exam(String examHeader) {
		header = examHeader;
		QuestionList = new ArrayList<Question>();
		totalPoints = 0.0;
	}
	
	void addQuestion(Question q1) { //Add new question to the list of questions
		QuestionList.add(q1);
	}
	
	void print() { //Print the exam
			System.out.println(header);
			System.out.println(" ");
			for(Question d:QuestionList) {
				System.out.println(" ");
	            d.print();
	        }
			System.out.println(" ");
		}
	
	Question getQuestion(int postion) { //Retrieve question in the qiven position
		return QuestionList.get(postion);
	}
	
	void reorderQuestions() { //Shuffle questions
		Collections.shuffle(QuestionList);
		for(Question d:QuestionList) {
			d.reorderAnswers();
		}
	}
	
	public double getValue() { //Count total points for this exam
		for(Question d:QuestionList) { //Iterate through question list to get points for each question
            totalPoints += d.getValue(); //add the value for each question to totalPoins
        }
		return totalPoints; 
	}
}
