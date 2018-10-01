/*
 * Tim Peloza
 * tpeloz2
 * Description: Creates a singleton scanner that is passed around to the other objects.
 * 
 */
import java.util.Scanner;
public class ScannerFactory {
	
	private static Scanner keyboardScanner;
	
	/*
	 * getKeyboardScanner
	 * return: A scanner object with System.in
	 */
	public static Scanner getKeyboardScanner(){
		if (keyboardScanner == null){
			keyboardScanner = new Scanner(System.in);
		}
		return keyboardScanner;
	}
	
}