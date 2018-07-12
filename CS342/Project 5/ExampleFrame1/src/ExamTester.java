/*
Tim Peloza
tpeloz2
Description: This class tests the exam, question, and answer classes and subclasses. It creates two
MCSA questions and two SA questions.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamTester {
	
	/*
	 * Method to test if all classes are functioning as intended.
	 * return: boolean
	 */
	public static boolean Exam_Test(){
		//Part 1
		System.out.println("INSIDE");
		File examFile = new File("src/Exam.txt");
		File studFile = new File("src/studAns.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(examFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw2 = null;
		try {
			pw2 = new PrintWriter(studFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Exam exam = new Exam("Example Test");
		//MCMA question #1
		System.out.println("Creating question 1...");
		MCMAQuestion q = new MCMAQuestion("Select all sevens?", 1.0, 0.4);
		q.addAnswer(new MCMAAnswer("7", .3));
		q.addAnswer(new MCMAAnswer("6", 0));
		q.addAnswer(new MCMAAnswer("Seven", .3));
		q.addAnswer(new MCMAAnswer("6", 0));
		q.addAnswer(new MCMAAnswer("2", 0));
		//MCSA question #1
		System.out.println("Creating question 2...");
		MCSAQuestion quest3 = new MCSAQuestion("How many questions are there?", 1.0);
		quest3.addAnswer((MCSAAnswer)quest3.getNewAnswer("2", 0.0));
		quest3.addAnswer((MCSAAnswer)quest3.getNewAnswer("1", 0.0));
		quest3.addAnswer((MCSAAnswer)quest3.getNewAnswer("4", 1.0));
		quest3.addAnswer((MCSAAnswer)quest3.getNewAnswer("5", 0.0));
		quest3.addAnswer((MCSAAnswer)quest3.getNewAnswer("8", 0.0));
		quest3.addAnswer((MCSAAnswer)quest3.getNewAnswer("9", 0.0));
		quest3.setRightAnswer(quest3.getNewAnswer("4", 1.0));
		//SA Question #1
		System.out.println("Creating question 3...");
		SAQuestion quest2 = new SAQuestion("What is the current year?", 5.0);
		quest2.setRightAnswer(new SAAnswer("2018"));
		//SA Question #2
		System.out.println("Creating question 4...");
		SAQuestion quest4 = new SAQuestion("What liquid is necessary for survival?", 5.0);
		quest4.setRightAnswer(new SAAnswer("Water"));
		//Add questions to exam
		System.out.println("Adding questions to exam...");
		exam.addQuestion(q);
		exam.addQuestion(quest2);
		exam.addQuestion(quest3);
		exam.addQuestion(quest4);
		System.out.println("Reordering Exam...");
		
		exam.reorderQuestions();
		//exam.reorderMCAnswers(-1); //-1 reorders all multiple choice questions
		System.out.println("Saving Exam...");
		exam.save(pw); //Save exam
		System.out.println("Getting answers from test taker...\n");
		for (int i = 0; i < 4; i++){ //Gets answers from keyboard to all 4 questions
			System.out.println("Question " + (i+1)+ ":  ");
			exam.getAnswerFromStudent(i + 1);
		}
		
		//SAVING EXAM
		System.out.println("Saving Answers...");
		exam.saveStudentAnswers(pw2);
		pw.close();
		pw2.close();
		
		//RESTORING EXAM
		System.out.println("Restoring the exam and answers...");
		Scanner scan = null;
		try {
			scan = new Scanner(examFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scan2 = null;
		try {
			scan2 = new Scanner(studFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exam = null; //Sets exam to null. resetting it.
		exam = new Exam(scan); //Restores exam
		exam.restoreStudentAnswers(scan2); //Restores answers
		exam.reportQuestionValues();
		System.out.println("Answers Restored.");
		return true;
	}
	
	//Intermediate test
	//Used to scan in a 3 question exam and test its output. Must be outside of src
	public static void test2(){
		Exam exam = null;
		File examFile = new File("Exam.txt");
		File studFile = new File("studAnswer.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(examFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc2 = null;
		try {
			sc2 = new Scanner(studFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exam = new Exam(sc);
		exam.print();
		for (int i = 0; i < 3; i++){ //Gets answers from keyboard to all 4 questions
			System.out.println("Question " + (i+1)+ ":  ");
			exam.getAnswerFromStudent(i + 1);
		}
		exam.reportQuestionValues();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Tim Peloza");
		System.out.println("tpeloz2\n\n");
		
		
		if (Exam_Test()){
			System.out.println("Exam tester passed.");
		}
		else{
			System.out.println("Test failed...");
		}
		
		
		
		//test2();
	}

}
