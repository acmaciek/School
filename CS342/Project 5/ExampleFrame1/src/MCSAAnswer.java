/*
 *Tim Peloza
 *tpeloz2
 *Description: Child of MCAnswer stores a specific answer type. 
 */
import java.util.Scanner;

/*
 * Tim Peloza
 * tpeloz2
 * Description: MCSA Answer is a subclass of MCAnswer and represents a single answer multiple
 * choice question.
 */
public class MCSAAnswer extends MCAnswer{
	
	/*
	 * Constructor
	 * Parameters: String, double
	 * Sets answer text and credit for this answer.
	 */
	 MCSAAnswer(String text, double creditIfSelected){
		super(text, creditIfSelected);
	}
	
	/*
	 * Constructor
	 * Parameters: Scanner
	 * Description: Calls super/parent
	 */
	 MCSAAnswer(Scanner scan){
		super(scan);
	}
	
}
