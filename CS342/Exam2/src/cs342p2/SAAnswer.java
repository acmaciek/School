
public class SAAnswer extends Answer {
	protected String text;

	public SAAnswer(String text) {
		
		this.text = text;
	}

	public void print() {
		System.out.println(text);
	}

	public double getCredit(Answer rightAnswer) {
		if (this.equals(rightAnswer)) {
			return 1;
		} 
			return 0;
	}
	
	String getText() {
		return text;
	}
}
