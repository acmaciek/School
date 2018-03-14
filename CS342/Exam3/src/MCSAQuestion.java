import java.io.PrintWriter;
import java.util.Scanner;

class MCSAQuestion extends MCQuestion{

	public MCSAQuestion(String text, double maxValue) {
		super(text, maxValue);
	}
	
	//===========================================================================================
	
	public MCSAQuestion(Scanner qf) {
		
		super(qf);
		
		int numAs = Integer.parseInt(qf.nextLine());
		for(int i = 0; i < numAs; i++) {
			double CiSCnS = qf.nextDouble();
			String Answer = qf.nextLine().trim();
			
			MCSAAnswer nAns=new MCSAAnswer(Answer,CiSCnS);
			
			answers.add(nAns);
		}
	}
	
	//===========================================================================================
 
	
	//Creates a new answer object
	 	public Answer getNewAnswer() {
		
			System.out.println("Please enter your answer.\n");
			
			Scanner Utext = ScannerFactory.getKeyboardScanner();
			String ansU = Utext.nextLine();
			
			System.out.println("Enter the value of the answer.\n");
			
			Scanner cre = new Scanner(System.in);
			double credit = cre.nextDouble();
			
			MCSAAnswer ans = new MCSAAnswer(ansU, credit);
			answers.add(ans);
			
			Utext.close();
			cre.close();
			
			return ans;
		}

		//===========================================================================================
	
	
		public Answer getNewAnswer(String text, double creditIfSelected) {
			
			MCSAAnswer ans = new MCSAAnswer(text, creditIfSelected);
			answers.add(ans);
			return ans;
		}

		
		//===========================================================================================

	
	//get the Students answer choice
	public void getAnswerFromStudent() {
				
		Scanner scan = ScannerFactory.getKeyboardScanner();
		
		char choice = scan.nextLine().trim().charAt(0); 
		
		int index = (int)choice - 97;	
			
		this.studentAnswer = answers.get(index); 
		
		if(this.studentAnswer instanceof MCSAAnswer) {
			MCSAAnswer tmp = (MCSAAnswer) this.studentAnswer;
		//	tmp.setSelected(true);
			this.studentAnswer = tmp;
		}
	}
	
	//===========================================================================================
	
	public double getValue() {
		 
		try {
			return super.getValue((MCSAAnswer)studentAnswer);  
		}catch (Exception e){
			return 0.0;
		}
	}
	//===========================================================================================
	
 	public void setRightAnswer(Answer ans) {
		this.rightAnswer = ans;
	}
 	
	//===========================================================================================

	
	public void save(PrintWriter file) {
		file.println("MCSAQuestion");
		super.save(file);
		file.println();
	}
	
	//===========================================================================================

	
	public void print() {
		System.out.println(this.text);
		for(int i = 0; i < answers.size(); i++) {
			System.out.print("  " +(char)(i+'a')+".");  
			this.answers.get(i).print();  
		}
	}
}