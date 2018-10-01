// Name: Maciej Girek
// Lab: Lab 5 - Multiple inputs and outputs
// Monday @ 1 pm
// Description: Convert amount of light coming to photoresistor to display it with LED it there is no light coming it , all LED should be lit up and the buzzer should go off 
// Included :
//Arduino Uno
//10k ohm potentiometer
// Buzzer
// Photoresistor
//220 ohm resistor
//10k ohm resistor
//hook-up wires
//breadboard
// References :
//  https://www.arduino.cc/en/Reference/AnalogWrite
//  https://www.arduino.cc/en/Reference/Map
//  https://www.arduino.cc/en/Reference/AnalogRead

const int led1 = 9; 
const int led2 = 8; 
const int led3 = 7;
const int led4 = 6;
const int lightSensor= 0;
const int buzzer = 5;
const int potMeter = A1; 


void setup() {
pinMode (led1, OUTPUT); 
pinMode (led2, OUTPUT); 
pinMode (led3, OUTPUT); 
pinMode (led4, OUTPUT); 
Serial.begin(9600); 
pinMode (buzzer, OUTPUT); 

}

void loop(){
int output= analogRead((lightSensor)) /10; 
Serial.println(output);

if (output < 60){
digitalWrite(led1, HIGH); 
digitalWrite(led2, HIGH); 
digitalWrite(led3, HIGH); 
digitalWrite(led4, HIGH); 
delay(200);
}
if (output < 70){
digitalWrite(led1, HIGH); 
digitalWrite(led2, HIGH); 
digitalWrite(led3, HIGH); 
delay(200);
}
if (output < 80){
digitalWrite(led1, HIGH); 
digitalWrite(led2, HIGH); 
delay(200);
}

if (output < 90){
digitalWrite(led1, HIGH);
delay(200); 
}

if (output < 100){
digitalWrite(led1, LOW); 
digitalWrite(led2, LOW); 
digitalWrite(led3, LOW); 
digitalWrite(led4, LOW); 
delay(200);
}

    int val = 0; 
    val = analogRead(potMeter); 
    val = map(val, 0, 1000,0,200); 
    analogWrite(5,val);  
}
