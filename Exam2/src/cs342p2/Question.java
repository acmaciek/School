//package cs342p2;

public abstract class Question {
	protected String text;
	protected Answer rightAnswer;
	protected Answer studentAnswer;
	protected double maxValue;
	

	protected Question(String text, double maxValue) {
	
		this.text = text;
		this.maxValue = maxValue;
	}


	public void print() {
		System.out.println(text);
	}

	abstract void setRightAnswer(Answer ans);

	abstract Answer getNewAnswer();

	abstract Answer getAnswerFromStudent();

	abstract double getValue();

}

