
import java.util.Scanner;

public class MCSAQuestion extends MCQuestion {

	private Scanner input;

	public MCSAQuestion(String text, double maxValue) {
		super(text, maxValue);
	}

	void setRightAnswer(Answer ans) {
		this.rightAnswer = ans;
	}	
	
 	public Answer getNewAnswer() {
 		return rightAnswer;
 	}

	public Answer getNewAnswer(String text) {
		
		MCSAAnswer ans = new MCSAAnswer(text, maxValue);
		answers.add(ans);
		return ans;
	}

	public Answer getNewAnswer(String text, double creditIfSelected) {
		MCSAAnswer ans = new MCSAAnswer(text, creditIfSelected);
		answers.add(ans);
		return ans;
	}

	public Answer getAnswerFromStudent() {
		System.out.println("Enter your answer: \n");
		input = new Scanner(System.in);
		int stdAns = input.nextInt();
		studentAnswer = answers.get(stdAns-1);
		return studentAnswer;
	}

	public double getValue() {
		if(rightAnswer.getText()==studentAnswer.getText())
		{
			return rightAnswer.getCredit(rightAnswer);
		}
		return 0;
	}
}



















