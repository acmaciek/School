//Maciej Girek
//mgirek2

public class Answer {
	
	String answer;
	double correctAnswer;
	double wrongAnswer;
	Boolean answerChecked;
	
	
	public Answer(String newAnswer) {
		answer = newAnswer;
		correctAnswer = 0;
		wrongAnswer = 0;
		answerChecked = false;
	}
	

	public void setSelected(Boolean selected) {
		answerChecked = selected;
	}
	
	public void setValue(double correct, double wrong) {
			correctAnswer = correct;
			wrongAnswer = wrong;
	}
	public double getValue() {
		if(answerChecked == true) {
			return correctAnswer;
		} 
		else {
			return wrongAnswer;
		}
	} 
	
	void print() {
		System.out.println(answer);
	}
}
