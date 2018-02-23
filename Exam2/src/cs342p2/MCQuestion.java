
import java.util.ArrayList;
import java.util.Collections;

public abstract class MCQuestion extends Question {
	
	protected ArrayList<MCAnswer> answers;
	
	MCQuestion(String text, double maxValue) {
		super(text, maxValue);
		answers = new ArrayList<MCAnswer>();
	}
	
	public void print() {
		System.out.println(text);
		int a = 1;
		for(MCAnswer i: answers) {
			System.out.print(a + ")  ");
			i.print();
			System.out.println("\n");
			a++;
		}
	}

	public void addAnswer(MCAnswer Ans) {
		answers.add(Ans);
	}

	public void reorderAnswers() {	
		Collections.shuffle(answers);
	}	
}
