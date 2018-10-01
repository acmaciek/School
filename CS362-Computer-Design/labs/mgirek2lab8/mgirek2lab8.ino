// Name: Maciej Girek
// Lab: Lab 8 - Interrupts
// Monday @ 1 pm
// Description: Write a program for the arduino that utilizes interrupts.
// Included :
//Arduino Uno
//220 ohm resistors
// buttonPin x 2
//hook-up wires
//breadboard
// LCD Display
// References :
//http://arduino.cc/en/Reference/attachInterrupt
//https://www.arduino.cc/en/Reference/Millis



#include <LiquidCrystal.h>

// initialize the library by associating any needed LCD interface pin
// with the arduino pin number it is connected to

const int button = 2;
const int button2 = 3;
unsigned long time;
unsigned long elapsedTime;
unsigned long currTime;
bool buttonPressed = false;
LiquidCrystal lcd(12, 11, 8, 7, 6, 5);

void setup() {
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
  lcd.clear();
  pinMode(button,INPUT);
  pinMode(button2,INPUT);
  attachInterrupt(digitalPinToInterrupt(button),firstButtonPressed, RISING);
  attachInterrupt(digitalPinToInterrupt(button2),secondsondButtonPressed, RISING);
}


void loop() {
  // set the cursor to column 0, line 1
  // (note: line 1 is the secondsond row, since counting begins with 0):
  lcd.setCursor(0, 0);
  time = millis() / 1000;
  currTime = time - elapsedTime;
  if(buttonPressed == false) {
    display();
    } 
    else{
      
  lcd.clear();
  lcd.print("Interrupt received!");
  lcd.setCursor(0,1);
  lcd.print("Press button 2 to continue");}
}

void display() {
  lcd.clear();
  lcd.print("We are waiting");
  lcd.setCursor(0,1);
  lcd.print(currTime);
  lcd.print(" seconds"); 
 }

 void firstButtonPressed() {
  buttonPressed = true;
 }
 void secondsondButtonPressed() {
  if(buttonPressed == true) {
    lcd.clear();
    elapsedTime = time;
    currTime = time - elapsedTime;
    buttonPressed = false; 
      }
    }



