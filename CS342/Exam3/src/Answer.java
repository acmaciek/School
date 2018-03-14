import java.io.PrintWriter;
import java.util.Scanner;



public abstract class Answer {
	
	protected Answer() {}
	
	protected Answer(Scanner file) {}

	//To print an answer
	abstract void print();	
	
	//Returns the credit 
	abstract double getCredit(Answer rightAnswer);
	
	//Save
	abstract void save(PrintWriter file);
}