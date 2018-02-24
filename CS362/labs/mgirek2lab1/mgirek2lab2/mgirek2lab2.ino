// Name: Maciej Girek
// Lab: Monday @ 1 pm
// Description: Get 3 LEDs to blink at different times
// Included 2 LEDs : red and green ,respectively connected to pic 8 and 12, 2 resistors 
// References : http://arduino.cc/en/Tutorial/Blink
void setup() {
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(12, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(13, OUTPUT);
}

// the loop function runs over and over again forever
void loop() {
  digitalWrite(8, LOW);
  digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
  digitalWrite(12,LOW);
  delay(1000);                       // wait for a second
  digitalWrite(13, LOW);
  digitalWrite(12, HIGH);
  digitalWrite(8, LOW);    // turn the LED off by making the voltage LOW
  delay(1000);                       // wait for a second
  digitalWrite(13, LOW);
  digitalWrite(12, LOW);
  digitalWrite(8, HIGH);
  delay(1000);
}
