
import java.io.PrintWriter;
import java.util.Scanner;

abstract class MCAnswer extends Answer {

	
	//protected boolean choosenAns;
	protected String text;
	protected double creditIfSelected;
	
	//Constructor
	protected MCAnswer(String answer, double credit) {
		text = answer;
	//	choosenAns = false;
		creditIfSelected = credit;
	}
	

	protected MCAnswer(Scanner scan) {
		this.text = scan.nextLine();
	}
	
  	public void print() {
//		if(this.choosenAns == false) {
//			System.out.println(ans);
//		}
	//	else if(this.choosenAns==true) {
			System.out.println(text);
	//	}
	}
	 
// 	public void setSelected(boolean selected) {
// 		choosenAns = selected;
//	}
 	
	public double getCredit(Answer rightAnswer) {	
		
			MCAnswer Rans = (MCAnswer) rightAnswer;
			
			if(text.equals(Rans.text)) {
				return Rans.creditIfSelected;
			}
			else return 0.0;		
	}
	
 	
	public void save(PrintWriter s) {
		s.println(text.trim());
	}
	
 	
	public String getString() {
		return  text;
	}
}
