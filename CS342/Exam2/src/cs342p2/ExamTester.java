
public class ExamTester {
	public static void main(String args[]) {
		
		System.out.println("Maciej Girek\nnetid: mgirek2\n");
		Exam ex1 = new Exam("CS 342 Exam\n");

		MCSAQuestion q1=new MCSAQuestion("What is the biggest city in the world ?\n", 1);		
		q1.getNewAnswer("New York", 0);
 		q1.getNewAnswer("Sao Paulo", 0);
 		q1.getNewAnswer("Seoul", 0); 	
 		q1.getNewAnswer("Berlin", 0); 
 		q1.getNewAnswer("Tokyo", 0); 
 		MCSAAnswer ans=new MCSAAnswer("Tokyo", 1);
 		q1.setRightAnswer(ans);

 		
 		MCSAQuestion q2=new MCSAQuestion("How many planets are there ?\n", 1);		
		q2.getNewAnswer("7", 0);
 		q2.getNewAnswer("8", 1);
 		q2.getNewAnswer("9", 0);
 		q2.getNewAnswer("10", 0);
 		q2.getNewAnswer("11", 0);
 		
 		MCSAAnswer ans2=new MCSAAnswer("8", 1);
 		q2.setRightAnswer(ans2);
 		
  
 		SAQuestion q3=new SAQuestion("What is the biggest planet ?\n", 1);
 		
 		SAAnswer ans3=new SAAnswer("Jupiter");
 		
 		q3.setRightAnswer(ans3);
 		
 		
 		SAQuestion q4=new SAQuestion("How many continents are there?\n", 1);
 		
 		SAAnswer ans4=new SAAnswer("7");
 		
 		q4.setRightAnswer(ans4);
 		
 		ex1.addQuestion(q1);
 		ex1.addQuestion(q2);
 	 	ex1.addQuestion(q3);
 	 	ex1.addQuestion(q4);
 		ex1.print();
 		System.out.println("Reordering questions and Answers \n");
		
 		ex1.reorderQuestions();
		ex1.reorderMCAnswers(-1);
		ex1.print();

		ex1.getAnswerFromStudent(0);
		ex1.getAnswerFromStudent(1);
		ex1.getAnswerFromStudent(2);
		ex1.getAnswerFromStudent(3);
		
 		

		System.out.println("Grading exam...\n");
		System.out.printf("Score: %f\n", ex1.getValue());
	}
}
