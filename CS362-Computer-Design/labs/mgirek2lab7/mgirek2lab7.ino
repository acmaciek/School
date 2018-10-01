// Name: Maciej Girek and Manuel Adrianzen
// Lab: Lab 7 - Arduino to Arduino communication
// Monday @ 1 pm
// Description: have two arduinos communicate with eachother.
// Included :
//Arduino Uno x 2
//220 ohm resistor x 2
// buttonPin x 2
// Red LED x 2
//hook-up wires
//breadboard
// References :
//lab 2 

const int buttonPin = 7;
const int ledPin = 13;


int currButton = 0;
int prevButton = 0;
int flag = 0;

// Setup
void setup() 
{
  Serial.begin(9600);
  pinMode(buttonPin, INPUT);
  pinMode(ledPin, OUTPUT);
}


void loop() {
  currButton = digitalRead(buttonPin);
  if(currButton == HIGH)
  {  
    if(prevButton != currButton)
    {
      Serial.write(1);
      currButton = LOW;
    }
  }
  prevButton = currButton;

  if(Serial.available() > 0)
  {
    if(Serial.read()) 
    {
      flag = ~flag;
      digitalWrite(ledPin, flag);
    }   
  }
  
}
