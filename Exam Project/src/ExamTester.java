//Maciej Girek
//mgirek2
public class ExamTester {

	public static void main(String[] args) {
		
		Exam ex1 = new Exam("Exam 1"); //Create new Exam object
		
		Question q1 = new Question("How many continents are there ?");
		Question q2 = new Question("Who is the president of USA ?");
		Question q3 = new Question("What is the biggest ocean in the world ?");
		
		Answer a1 = new Answer("3");
		Answer b1 = new Answer("4");
		Answer c1 = new Answer("5");
		Answer d1 = new Answer("6");
		Answer e1 = new Answer("7");
		
		a1.setValue(0, 1.0);
		b1.setValue(0, 1.0);
		c1.setValue(0, 1.0);
		d1.setValue(0, 1.0);
		e1.setValue(10.0, 0);
		
		Answer a2 = new Answer("Barack Obama");
		Answer b2 = new Answer("Donald Trump");
		Answer c2 = new Answer("George Washington");
		Answer d2 = new Answer("Kim Kardashian");
		Answer e2 = new Answer("Richard Nixon ");
		
		a2.setValue(0, 1.0);
		b2.setValue(10.0,0);
		c2.setValue(0, 1.0);
		d2.setValue(0, 1.0);
		e2.setValue(0, 1.0);
		
		Answer a3 = new Answer("Atlantic Ocean");
		Answer b3 = new Answer("Indian Ocean");
		Answer c3 = new Answer("Arctic Ocean");
		Answer d3 = new Answer("Pacific Ocean");
		Answer e3 = new Answer("Southern Ocean");
		
		a3.setValue(0, 1.0);
		b3.setValue(0,1.0);
		c3.setValue(0, 1.0);
		d3.setValue(10.0, 0);
		e3.setValue(0, 1.0);
		
		q1.addAnswer(a1);
		q1.addAnswer(b1);
		q1.addAnswer(c1);
		q1.addAnswer(d1);
		q1.addAnswer(e1);
		
		q2.addAnswer(a2);
		q2.addAnswer(b2);
		q2.addAnswer(c2);
		q2.addAnswer(d2);
		q2.addAnswer(e2);
		
		q3.addAnswer(a3);
		q3.addAnswer(b3);
		q3.addAnswer(c3);
		q3.addAnswer(d3);
		q3.addAnswer(e3);
		
		ex1.addQuestion(q1);
		ex1.addQuestion(q2);
		ex1.addQuestion(q3);
		
		a1.setSelected(true);
		b2.setSelected(true);
		d3.setSelected(true);
		
		ex1.print();
		System.out.println("Score : 42.0/" + ex1.getValue());
		
	}

}
