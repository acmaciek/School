//Maciej Girek CS 342 
// mgirek2
// Project 3

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract class MCQuestion extends Question{

	protected ArrayList<MCAnswer> answers;
	protected int numberOfAnswers;
	protected double value;

	MCQuestion(String text, double maxValue) {
		super(text, maxValue);
		numberOfAnswers = 0;
		value = 0.0;
		answers = new ArrayList<MCAnswer>();
	}
	
	protected MCQuestion(Scanner file) {
		super(file);
		this.answers = new ArrayList<MCAnswer>();
	}
	 
	/*
	 * print
	 * Prints the question along with the answers and their positions.
	 */
	@Override
	public String getText() {
		String newText;
		newText = text + "\n";
		for(int i = 0; i < answers.size();i++) {
			newText += (char)('a' + i) + ") " + answers.get(i).getText() + "\n";
		}
		return newText;
	}
	
	public void print(){
		if (this instanceof MCSAQuestion){
			System.out.println("MCSA Question\n" + text);
		}
		else if (this instanceof MCMAQuestion){
			System.out.println("MCMA Question\n" + text);
		}
		for (int i = 0; i < answers.size(); i++){
			System.out.print((char)('a' + i) + ". ");
			answers.get(i).print(); //Adds 1 to position since Answer print assumes
		}									   //the value to be at least 1
	}
	
	public void addAnswer(MCAnswer Ans) {
		System.out.println(answers.size());
		answers.add(Ans);
		numberOfAnswers++;
	}

 	public void reorderAnswers() {
		Collections.shuffle(answers);
	}

 	public double getValue(MCAnswer Ans) {
		double score = 0;
		for(Answer a : answers) {
			score += a.getCredit(Ans);
		}
		return this.maxValue * score;
	}
	
	public void save(PrintWriter file) {
		
		file.println(maxValue);
		file.println(text);
		file.println(answers.size());
		
		for(int i = 0; i < answers.size(); i++) {
			file.print(this.answers.get(i).getCredit(this.answers.get(i))+ " ");
			file.println(this.answers.get(i).getString());
		}
		file.println();
	}
}
