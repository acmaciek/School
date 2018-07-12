import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class FrameGrader{
	
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel inputPanel;
	private JPanel outputPanel;
	private JScrollPane scroll;
	private GridLayout gridLayout;
	private BoxLayout boxLayout;
	private JTextArea output;
	private JButton load;
	private JButton grade;
	private JTextField examText;
	private JTextField studentText;
	private ActionListener actionListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().contains("Load")){
				loadExam();
			}
			else if (e.getActionCommand().contains("Grade")){
				gradeExam();
			}
		}
		
	};
	
	String examFileName = null;
	String studFileName = null;
	Exam currExam = null;
	File examFile = null;
	File studFile = null;
	
	public FrameGrader(){
		mainFrame = new JFrame("Exam Grader");
		mainPanel = new JPanel();
		inputPanel = new JPanel();
		outputPanel = new JPanel();
		load = new JButton("Load");
		grade = new JButton("Grade");
		studentText = new JTextField();
		examText = new JTextField();
		output = new JTextArea(12, 30);
		scroll = new JScrollPane(output);
		gridLayout = new GridLayout(1, 2);
		boxLayout = new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS);
		
		mainPanel.setPreferredSize(new Dimension(800, 200));
		inputPanel.setPreferredSize(new Dimension(400, 600));
		
		mainPanel.setLayout(gridLayout);
		inputPanel.setLayout(boxLayout);
		
		
		studentText.setPreferredSize(new Dimension(400, 30));
		examText.setPreferredSize(new Dimension(400, 30));
		load.setPreferredSize(new Dimension(100, 30));
		grade.setPreferredSize(new Dimension(100, 30));
		
		studentText.setMaximumSize(studentText.getPreferredSize());
		examText.setMaximumSize(examText.getPreferredSize());
		load.setMaximumSize(load.getPreferredSize());
		grade.setMaximumSize(grade.getPreferredSize());
		
		output.setEditable(false);
		load.addActionListener(actionListener);
		grade.addActionListener(actionListener);
		
		
		//Adding the components
		mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainPanel.add(inputPanel);
		mainPanel.add(outputPanel);
		outputPanel.add(scroll);
		
		inputPanel.add(new JLabel("Exam Text File:"));
		inputPanel.add(examText);
		inputPanel.add(new JLabel("Student Text File:"));
		inputPanel.add(studentText);
		inputPanel.add(load);
		inputPanel.add(grade);
		
		//outputPanel.add(output);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		output.append("Welcome To Exam Grader!\nPlease Enter the exam you want to grade at the top\n"
					 + "and enter the student answers file in the bottom.\n");
	}
	
	public boolean isMatch(File exam, File studAns){
		String examName = null;
		String studExamName = null;
		Scanner examFileScan= null;
		try {
			examFileScan = new Scanner(examFile);
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
	
	public void loadExam(){
		output.setText("");
		examFileName = examText.getText();
		examText.setText("");
		studFileName = studentText.getText();
		studentText.setText("");
		output.append("Exam File Being Used: " + examFileName + "\n");
		output.append("Student File Being Used: " + studFileName + "\n");
		examFile = new File(examFileName);
		studFile = new File(studFileName);
		
		if (!isMatch(examFile, studFile)){
			output.setText("Files do not match.\n");
		}
		
		output.append("Files match. \n");
	}
	
	public void gradeExam(){
		output.append("Grading Exam\n");
		Scanner examScan = null;
		try {
			examScan = new Scanner(examFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner studScan = null;
		try {
			studScan = new Scanner(studFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currExam = new Exam(examScan);
		currExam.restoreStudentAnswers(studScan);
		currExam.reportQuestionValues(output);
		output.append("Exam Graded\n");
	}
	
	public static void main(String[] args) {
		FrameGrader grader = new FrameGrader();
	}
	
}
