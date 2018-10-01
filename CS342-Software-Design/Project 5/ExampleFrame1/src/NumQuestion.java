import java.util.Scanner;
import java.io.PrintWriter;

public class NumQuestion extends Question{
	
		double tolerance;
		public NumQuestion(String text, double maxValue, double tl){
		super(text,maxValue);
		tolerance=tl;

	}

	public NumQuestion(Scanner scanner){
		super(scanner);
        rightAnswer = new NumAnswer(scanner.nextDouble());
        scanner.nextLine();
        tolerance= scanner.nextDouble();
        scanner.nextLine();
	}

	public Answer getNewAnswer(){
		return null;
	}

	public void getAnswerFromStudent(){
		
		Scanner scan = ScannerFactory.getKeyboardScanner();
		studentAnswer = new NumAnswer(scan.nextDouble());

	}
	
	public void getAnswerFromStudent(String a){
		

		studentAnswer = new NumAnswer(Integer.parseInt(a));

	}

	public double getValue(){
		return 0;
	}



   public void saveStudentAnswer(PrintWriter pw)
	   {
        if(studentAnswer == null)
        {
            return;
        }
        
        else {        	
        	pw.println("NumAnswer");
        	studentAnswer.save(pw);
        }
    }

    public void restoreStudentAnswers(Scanner scanner)
    {
        studentAnswer = new SAAnswer(scanner);
    }
    
	public void save(PrintWriter pw){
		
		pw.println("NUMQuestion");
		pw.println(this.maxValue);
		pw.println(this.text);

	}

    
    //Leave here
	@Override
	void print() {
		// TODO Auto-generated method stub
		
	}



}