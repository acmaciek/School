

import java.util.ArrayList;
import java.util.Collections;

public class Exam {
	private ArrayList<Question> questions;
	private String text;

	public Exam(String header) {
		text = header;
		questions = new ArrayList<Question>();
	}

	public void print() {
		System.out.println(text);
		for(Question q : questions) {
			q.print();
		}
	}	
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	

	public void reorderQuestions() {
		Collections.shuffle(questions);
	}
	
	public void reorderMCAnswers(int position) {
		
		if (position == -1) {
			
			for (Question q : questions) {
				
				if (q instanceof MCQuestion)
				{
					((MCQuestion) q).reorderAnswers();
				}
			}
		}
		else {	
			if (questions.get(position) instanceof MCQuestion) {	
				((MCQuestion) questions.get(position)).reorderAnswers();
			} 
			else 
				System.out.println("Not a valid Multiple Choice Question\n");
		}
	}

	double getValue() {
		double score=0;
		
		for(Question i: questions) {
			 score+=i.getValue();
		}
		 return score;
	}

	public void getAnswerFromStudent(int position) {
		int pos = position + 1;
		System.out.println("Question #" + pos);
		questions.get(position).getAnswerFromStudent();
	}
}

