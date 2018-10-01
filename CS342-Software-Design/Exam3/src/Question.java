import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Question{
	
	protected String text;
	protected Answer rightAnswer;
	protected Answer studentAnswer;
	protected double maxValue=0;
	
	//========================================================================
	//Constructor
	protected Question(String txt, double mxValue) {
		this.text = txt;
		this.maxValue = mxValue;		 
	}
	//========================================================================

	protected Question(Scanner file) {
		this.maxValue = Double.parseDouble(file.nextLine());
		this.text = file.nextLine();		 
	}
	//========================================================================

	abstract void print();  
	public void setRightAnswer(Answer ans) {
		this.rightAnswer = ans;
	}
	abstract Answer getNewAnswer();
	//========================================================================

 	abstract void getAnswerFromStudent();
	//========================================================================

 	abstract double getValue();
	//========================================================================

	abstract void save(PrintWriter file);

	//========================================================================

	public void saveStudentAnswers(PrintWriter file) {
		
		if(studentAnswer == null) {
			return;
			}
		if(studentAnswer instanceof MCSAAnswer) {
			file.println("MCSAAnswer");
		}
		studentAnswer.save(file);
		file.println();
	}
	//========================================================================

	public void restoreStudentAnswers(Scanner file) {
		if(this instanceof SAQuestion) {
			studentAnswer = new SAAnswer(file.nextLine());
		}
		else if(this instanceof MCQuestion) {
			studentAnswer = new MCSAAnswer(file.nextLine(), 0.0);
		}
	
	}
	//========================================================================

}