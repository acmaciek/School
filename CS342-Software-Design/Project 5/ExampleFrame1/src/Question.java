//Maciej Girek CS 342 
// mgirek2
// Project 3

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Question{
	
	protected String text;
	protected Answer rightAnswer;
	protected Answer studentAnswer;
	protected double maxValue = 0;
	
	protected Question(String txt, double mxValue) {
		this.text = txt;
		this.maxValue = mxValue;		 
	}

	protected Question(Scanner file) {
		this.maxValue = Double.parseDouble(file.nextLine());
		this.text = file.nextLine();		 
	}

	public String getText() {
		return text;
	}
	
	abstract void print();  
	public void setRightAnswer(Answer ans) {
		this.rightAnswer = ans;
	}
	abstract Answer getNewAnswer();

 	abstract void getAnswerFromStudent();
 	abstract void getAnswerFromStudent(String a);

 	abstract double getValue();

	abstract void save(PrintWriter file);


	public void saveStudentAnswers(PrintWriter file) {
		if(studentAnswer == null) {
			return;
		}
		if(studentAnswer instanceof MCSAAnswer) {
			file.println("MCSAAnswer");
		}
		else if(studentAnswer instanceof SAAnswer) {
			file.println("SAAnswer");
		}
		studentAnswer.save(file);
		file.println();
	}

	public void restoreStudentAnswers(Scanner file) {
		if(this instanceof SAQuestion) {
			studentAnswer = new SAAnswer(file.nextLine());
		}
		else if(this instanceof MCQuestion) {
			studentAnswer = new MCSAAnswer(file.nextLine(), 0.0);
		}
	
	}

	public void saveStudentAnswer(PrintWriter pWrite) {
		// TODO Auto-generated method stub
		
	}

}
