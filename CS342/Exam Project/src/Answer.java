//Maciej Girek
//mgirek2

public class Answer {
	
	private String answer;
	private double correctAnswer;
	private double wrongAnswer;
	private Boolean answerChecked;
	
	
	public Answer(String newAnswer) {
		answer = newAnswer;
		correctAnswer = 0;
		wrongAnswer = 0;
		answerChecked = false;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setSelected(Boolean selected) {  //decide whether answer is true or false
		answerChecked = selected;
	}
	
	public void setValue(double correct, double wrong) { // set values for wrong and correct answers
			correctAnswer = correct;
			wrongAnswer = wrong;
	}
	public double getValue() { //get value for wrong and correct answer
		if(answerChecked == true) {
			return correctAnswer;
		} 
		else {
			return wrongAnswer;
		}
	} 
	
	void print(int position) { //print answer in the given position
		System.out.println(answer);
	}
	
}
