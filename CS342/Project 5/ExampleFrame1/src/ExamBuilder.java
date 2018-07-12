import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.io.*;

public class ExamBuilder {
	public static JFrame frame;
	
	
	public static void main(String [] args) throws IOException {
		
		
		
			
		frame=new JFrame();
		
		Exam testExam = new Exam("ExamS");
		
		String OS="Select 'quit' to quit, 'addq' to add questions, 'load' to load "
				+ "an exam\n"
				+ "'reorder' to reorder the questions, 'save' to save the exam to file\n"
				+ "";
		
		String[] OSBut={"Load","Quit","Reorder","Save","Print","AddQ","Remove", ":)"};
		
		//=======================================================================================
		
		
		int choice=6;

		while(!OSBut[choice].equals("Quit")){
			
			
	       choice=JOptionPane.showOptionDialog(frame,OS, "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,OSBut,"Option 1");
 			
			//===================================================================================================
			if(OSBut[choice].equals("Load")) {				
 				
 			  	File file=new File(JOptionPane.showInputDialog(frame, "Enter name of text file containing the exam: "));
				
				Scanner scn = new Scanner(file);
				
				testExam = new Exam(scn);		
				
				testExam.reorderQuestions();
				
				for(int i=0; i < 4; i++) {
					testExam.reorderMCAnswers(i);
				}				 
								
			}
			
			//===================================================================================================

			if(OSBut[choice].equals("Reorder")) {
				
				testExam.reorderQuestions();
				
				for(int i=0; i < 4; i++) {
					testExam.reorderMCAnswers(i);
				}
			}
			
			//===================================================================================================

			
			if(OSBut[choice].equals("Save")) {
				
				File savedFileExam = new File("ExamS.txt");
				PrintWriter write = new PrintWriter(savedFileExam);
				testExam.save(write);
				write.close();

			}
			
			
			if(OSBut[choice].equals("Print")) {
				
				String ExamString="";
				for(int i=0;i<testExam.getNumberOfQuestions();i++) {
					ExamString= ExamString+Integer.toString(i+1)+") "+testExam.getQuestionText(i)+"\n";
				}
 				
				JOptionPane.showMessageDialog(new JFrame(),ExamString);
			}
			
			
			if(OSBut[choice].equals("AddQ")) {
				
				String qtype=JOptionPane.showInputDialog("What type of question would you like to add, MCSA,MCMA, SA or NUM?");
			
				
				if(qtype.equals("SA")) {
					
					String ques=JOptionPane.showInputDialog("Enter SA Question");	 								
					String ptsS = JOptionPane.showInputDialog("How many points is it worth");					
					int pts=Integer.parseInt(ptsS);					
					SAQuestion q1=new SAQuestion(ques, pts);						
 					String Ans = JOptionPane.showInputDialog("What is the expected answer\n");					
			    	q1.getNewAnswer(Ans);
					testExam.addQuestion(q1);  

				}
				
				
				if(qtype.equals("MCSA")) {
					
					String ques=JOptionPane.showInputDialog("Enter MCSA Question");	 								
					String ptsS = JOptionPane.showInputDialog("How many points is it worth");					
					int pts=Integer.parseInt(ptsS);	
				
					MCSAQuestion q1=new MCSAQuestion(ques, pts);	 
					
					for(int i=0;i<5;i++) {									
 					 				 		
						String ansStr2 = JOptionPane.showInputDialog("Type Answer");													
						System.out.println("How many points is it worth");						
						String cif2=JOptionPane.showInputDialog("How many points is it worth");
						int cif=Integer.parseInt(cif2);
						MCSAAnswer Ans1=new MCSAAnswer(ansStr2,  cif);
						
						q1.addAnswer(Ans1);
					}
					
					testExam.addQuestion(q1); 
									
				}
				
				
				if(qtype.equals("MCMA")) {
					
					String ques=JOptionPane.showInputDialog("Enter MCMA Question");	 								
					String ptsS = JOptionPane.showInputDialog("How many points is it worth");					
					int pts=Integer.parseInt(ptsS);			
				
					MCMAQuestion q1=new MCMAQuestion(ques, pts, pts);	 
					
					for(int i=0;i<5;i++) {										
						String ansStr2 = JOptionPane.showInputDialog("Type Answer");													
						System.out.println("How many points is it worth");						
						String cif2=JOptionPane.showInputDialog("How many points is it worth");
						int cif=Integer.parseInt(cif2);
						
						MCMAAnswer Ans1=new MCMAAnswer(ansStr2,  cif);
						
						q1.addAnswer(Ans1);
					}
					
					testExam.addQuestion(q1); 
									
				}	
				
				if(qtype.equals("NUM")) {
					
					String ques=JOptionPane.showInputDialog("Enter NUM Question");	 								
					String ptsS = JOptionPane.showInputDialog("How many points is it worth");					
					int pts=Integer.parseInt(ptsS);			
				
					MCMAQuestion q1=new MCMAQuestion(ques, pts, pts);	 
					
					for(int i=0;i<5;i++) {										
						String ansStr2 = JOptionPane.showInputDialog("Type Answer");													
						System.out.println("How many points is it worth");						
						String cif2=JOptionPane.showInputDialog("How many points is it worth");
						int cif=Integer.parseInt(cif2);
						
						MCMAAnswer Ans1=new MCMAAnswer(ansStr2,  cif);
						
						q1.addAnswer(Ans1);
					}
					
					testExam.addQuestion(q1); 
									
				}		
		 
				
			}
			
			//=======================================================================================================
			
			
			

			if(OSBut[choice].equals("Remove")) {
				
 				
				String rmq2 = JOptionPane.showInputDialog("Which question would you like to remove? Select number 1,2,3..");				
				
				int rmq=Integer.parseInt(rmq2);
				testExam.remove(rmq-1);
			 
			}
					
			
			
			//Test function remove latter
			if(OSBut[choice].equals("Take")) {
				
				testExam.takeExam();
				testExam.reportQuestionValues();
			}						
			
			
	    
 		}
		

	}	
	
	
	
}