import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.*;


public class ExamTaker {
	public static JFrame frame;
	
	
	
	public static void main(String [] args) throws IOException {
		
		String fileName = (JOptionPane.showInputDialog(frame, "Enter name of text file containing the exam: "));
		File examFile = new File(fileName); //create new File 
		String userName = (JOptionPane.showInputDialog(frame, "Please enter your name "));
		FileOutputStream ans = new FileOutputStream("StudentAnswer.txt", false);
		PrintWriter studentAns = new PrintWriter(ans);
		Scanner fileScanner = new Scanner(examFile);
		Exam exam1 = new Exam(fileScanner);

		while(true) {
 			String userChange = JOptionPane.showInputDialog(frame, "If You want to answer the question type 'yes' ");
			if(userChange.equals("yes") || userChange.equals("Yes")) {
			String userInput = JOptionPane.showInputDialog(frame, "Enter number of the question you want to answer \n" +
					exam1.getQuestionText()
			);
			int questionNumber = Integer.parseInt(userInput);
			userInput = JOptionPane.showInputDialog(exam1.getQuestionText(questionNumber - 1));
			exam1.getAnswerFromStudent(questionNumber,userInput);
			}
			else {
				break;
			}
		}
		exam1.saveStudentAnswers(studentAns,userName);
		studentAns.close();
		File eFile = new File("Exam.txt");
		File saFile = new File("StudentAnswer.txt");
		Scanner studentAnswers = new Scanner(saFile);
		Scanner remakeExam = new Scanner(eFile);
		exam1 = new Exam(remakeExam);
		exam1.restoreStudentAnswers(studentAnswers);
		//exam1.reportQuestionValues();
	}
}

