import java.io.PrintWriter;
import java.util.Scanner;

class SAAnswer extends Answer{
	protected String ans;
	
	//constructor is just the string holding the answer choice 
	SAAnswer(String answer){
		this.ans = answer;
	}
	
	SAAnswer(Scanner file){
		this.ans = file.nextLine();
	}
	//print out the string for the answer
	public void print() {
		System.out.println(this.ans);
	}
	
	//function to award credit for the student
	//makes the cancer case insensitive 
	public double getCredit(Answer rightAnswer) { 
		if(rightAnswer instanceof SAAnswer) { //check for cast
			SAAnswer rightAns = (SAAnswer) rightAnswer; //type cast
			if(this.ans.toUpperCase().equals(rightAns.ans.toUpperCase())) { //if theyre the same word award credit
				return 1.0;
			}
			else {
				return 0.0;
			}
		}
		else {
			return 0.0;
		}
	}
	
	public void save(PrintWriter file) {
		file.println("SAAnswer");
		file.println(this.ans);
	}
	
	public String getString() {
		return this.ans;
	}
}
