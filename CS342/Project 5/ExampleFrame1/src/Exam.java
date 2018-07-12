//Maciej Girek CS 342 
// mgirek2
// Project 3

import java.util.*;

import javax.swing.JTextArea;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Exam {

	private ArrayList<Question> questions;  //arrayList of questions
	private String text; 
	private String name;
	LocalDate date = LocalDate.now();
	LocalTime time = LocalTime.now();
	
	public Exam(String txt) { //Constructor
		text = txt; 
		questions = new ArrayList<Question>(); //initialize questions
	}

	public void addQuestion(Question q) {
		this.questions.add(q);
	}
	

	public void print() {
		System.out.print("         EXAM 3 CS 342\n");
		System.out.print("        UIC Spring 2018\n\n");
		System.out.println(text);
		for(Question q : questions) {
			q.print();
			System.out.println("\n");
		}
	}
	
	public String getQuestionText(int position) {
		if(position < 0 || position >= this.questions.size()) {
			return "No Question at this postion";
		}
		//return Question.getText();
		return questions.get(position).getText();
	}
	
	public String getQuestionText() {
		String newS = "";
		for(int i = 0; i< questions.size();++i) {
			int j = i + 1;
			newS += (j) +".  "+ questions.get(i).text + "\n";
		}
		return newS;
	}
	
	public String Jprint() {
		
		String s="         EXAM 3 CS 342\\n\""+"        UIC Spring 2018\n\n"+text;
		 
		for(Question q : questions) {			
			s=s+q.text+"\n";
			
		}
		
		return s;
	}
	
	public int getNumberOfQuestions(){
		return questions.size();
	}

	public void reorderQuestions() {
		Collections.shuffle(questions);
	}
	
	public void reorderMCAnswers(int position) {
		if(position < 0 || position >= this.questions.size()) {
			return;
		}
		if(this.questions.get(position) instanceof MCQuestion) { 
			MCQuestion reorder =  (MCQuestion)this.questions.get(position);
			reorder.reorderAnswers(); 
			questions.set(position, reorder);
		}
		else { 
			return;
		}
	}
	
	public Exam(Scanner s) {
		text = s.nextLine();
		questions = new ArrayList<Question>();
		while(s.hasNext()) {
			String qType = s.nextLine();
			if(qType.equals("SAQuestion")) {
				questions.add(new SAQuestion(s));
			}
			else if(qType.equals("MCSAQuestion")) {
				questions.add(new MCSAQuestion(s));
			}
			else if(qType.equals("MCMAQuestion")) {
				questions.add(new MCMAQuestion(s));
			}
			else if(qType.equals("NUMQuestion")) {
				questions.add(new NumQuestion(s));
			}
		}
	}
	
	public double getValue() {
		double total = 0;
		for(Question q : questions) {
			total += q.getValue();
		}
		return total;
	}

	public void getAnswerFromStudent(int position) {
		this.questions.get(position-1).getAnswerFromStudent(); 	
	}
	
	public void getAnswerFromStudent(int position,String text) {
		this.questions.get(position-1).getAnswerFromStudent(text); 	
	}
	

	
	
	public void reportQuestionValues() {
	for(Question q : questions) {
		q.print();
		System.out.println();
		System.out.println("For this question you received " + q.getValue() + " points");
		System.out.println("\n");
	}
		
		System.out.println("Your total score for this exams is: " + getValue() + " points");
	}
	
	
	
	public void reportQuestionValues(JTextArea ta) {
		DecimalFormat d = new DecimalFormat("##.00");
		ta.append("\n\t\t      Results\n\tQuestions\t\tValue\n"); //Labels
		
		for (int i = 0; i < questions.size(); i++){ 			//Loop to print out results(the values of each )
			ta.append("-------------------------------------------------\n");  //Question)
			ta.append("|Question " + (i + 1) + ": \t |\t" + d.format(questions.get(i).getValue())
							   + "\t|\n");
		}
		
		ta.append("-------------------------------------------------\n");
		ta.append("|Overall      :\t|\t" + d.format(getValue()) + "\t|\n"); //Overall score
		ta.append("-------------------------------------------------\n\n");
	}
	
	
	
	public void saveScoresToFile(PrintWriter file){
		file.print(name + ","  + this.getValue());
		for(Question q : questions) {
			file.print("," + q.getValue());
		}
	}
	
	public void takeExam() {	
		for(Question q : questions) {
			q.getAnswerFromStudent();
		}
	}
	
	LocalDateTime now = LocalDateTime.now();  

	public void save(PrintWriter file) {
		file.println(this.text + "\n");
		file.println(now +"\n");
		for(int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).save(file);
		}
	}
	
	public void saveStudentAnswers(PrintWriter file,String name) {
		file.println(text);
		file.println(date + " " + time);
		file.println();
//		Scanner scan = ScannerFactory.getKeyboardScanner();
//		System.out.println("Please enter your name: ");
//		name = scan.nextLine();
		file.println(name);
		file.println();
		
		for(Question q : questions) {
			q.saveStudentAnswers(file);
		}
	}
	
	public void saveStudentAnswers(PrintWriter file) {
		file.println(text);
		file.println(date + " " + time);
		file.println();
		Scanner scan = ScannerFactory.getKeyboardScanner();
		System.out.println("Please enter your name: ");
		name = scan.nextLine();
		file.println(name);
		file.println();
		
		for(Question q : questions) {
			q.saveStudentAnswers(file);
		}
	}
	
	public void remove(int i) {
		
		questions.remove(i);
	}
	
 
	
	public void restoreStudentAnswers(Scanner file) {
		int i = 0;
		int length = this.questions.size();
		file.next();
		file.next();
		file.next();
		name = file.next();
		while(file.hasNext()) {
			String aType = file.nextLine();
			if(aType.equals("SAAnswer")) {
				if(this.questions.get(i) instanceof SAQuestion) {
					this.questions.get(i).restoreStudentAnswers(file);

				}
				i++;
				if(i >= length) {
					return;
				}
			}
			else if(aType.equals("MCSAAnswer")) {
				if(this.questions.get(i) instanceof MCSAQuestion) {
					this.questions.get(i).restoreStudentAnswers(file);

				}
				i++;
				if(i >= length) {
					return;
				}
			}
			else if(aType.equals("MCMAAnswer")) {
				if(this.questions.get(i) instanceof MCMAQuestion) {
					this.questions.get(i).restoreStudentAnswers(file);

				}
				i++;
				if(i >= length) {
					return;
				}
			}
			
			else if(aType.equals("NumAnswer")) {
				if(this.questions.get(i) instanceof NumQuestion) {
					this.questions.get(i).restoreStudentAnswers(file);

				}
				i++;
				if(i >= length) {
					return;
				}
			}
		}
	}
	
}
