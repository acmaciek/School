
public abstract class MCAnswer extends Answer {
	
	protected String text;
	protected boolean selected;
	protected double points;

	protected MCAnswer(String text, double points) {
		this.text = text;
		this.selected = false;
		this.points = points;
	}
	
	public void print() { 
		System.out.println(text);
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
