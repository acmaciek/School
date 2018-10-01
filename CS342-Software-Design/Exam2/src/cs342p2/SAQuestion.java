
import java.util.Scanner;
public class SAQuestion extends Question {
	
	public SAQuestion(String text, double maxValue) {
		super(text, maxValue);
	}

	public Answer getNewAnswer() {
		System.out.println("Type in your answer.\n");
		Scanner inp = new Scanner(System.in);
		String ansU = inp.nextLine();
		SAAnswer ans = new SAAnswer(ansU);
		return ans;
	}

	public Answer getAnswerFromStudent() {
		System.out.println("Type in your answer.\n");
		Scanner inp = new Scanner(System.in);
		String studentAns = inp.nextLine();
		this.studentAnswer = new SAAnswer(studentAns);
        return this.studentAnswer;        
	}

	public double getValue() {
		if(studentAnswer.getText().equals(rightAnswer.getText())) {			
			return rightAnswer.getCredit(rightAnswer);
		}
        return 0;
	}

	void setRightAnswer(Answer ans) {		
		rightAnswer=ans;
	}
}
