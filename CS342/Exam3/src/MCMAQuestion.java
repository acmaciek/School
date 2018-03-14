import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


class MCMAQuestion extends MCQuestion{
	protected ArrayList<Answer> studentAnswer;
	public double baseCredit;
	
	public MCMAQuestion(String text, double maxValue, double baseCredit) {
		super(text, maxValue);
		this.baseCredit = baseCredit;
		studentAnswer = new ArrayList<Answer>();		
	}
	
	public MCMAQuestion(Scanner scan) {
		super(scan);
		studentAnswer = new ArrayList<Answer>();
		baseCredit = Double.parseDouble(scan.nextLine());
		int length = Integer.parseInt(scan.nextLine());
		for(int i = 0; i < length; i++) {			
			double pts = scan.nextDouble();
			String answerString = scan.nextLine().trim();
			answers.add(new MCMAAnswer(answerString,pts));
		}
	}
		
	public MCMAAnswer getNewAnswer() {
		
		Scanner scan = ScannerFactory.getKeyboardScanner();  
		System.out.println("Please enter your answer");
		String Answer = scan.nextLine();
		
		while(!(scan.hasNextDouble())) {
			scan.next();
		}
		
		double credit = scan.nextDouble();
		return new MCMAAnswer(Answer, credit);
	}
	
	public MCMAAnswer getNewAnswer(String text, double creditIfSelected) {
		return new MCMAAnswer(text,creditIfSelected);
	}
	

 	public void getAnswerFromStudent() {
	
		Scanner ans = ScannerFactory.getKeyboardScanner();
		String strinAnswer = ans.nextLine().trim();
		strinAnswer = strinAnswer.replaceAll("\\s+",""); 
		for(int i = 0; i<strinAnswer.length(); i++) {
			char choice = strinAnswer.charAt(i);   
			int intChoice = (int)choice - 97;  
			this.studentAnswer.add(answers.get(intChoice));
		}
		
	
		for(int i = 0; i < studentAnswer.size(); i++) {  
			MCMAAnswer answer = (MCMAAnswer)studentAnswer.get(i);
			studentAnswer.set(i, answer);			
		}
	}
	
 	public double getValue() {
 		double score = this.baseCredit*maxValue;			
 		for(Answer a : studentAnswer) {
 			if(a instanceof MCMAAnswer) {
 				score += super.getValue((MCMAAnswer)a);
 			}
 		}
 		return score; 
 	}
 		
	public void print() {
		System.out.println(text);
		int i = 0;
		for(Answer a: answers) {
			System.out.print("("+(char)(i+'a')+") ");
			a.print();
			i++;
		}
		
	}
	
	public void restoreStudentAnswers(Scanner file) {
		
		int n = Integer.parseInt(file.nextLine());
		
		for(int i = 0; i < n; i++) {
			this.studentAnswer.add(new MCMAAnswer(file.nextLine(), 0));
		}
	}
	
	public void saveStudentAnswers(PrintWriter ssaf) {
		
		ssaf.println("MCMAAnswer");
		

		ssaf.println(studentAnswer.size());
		for(int i = 0; i < studentAnswer.size(); i++) {
			studentAnswer.get(i).save(ssaf);
		}
		ssaf.println();
	}
	
	public void save(PrintWriter ssf) {
		
		ssf.println("MCMAQuestion");
		ssf.println(this.maxValue);
		ssf.println(this.text);
		ssf.println(this.baseCredit);
		ssf.println(answers.size());
		
		for(int i = 0; i < answers.size(); i++) {
			
			ssf.print(this.answers.get(i).getCredit(this.answers.get(i))+ " ");
			ssf.println(this.answers.get(i).getString());
		}
		ssf.println();
	}
}