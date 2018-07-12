/*
 * Tim Peloza
 * 
 * Exam Grader
 * 
 * Description: Based on an exam file and student answer file this program grades the exam and saves 
 * 				to a file with the students name on it.
 * 
 * 				-a studentAnswerFile
 *				or
 *				-e examFile -a studentAnswerFile
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamGrader {
	
	public static boolean isMatch(File exam, File studAns){
		String examName = null;
		String studExamName = null;
		Scanner examFileScan= null;
		try {
			examFileScan = new Scanner(exam);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner studFileScan = null;
		try {
			studFileScan = new Scanner(studAns);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (examFileScan.hasNextLine()){ //Gets name of exam from exam
			examName = examFileScan.nextLine();
		}
		else{
			return false;
		}
		if (studFileScan.hasNextLine()){ //Gets name of exam from student
			studExamName = studFileScan.nextLine();
		}
		else{
			return false;
		}
		if (studExamName.equals(examName)){ //compares
			return true;
		}
		return false;
	}
	
	public static String getExamName(String studAns){
		String examName;
		Scanner studFileScan = null;
		try {
			studFileScan = new Scanner(new File(studAns));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		examName = studFileScan.nextLine();
		return examName;
	}
	
	public static void main(String args[]){
		System.out.println("Creator: Tim Peloza");
		System.out.println("tpeloz2\n");
		int length = args.length;
		String examNameToFile = null;
		String studentNameToFile = null;
		if (length == 2){ //Finds the exam associated with the student file
			if (args[0].equals("-a")){
				studentNameToFile = args[1];
				examNameToFile = getExamName(args[1]) + ".txt";
			}
			else{
				System.out.println("Incorrect format. Must be: -e file.txt");
				System.exit(-1);
			}
		}
		else{ //Normal -e exam -a studentFile: Can be in any order
			for (int i = 0; i < length; i++){
				if (args[i].equals("-e")){
					if (args[i + 1].endsWith(".txt")){
						examNameToFile = args[i + 1];
						i++;
					}
					else{
						System.out.println("Incorrect format. Must be: -e file.txt");
						System.exit(-1);
					}
				}
				else if (args[i].equals("-a")){
					if (args[i + 1].endsWith(".txt")){
						studentNameToFile = args[i + 1];
						i++;
					}
					else{
						System.out.println("Incorrect format. Must be: -a file.txt");
						System.exit(-1);
					}
				}
				else{
					System.out.println("Incorrect format. Must be: -a/e file.txt");
					System.exit(-1);
				}
			}
		}
		
		//Creates the files
		File examFile = new File(examNameToFile);
		File studAnswersFile = new File(studentNameToFile);
		File studScoreFile = new File(studentNameToFile.substring(0, studentNameToFile.indexOf(".txt"))+ "_CSV.txt");
		
		
		//Checks if the exam names match
		if (!isMatch(examFile, studAnswersFile)){
			System.out.println("Exam names do not match");
			System.exit(-1);
		}
		
		
		Scanner examScan = null;
		try {
			examScan = new Scanner(examFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner studAnswersScan = null;
		try {
			studAnswersScan = new Scanner(studAnswersFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter scoreFile = null;
		try {
			scoreFile = new PrintWriter(studScoreFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//find exam if not provided
		//Use exam file to generate the exam
		Exam exam = new Exam(examScan);
		//restore answers
		exam.restoreStudentAnswers(studAnswersScan);
		//print out table on screen
		exam.reportQuestionValues();
		//print to file
		exam.saveScoresToFile(scoreFile);
		scoreFile.close();
		System.out.println("\nSAVED AS: " + studentNameToFile + "_CSV.txt");
	}
	
}
