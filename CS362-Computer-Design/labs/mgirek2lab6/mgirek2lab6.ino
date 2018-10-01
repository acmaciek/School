
// Name: Maciej Girek
// Lab: Lab 6 - Communication
// Monday @ 1 pm
// Description: take in user input and have your arduino display the date and time 
// Included :
//Arduino Uno
//LCD Display
//10k ohm potentiometer
//220 ohm resistor
//hook-up wires
//breadboard
// References :
//http://arduino.cc/en/Reference/Serial#.UwYyzfldV8E
//http://arduino.cc/en/Serial/Available#.UwYy2PldV8E
//http://arduino.cc/en/Serial/ReadBytesUntil#.UwYy6_ldV8E

#include <LiquidCrystal.h>
#include <Time.h>

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
int seconds = 0;
int minutes = 0;
int hours = 0;
int month = 0; 
int day = 0; 
int year= 0; 

void setup(){

  Serial.begin(9600);
  lcd.begin(16, 2);
 
  Serial.println("Enter the following format");
  Serial.println("hour minute second month day year");
  while(Serial.available() == 0){}
  hours = Serial.parseInt();  
  Serial.println(hours);

  if(hours > 23){ //ERROR CHECKING
    lcd.print("Wrong hour val");
    exit(0); 
  }
   if(hours < 0){
    lcd.print("Wrong hour val");
    exit(0);  
  }

  while(Serial.available() == 0){}
  minutes = Serial.parseInt();  
  Serial.println(minutes);

  if(minutes > 59){ //ERROR CHECKING
    lcd.print("Wrong minut val");
    exit(0);
  }
   if(minutes < 0){
    lcd.print("Wrong minut val");
    exit(0);  
  }



  while(Serial.available() == 0){}
  seconds = Serial.parseInt();  
  Serial.println(seconds);

  if(seconds > 59){ //ERROR CHECKING
    lcd.print("Wrong second val");
    exit(0); 
  }
   if(seconds < 0){
    lcd.print("Wrong second val");
    exit(0);  
  }


  while(Serial.available() == 0){}
  month = Serial.parseInt();  
  Serial.println(month);

  if(month > 12){  //ERROR CHECKING
    lcd.print("Wrong month val");
    exit(0);  
  }
   if(month <= 0){
    lcd.print("Wrong month val");
    exit(0); 
  }

  while(Serial.available() == 0){}
  day = Serial.parseInt();  

  Serial.println(day);
  //ERROR CHECKING
  if(month == 4 || month == 6 || month == 9 || month == 11 &&  day > 30) { 
    lcd.print("Wrong day value");
    exit(0);
    }
  else if(month == 2 && day > 28) {
    lcd.print("Wrong day value");
    exit(0);
    }
  else if(day > 31){
    lcd.print("Wrong day value");
    exit(0);  
  }
   else if(day <= 0){
    lcd.print("Wrong day value");
    exit(0); 
  }

while(Serial.available() == 0){}
  year = Serial.parseInt();  
  Serial.println(year);
   if(year <= 0){
    lcd.print("Wrong year value");
    exit(0); 
  }
}

void loop(){   


  lcd.setCursor(1,0);
  //PRINT HOURS
  if(hours < 10){
    lcd.print('0');
    lcd.print(hours);
  }
  else{
    lcd.print(hours);
  }
  //PRINT MINUTES
  lcd.print(":");
  
  if(minutes < 10){
    lcd.print('0');
    lcd.print(minutes);
  }
  else
  {
    lcd.print(minutes);
  }
  //PRINT SECONDS
  lcd.print(":"); 
  
  if(seconds < 10){
    lcd.print('0');
    lcd.print(seconds);
  }
  else
  {
    lcd.print(seconds);
  }

  //PRINT DATE
  lcd.setCursor(1,2);
  
  // PRINT MONTH
  if(month < 10){
    lcd.print('0');
    lcd.print(month);
  }
  else
    lcd.print(month);
    
  // PRINT DAY
  lcd.print(":");
  if(day < 10){
    lcd.print('0');
    lcd.print(day);
  }
  else
  {
    lcd.print(day);
  // PRINT YEAR
  }
  lcd.print(":"); 
    lcd.print(year);
  
 
  
  if(seconds == 60){ //IF SECONDS == 60 INCREMENT MINUTES
    minutes++;
    seconds = 0;
  }
  if(minutes == 60){ //IF MINUTES == 60 INCREMENT HOURS
    hours++;
    minutes = 0;
  }
  
  if(hours == 24){ //IF HOURS == 24 INCREMENT DAY
    hours = 0;
    day++;
  }
  if(day  > 31){ // IF DAY IS GREATER THAN 31 INCREMENT MONTH
    month++;
    day = 1;
  }
  if(month  > 12){ //IF MONTH GREATER THAN 12 INCREMENT YEAR
    year++;
    month = 1;
    day = 1;
  }
  seconds++; 
  delay(1000);  

}//end loop
