import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Tim Peloza
 * tpeloz2
 * Description: MCAnswer relies on its subclasses to fulfill certain roles, but this class provides
 * the template for both single answer and multi answer multiple choice questions.
 */
public class MCAnswer extends Answer{
	
	protected String text; //Answer text
	protected boolean selected;
	protected double creditIfSelected;
	
	/*
	 * Constructor
	 * Parameters: Scanner
	 * Sets the answer text and the credit for this answer
	 */
	public String getText(){
		return text;
	}
	
	public MCAnswer(Scanner scan){
		super(scan);
		String data = scan.nextLine();
		int space = data.indexOf(' ');
		this.creditIfSelected = Double.parseDouble(data.substring(0, space));
		this.text = data.substring(space + 1);
	}
	
	/*
	 * Constructor
	 * Parameters: String, double
	 * Sets the answer text and the credit for this answer
	 */
	protected MCAnswer(String text, double creditIfSelected){
		this.text = text;
		this.creditIfSelected = creditIfSelected;
	}
	
	/*
	 * print
	 * Prints the answer text.
	 */
	public void print(){
		System.out.println(text);
	}
	
	/*
	 * setSelected
	 * Parameters: boolean
	 * Takes a boolean as a parameter and sets whether the question is selected or not based on that
	 * boolean
	 */
	public void setSelected(boolean selected){
		this.selected = selected; //true for selected, false if not selected
	}
	
	/*
	 * getCredit
	 * Parameters: Answer
	 * rightAnswer isn't really needed, but it is needed as a parameter. This method just
	 * returns the value if this answer is selected.
	 */
	@Override
	public double getCredit(Answer ans) {
		// TODO Auto-generated method stub
		if (((MCAnswer) ans).text.equals(this.text)){
			return creditIfSelected;
		}
		return 0.0;
	}

	/*
	 * save
	 * Parameter: PrintWrite
	 * Description: Saves the answer text to file.
	 */
	@Override
	public void save(PrintWriter pWrite) {
		// TODO Auto-generated method stub
		pWrite.println(text);
	}
	
	public String getString(){
		return text;
	}
	
}
