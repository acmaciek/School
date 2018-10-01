// Name: Maciej Girek
// Lab: Lab 2 - Input and Output // Monday @ 1 pm
// Description: Get 3 LEDs to blink at different times depending on button pressed, the LEDs should represent binary number counter
// Included 3 LEDs : red,green, and blue  ,respectively connected to pin 2,3, and 4
// References : https://www.arduino.cc/en/Tutorial/Button 
//  http://www.ladyada.net/learn/arduino/lesson5.html

int ledPin[] = {2,3,4}; //array of pin numbers where LEDs are
int buttonUP = 8; //button counting up at pin # 8
int buttonDOWN = 9;//button counting up at pin # 9
byte counter = 0; //initialize byte counter to 0

void setup()
{
  pinMode(buttonUP, INPUT);
  pinMode(buttonDOWN, INPUT);
  for (int i = 0; i < 3; i++) {
    pinMode(ledPin[i], OUTPUT); // map the LEDs 
  }
}

void loop()
{
  if (digitalRead(buttonUP) == HIGH) { //if the buttonUP is pressed
    counter++;// Increment the counter.
    delay(200);
    for (int i = 0; i < 3; i++) //loop through the LEDs
  {
    if (bitRead(counter, i) == 1) // if the bit is on at given pin
    {
      digitalWrite(ledPin[i], HIGH); //turn on the led at that pin position
    }
    else
    {
      digitalWrite(ledPin[i], LOW); //else turn the led off
    }
  }
  }
  if (digitalRead(buttonDOWN) == HIGH) { //if the buttonDown is presed
    counter--; //decrement the counter
    delay(200);
   for (int i = 0; i < 3; i++)
  {
    if (bitRead(counter, i) == 1) //if the bit is on
    {
      digitalWrite(ledPin[i], HIGH); //turn on the led at that position
    } 
    else
    {
      digitalWrite(ledPin[i], LOW); //else turn it off
    }
  }
 }
  if (counter >= 8) { //if binary counter is 8 or greater then reset
    delay(1000);
    counter = 0; //reset the counter
  }
}

