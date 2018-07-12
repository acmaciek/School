/*
 * Tim Peloza
 * tpeloz2
 * Description: MCSA Question is the single right answer multiple choice question. This class allows
 * for modification of its various aspects.
 */
import java.io.PrintWriter;
import java.util.Scanner;

public class MCSAQuestion extends MCQuestion{
	
	/*
	 * Constructor
	 * Parameters: String, double
	 * Sets the question text and maximum credit
	 */
	public MCSAQuestion(String text, double maxCredit) {
		super(text, maxCredit);
		// TODO Auto-generated constructor stub
	}
	

//public MCSAQuestion(Scanner s) {
//		super(s);
//		int numAs = Integer.parseInt(s.nextLine());
//		for(int i = 0; i < numAs; i++) {
//			double pts = s.nextDouble();
//			String Answer = s.nextLine().trim();
//			MCSAAnswer answer=new MCSAAnswer(Answer,pts);	
//			answers.add(answer);
//		}
//	}

public MCSAQuestion(Scanner scan){
	super(scan);
	numberOfAnswers = Integer.parseInt(scan.nextLine());
	//MCAnswer a = null;
	for(int i = 0; i < numberOfAnswers; i++) {
	double pts = scan.nextDouble();
	String Answer = scan.nextLine().trim();
	MCSAAnswer answer=new MCSAAnswer(Answer,pts);	
	answers.add(answer);
}
}
	/*
	 * getNewAnswer
	 * returns: MCSA answer
	 * Creates a new MCSA Answer object with default values. Empty string and 0 credit.
	 */
	@Override
	public Answer getNewAnswer() {
		// TODO Auto-generated method stub
		String text = "";
		return new MCSAAnswer("", 0.0); //Returns new MSCA answer object
	}
	
	/*
	 * getNewAnswer
	 * Parameters: String, double
	 * returns: MCSA answer
	 * Creates a new MCSA Answer object. Sets the String in the parameter to the question text and
	 * the double to the credit if selected.
	 */
	public Answer getNewAnswer(String text, double creditIfSelected) {
		// TODO Auto-generated method stub
		//return new MCSAAnswer(text, creditIfSelected); //Returns new MSCAAnswer object
		MCSAAnswer answer = new MCSAAnswer(text, creditIfSelected);
		return answer;
	}

	/*
	 * getAnswerFromStudent
	 * Gets the student answer from the keyboard. A char is read in and converted to the appropriate answer
	 */
	@Override
	
public void getAnswerFromStudent(String a){
		studentAnswer = answers.get(a.charAt(0) - 'a');
	}
	
	public void getAnswerFromStudent() {
		// TODO Auto-generated method stub
		char studentAnswerText = ' ';
		Scanner scan = ScannerFactory.getKeyboardScanner(); //Creates a scanner object that reads from system in
		print();
		System.out.print("Enter here: ");
		while ((studentAnswerText < 'a') || (studentAnswerText > ('a' + 4))){
			studentAnswerText = (char) scan.nextLine().charAt(0); //Gets the first char entered
		}
		System.out.println();
		studentAnswer = answers.get(((int)(studentAnswerText - 'a'))); //Sets student answer to 
	}				//to the index of the
			
	//of the selected answer
	/*
	 * getValue
	 * return: double
	 * Returns the credit of the question.
	 */
	@Override
	
	
//	public double getValue() {
//		// TODO Auto-generated method stub
//		return super.getValue((MCAnswer)studentAnswer); //Checks credit of student answer
//	}
	public void setRightAnswer(Answer ans) {
		this.rightAnswer = ans;
	}
	public double getValue() {
		 
		try {
			return super.getValue((MCSAAnswer)studentAnswer);  
		}catch (Exception e){
			System.out.println(e);
			return 0.0;
		}
	}
	
	/*
	 * save
	 * Parameter: PrintWriter
	 * Description: Saves all the information of this question.
	 */
	@Override
	public void save(PrintWriter pWrite) {
		// TODO Auto-generated method stub
		pWrite.println("MCSAQuestion"); //Information to be saved
		pWrite.println(maxValue);
		pWrite.println(text);
		pWrite.println(numberOfAnswers);
		for (MCAnswer ans: answers){ //Saves all answers. Credit is separate from the answer object.
			pWrite.print(ans.getCredit(ans) + " ");
			ans.save(pWrite);
		}
		
		pWrite.println();
		
	}
//	public void save(PrintWriter file) {
//		file.println("MCSAQuestion");
//		super.save(file);
//		file.println();
//	}
}
