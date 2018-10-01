
public class MCSAAnswer extends MCAnswer {
	
	public MCSAAnswer(String text, double points) {
		super(text, points);
	}

	public void print() {
		System.out.println(text);
	}

	public double getCredit(Answer rightAnswer) {	
		if (this.equals(rightAnswer)) {
			return points;
		}
		return 0;	
	}
	
	public String getText() { 
		return text;
	}
}
