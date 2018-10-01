/*
 * Tim Peloza
 * tpeloz2
 * Description: MCMA works similar to MCSAQuestion but allows multiple selection.
 */
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MCMAQuestion extends MCQuestion{
	
	protected ArrayList<MCAnswer> studentAnswer;
	protected double baseCredit;
	
	/*
	 * Constructor
	 * Parameteres: text for question, maximum credit for the question, and the base credit for the question
	 */
	public MCMAQuestion(String text, double maxCredit, double baseCredit) {
		super(text, maxCredit);
		// TODO Auto-generated constructor stub
		studentAnswer = new ArrayList<MCAnswer>();
		this.baseCredit = baseCredit;
	}
	
	/*
	 * Constructor
	 * Parameters: Scanner
	 * Description: Scans in the details from a file.
	 */
	public MCMAQuestion(Scanner scan){
		super(scan);
		String check = null;
		studentAnswer = new ArrayList<MCAnswer>(); //Sets the values
		baseCredit = Double.parseDouble(scan.nextLine());
		numberOfAnswers = Integer.parseInt(scan.nextLine());
		MCAnswer a = null; //Value to temproraily store answers
		for (int i = 0; i < numberOfAnswers; i++){
			a = new MCAnswer(scan);
			answers.add(a); //Adds answers to list
		}
	}
	/*
	 * getNewAnswer
	 * returns: MCMA answer
	 * Creates a new MCMA Answer object with default values. Empty string and 0 credit.
	 */
	@Override
	public Answer getNewAnswer() {
		// TODO Auto-generated method stub
		String text = "";
		return new MCMAAnswer("", 0.0); //Returns new MSCA answer object
	}
	
	/*
	 * getNewAnswer
	 * Parameters: String, double
	 * returns: MCMA answer
	 * Creates a new MCMA Answer object. Sets the String in the parameter to the question text and
	 * the double to the credit if selected.
	 */
	public Answer getNewAnswer(String text, double creditIfSelected) {
		// TODO Auto-generated method stub
		return new MCMAAnswer(text, creditIfSelected); //Returns new MSCAAnswer object
	}

	/*
	 * getAnswerFromStudent
	 * Gets the student answer from the keyboard. A char is read in and converted to the appropriate answer
	 */
	@Override
	public void getAnswerFromStudent() {
		// TODO Auto-generated method stub
		String studentAnswerText = "";
		char current = ' ';
		Scanner scan = ScannerFactory.getKeyboardScanner();; //Creates a scanner object that reads from system in
		print();
		
		System.out.print("Enter here(input as a single word like: 'abc', etc.): ");
		studentAnswerText = scan.nextLine();
		for (int i = 0; i < studentAnswerText.length(); i++){
			current = studentAnswerText.charAt(i);
			if ((current < 'a') || (current > ('a' + numberOfAnswers))){
				System.out.println("Incorrect Input out of bounds");
				continue;
			}
			
			
			
			studentAnswer.add(answers.get(((int)(current - 'a')))); //Sets student answer to 
																				 //to the index of the
																				 //of the selected answer
		}
		System.out.println();
		
	}		
	
	public void getAnswerFromStudent(String a){
		//studentAnswer = answers.get(a.charAt(0) - 'a');
		char current = ' ';
		for (int i = 0; i < a.length(); i++){
			current = a.charAt(i);
			if ((current < 'a') || (current > ('a' + numberOfAnswers))){
				System.out.println("Incorrect Input out of bounds");
				continue;
			}
			
			
			
			studentAnswer.add(answers.get(((int)(current - 'a')))); //Sets student answer to 
																				 //to the index of the
																				 //of the selected answer
		}
		System.out.println();
	}
																			 
	/*
	 * getValue
	 * return: double
	 * Returns the credit of the question. Compares texts of student answer to answers in list
	 * and adds result to base if any.
	 */
	@Override
//	public double getValue() {
//		// TODO Auto-generated method stub
//		double result = baseCredit;
//		for (MCAnswer ans: studentAnswer){
//			result += super.getValue(ans); //Adds up results of each student answer
//		}
//		return (result * maxValue);
//	}
	
	public double getValue() {
 		double score = this.baseCredit*maxValue;			
 		for(Answer a : studentAnswer) {
 			if(a instanceof MCMAAnswer) {
 				score += super.getValue((MCMAAnswer)a);
 			}
 		}
 		return score; 
 	}
	
	/*
	 * save
	 * Parameter: PrintWriter
	 * Description: Prints out the necessary information for the question.
	 */
	@Override
	public void save(PrintWriter pWrite) {
		// TODO Auto-generated method stub
		pWrite.println("MCMAQuestion"); //Saves private info
		pWrite.println(maxValue);
		pWrite.println(text);
		pWrite.println(baseCredit);
		pWrite.println(numberOfAnswers);
		for (MCAnswer ans: answers){ //Saves the credit separately from the answer
			pWrite.print(ans.getCredit(ans) + " ");
			ans.save(pWrite); //Save answer
		}
		pWrite.println();
	}
	
	/*
	 *saveStudentAnswers
	 *Parameter: PrintWriter
	 *Description: Saves the student answers.
	 */
	@Override
	public void saveStudentAnswers(PrintWriter pWrite){
		pWrite.println("MCMAAnswer");
		pWrite.println(studentAnswer.size());
		for (MCAnswer ans: studentAnswer){
			ans.save(pWrite);
		}
		pWrite.println();
	}
	
	/*
	 * restoreStudentAnswers
	 * Parameter: Scanner
	 * Description: Scans in student answers from files.
	 */
	public void restoreStudentAnswers(Scanner scan){
		int size = 0;
		size = Integer.parseInt(scan.nextLine()); //Converts string to int
		for (int i = 0; i < size; i++){
			studentAnswer.add((MCAnswer)getNewAnswer(scan.nextLine(), 0.0)); //Saves the answer
		}
	}
}
