
import java.util.*;
import java.io.*;

public class Exam {
	//private data types
	private ArrayList<Question> questions; //Array List for question
	private String text; //String for exam header
	
	//Constructor for Exam
	public Exam(String txt) {
		text = txt; 
		questions = new ArrayList<Question>();
	}
	
	public Exam(Scanner file) {
		text = file.nextLine();
		questions = new ArrayList<Question>();
		while(file.hasNext()) {
			String qType = file.nextLine();
			if(qType.equals("SAQuestion")) {
				questions.add(new SAQuestion(file));
			}
			else if(qType.equals("MCSAQuestion")) {
				questions.add(new MCSAQuestion(file));
			}
			else if(qType.equals("MCMAQuestion")) {
				questions.add(new MCMAQuestion(file));
			}
		}
	}

	public void AddQuestion(Question q) {
		this.questions.add(q);
	}
	

	public void print() {

		System.out.print("         EXAM 3 CS 342\n\n");
		System.out.println(text);
		for(Question q : questions) {
			q.print();
			System.out.println("\n");
		}
	}

	public void reorderQuestions() {
		Collections.shuffle(questions);
	}
	
	public void reorderMCAnswers(int position) {
		if(position < 0 || position >= this.questions.size()) { //error
			return;
		}
		if(this.questions.get(position) instanceof MCQuestion) { //make sure it is of type MCQuestion
			MCQuestion temp =  (MCQuestion)this.questions.get(position);
			temp.reorderAnswers(); //shuffle
			questions.set(position, temp);
		}
		else { //error trying to reorder a non MC question
			return;
		}
	}
	
	//Method to calculate the test takers score and return it
	//will call the helper method to calculate the score for the exam
	public double getValue() {
		double total = 0;
		for(int i=0; i<questions.size(); i++) {
			total += questions.get(i).getValue();
		}
		return total;
	}
	
	//function to get answer from student 
	//calls the question getAnswerFrom student
	public void getAnswerFromStudent(int position) {
		if(position < 0 || position >= this.questions.size()) {
			return; //error
		}
		this.questions.get(position).getAnswerFromStudent(); //get the answer from the student	
	}
	
	//Function to report the students score
	//gives the total and the what value they got for each question
	public void reportQuestionValues() {
		for(int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).print();
			System.out.println("Points received for this question: " + questions.get(i).getValue() + "\n");
		}
		
		System.out.println("Total score for the exam was: " + this.getValue());
	}
	
	//Function I wrote to enter exam taking mode
	//The test taker is able to answer each question individually and scroll through each question

	
	public void takeExam() {	
		for(Question q : questions) {
			q.print();
			q.getAnswerFromStudent();
		}
	}
	
	public void save(PrintWriter file) {
		file.println(this.text + "\n");
		for(int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).save(file);
		}
	}
	
	public void saveStudentAnswers(PrintWriter file) {
		for(int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).saveStudentAnswers(file);
		}
	}
	
	public void restoreStudentAnswers(Scanner file) {
		int i = 0;
		int length = this.questions.size();
		while(file.hasNext()) {
			String aType = file.nextLine();
			if(aType.equals("SAAnswer")) {
				if(this.questions.get(i) instanceof SAQuestion) {
					this.questions.get(i).restoreStudentAnswers(file);

				}
				i++;
				if(i >= length) {
					return;
				}
			}
			else if(aType.equals("MCSAAnswer")) {
				if(this.questions.get(i) instanceof MCSAQuestion) {
					this.questions.get(i).restoreStudentAnswers(file);

				}
				i++;
				if(i >= length) {
					return;
				}
			}
			else if(aType.equals("MCMAAnswer")) {
				if(this.questions.get(i) instanceof MCMAQuestion) {
					this.questions.get(i).restoreStudentAnswers(file);

				}
				i++;
				if(i >= length) {
					return;
				}
			}
		}
	}
}
