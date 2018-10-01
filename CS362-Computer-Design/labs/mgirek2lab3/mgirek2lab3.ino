// Name: Maciej Girek
// Lab: Lab 3 - Scrolling output
// Monday @ 1 pm
// Description: Display name and favorite quote on the 16x2 display, name on first line and quote on the second line which will scroll. 
// Included :
//Arduino Uno
//LCD Screen 
//10k ohm potentiometer
//220 ohm resistor
//hook-up wires
//breadboard
// References : https://www.arduino.cc/en/Tutorial/LiquidCrystalScroll
//  http://www.ladyada.net/learn/arduino/lesson5.html

#include <LiquidCrystal.h>


LiquidCrystal lcd(12, 11, 5, 4, 3, 2); //initialize LCD with pins 
String quote = "If you tell the truth, you don't have to remember anything ~Mark Twain";
int start = 0;
int sstop = 0;
int endOfTheLine = 16;

// most of the part is pretty basic
void setup() {
  Serial.begin(9600);
  lcd.begin(16,2); //initialize LCD size
}

void loop() {
  lcd.setCursor(0, 0); 
  lcd.print("Maciej Girek"); //print in the first line of the LCD
  lcd.setCursor(0, 1); //set cursor on the second line
  
  lcd.print(quote.substring(start,sstop)); //print the substring from start to end index, we initialize those values so we can manipulate them and perfom scrolling
    
    
  delay(250);
  lcd.clear();
  if(start == 0 && endOfTheLine > 0){ 
    endOfTheLine--;
    sstop++; //get the length of the string
    
  } else if (start == sstop){ //at this point the start index reached the end index and we have to perform scrolling
    start = 0;  //we reset the start and end of the string
    sstop = 0; 
    endOfTheLine = 16;
  }
 else if (sstop == quote.length() && endOfTheLine == 0) { //if we reached the end 
    start++; //move the text if the text reached the end
  }
else { //at this point we go over the 16 characters width of the LCD so we have to scroll the text
    start++; //move starting index 
    sstop++; //move end index
  }
  }

