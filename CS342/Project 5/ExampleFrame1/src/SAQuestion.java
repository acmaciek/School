import java.io.PrintWriter;
import java.util.Scanner;

class SAQuestion extends Question{
	
	public SAQuestion(String text, double maxValue) {
		super(text,maxValue);
	}
	//===============================================================================
	public SAQuestion(Scanner file) {
		super(file);
		rightAnswer = new SAAnswer(file.nextLine());
	}
	//===============================================================================
	public SAAnswer getNewAnswer() {
		
		Scanner scan = ScannerFactory.getKeyboardScanner();  
		System.out.println("Enter answer");
		return new SAAnswer(scan.nextLine());
	}
	//===============================================================================

	public SAAnswer getNewAnswer(String text) {
		return new SAAnswer(text);
	}
	//===============================================================================

	public void getAnswerFromStudent() {
		print();
		Scanner scan = ScannerFactory.getKeyboardScanner();
		studentAnswer = new SAAnswer(scan.nextLine());
 	}
	
	public void getAnswerFromStudent(String a) {
		studentAnswer = new SAAnswer(a);
 	}
	
	public double getValue() {
		
		if(studentAnswer==null) {
			return 0.0;
		}
		return studentAnswer.getCredit(rightAnswer)* maxValue;
	}
	//===============================================================================
	
	public void setRightAnswer(Answer ans) {
		
		rightAnswer = ans;		
	}
	//===============================================================================
	
	public void print() {
		
		System.out.println(text);

		
	}
	//===============================================================================

	public void save(PrintWriter file) {
		
		file.println("SAQuestion");
		file.println(this.maxValue);
		file.println(this.text);
		
		SAAnswer ans = (SAAnswer)rightAnswer;
		ans.save(file);
		
	}
	//===============================================================================

}
