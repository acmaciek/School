import java.util.Scanner;
import java.io.PrintWriter;

public class NumAnswer extends Answer
{
    protected double value;

    public NumAnswer(double value)
    {
        this.value = value;
    }

    public NumAnswer(Scanner scanner){
        value=scanner.nextDouble();
        scanner.nextLine();
    }

    public void print()
    {
        System.out.println(value);
    }

    public double getCredit(Answer rightAnswer)
    {
         return 1.0;
    }


    public void save(PrintWriter pw)
    {
        pw.println(value);
    }

 
 
}