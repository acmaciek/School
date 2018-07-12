import java.util.Scanner;

/*
 * MCMAA
 * 
 * Description: Answer assigned to multiple choice multiple answer type questions.
 */
public class MCMAAnswer extends MCAnswer{
	
	/*
	 * Constructor
	 * Parameters: String, double
	 * Sets answer text and credit for this answer.
	 */
	public MCMAAnswer(String text, double creditIfSelected){
		super(text, creditIfSelected);
	}
	
	/*
	 * Constructor
	 * Parameter: Scanner
	 * Description: Calls parent constructor which does everything for the child.
	 */
	public MCMAAnswer(Scanner scan){
		super(scan);
	}
	
}
