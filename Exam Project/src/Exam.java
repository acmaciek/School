import java.util.ArrayList;
import java.util.Collections;

//Maciej Girek
//mgirek2

public class Exam {

	ArrayList<Question> QuestionList;
	String header;
	double totalPoints;
	
	Exam(String examHeader) {
		header = examHeader;
		QuestionList = new ArrayList<Question>();
		totalPoints = 0.0;
	}
	
	void addQuestion(Question q1) {
		QuestionList.add(q1);
	}
	
	void print() {
			System.out.println(header);
			System.out.println(" ");
			for(Question d:QuestionList) {
				System.out.println(" ");
	            d.print();
	        }
		}
	
	Question getQuestion(int postion) {
		return QuestionList.get(postion);
	}
	
	void reorderQuestions() {
		Collections.shuffle(QuestionList);
	}
	
	double getValue() {
		for(Question d:QuestionList) {
			System.out.println(d.question + "is ");
            totalPoints += d.getValue();
        }
		return totalPoints;
	}
}
