#include <Keypad.h>
#include <TimerOne.h>
#include <string.h>


//
String id ="1";
String recidencia ="1";
String apto="701";
String alarma="";
// comunicación arduino/ESP8622
#define SIZE_BUFFER_DATA       150
boolean     stringComplete = false;
String      inputString = "";
char        bufferData [SIZE_BUFFER_DATA];
//Minimum voltage required for an alert
const double MIN_VOLTAGE = 1.2;

//Battery indicator
const int BATTERY_LED = A1;

//Battery measure pin
const int BATTERY_PIN = A3;

//Current battery charge
double batteryCharge;


//Specified password
const String KEY = "1234";
const String KEY2 = "4321";
const String KEY3 ="1243";
// MAILS
String remitente ="lock@yale-services.com";
String correo1="abc@gmail.com";
String correo2="def@yahoo.com";
String correo3="ghi@hotmail.com";
//COntatenar mensaje
String coma = ",";
//alertas
String movimiento = "movimiento";
String fallo ="exceso de intentos de clave";
String bateria ="bateria baja";
String puertaTiempo = "Puerta abierta demasiado tiempo";
String puertaAbierta ="puerta abierta";


//Keypad rows
const byte ROWS = 4; 

//Keypad columns
const byte COLS = 3;

//Maximum number of attempts allowed
const byte maxAttempts = 3;

//Keypad mapping matrix
char hexaKeys[ROWS][COLS] = {
  {
    '1', '2', '3'
  }
  ,
  {
    '4', '5', '6'
  }
  ,
  {
    '7', '8', '9'
  }
  ,
  {
    '*', '0', '#'
  }
};

//Keypad row pins definition
byte rowPins[ROWS] = {
  9, 8, 7, 6
}; 

//Keypad column pins definition
byte colPins[COLS] = {
  5, 4, 3
};

//Keypad library initialization
Keypad customKeypad = Keypad(makeKeymap(hexaKeys), rowPins, colPins, ROWS, COLS); 

//Current key variable
String currentKey;
//Door state
boolean open;
//Number of current attempts
byte attempts;
//If the number of current attempts exceeds the maximum allowed
boolean block;
/*
 * PIR sensor tester
 */
 
int ledPin = A0;                // choose the pin for the LED
int inputPin = 2;               // choose the input pin (for PIR sensor)
int pirState = LOW;             // we start, assuming no motion detected
int val = 0;                    // variable for reading the pin status

/*
 * Button
 */
//Button pin
const int CONTACT_PIN = 11;

//R LED pin
const int R_LED_PIN = 13;

//G LED pin
const int G_LED_PIN = 12;

//B LED pin
const int B_LED_PIN = 10;



//Attribute that defines the button state
boolean buttonState;

//Current time when the button is tapped
long currTime;

 
void setup() {

  // Iniciamos el monitor serie
  Serial.begin(9600);

  // Ouput pin definition for BATTERY_LED
  pinMode(BATTERY_LED,OUTPUT);

  //Input pin definition for battery measure
  pinMode(BATTERY_PIN,INPUT);
  
  pinMode(ledPin, OUTPUT);      // declare LED as output
  pinMode(inputPin, INPUT);     // declare sensor as input
  
    
  buttonState = false;
  
  pinMode(R_LED_PIN, OUTPUT);
  pinMode(G_LED_PIN, OUTPUT);
  pinMode(B_LED_PIN, OUTPUT);
  pinMode(CONTACT_PIN,INPUT);
  
  setColor(0, 255, 0);

  currentKey = "";
  open = false;
  attempts = 0;
  block = false;
 Serial.begin(9600);
  
}
void loop(){
  motion();
  contact();
  checkBattery();
  password();
  receiveData();
}


void receiveData() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read();
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == '\n') {
      inputString.toCharArray(bufferData, SIZE_BUFFER_DATA);
      stringComplete = true;
      //Serial.println(inputString);
    }
  }
}

 void motion(){
   val = digitalRead(inputPin);  // read input value
  if (val == HIGH) {            // check if the input is HIGH
    digitalWrite(ledPin, HIGH);  // turn LED ON
    if (pirState == LOW) {
      // we have just turned on
      Serial.println("Motion detected!");
    
   //Serial.println(recidencia+coma+apto+coma+id+coma+movimiento+coma+remitente+coma+correo1+coma+correo2);
      // We only want to print on the output change, not state
      pirState = HIGH;
    }
  } else {
    digitalWrite(ledPin, LOW); // turn LED OFF
    if (pirState == HIGH){
      // we have just turned of
      //Serial.println("Motion ended!"+"mm");
      // We only want to print on the output change, not state
      pirState = LOW;
    }
  }
 }

 void contact(){
  //Button input read and processing 
  if(!buttonState) {
    if(digitalRead(CONTACT_PIN)) {
      currTime = millis();
      buttonState = true;
      setColor(0, 0, 255); 
      open = true;
      attempts = 0;
        
    }
  }
  else {
    if(digitalRead(CONTACT_PIN)) {
      if((millis()-currTime)>=30000) {
        setColor(255, 0, 0);
        Serial.println(recidencia+coma+apto+coma+id+coma+puertaTiempo+coma+remitente+coma+correo1+coma+correo2);
        
         delay(3000);
      }
    }else{
      setColor(0, 255, 0);
      open = false;
      buttonState = false;
    }
  }
  
  delay(100);
 }

 void checkBattery(){
  
  //Value conversion from digital to voltage
  batteryCharge = (analogRead(BATTERY_PIN)*5.4)/1024;
 
  
  //Measured value comparison with min voltage required
  if(batteryCharge<=MIN_VOLTAGE) {
    digitalWrite(BATTERY_LED,HIGH);
    //Serial.println("LOW BATTERY");
    //Serial.println(recidencia+coma+apto+coma+id+coma+bateria+coma+remitente+coma+correo1+coma+correo2);
  }
  else {
    digitalWrite(BATTERY_LED,LOW);
  }
 }

 void password(){
  char customKey;

  if(!block) {
    //Selected key parsed;
    customKey = customKeypad.getKey();
  }
  else {
    Serial.println(recidencia+coma+apto+coma+id+coma+fallo+coma+remitente+coma+correo1+coma+correo2);
    setColor(255, 0, 0);
    delay(10000);
    attempts=0;
    block=false;
    setColor(0, 255, 0);
    
  }

  //Verification of input and appended value
  if (customKey) {  
    currentKey+=String(customKey);
    Serial.println(currentKey);
  }

  //If the current key contains '*' and door is open
  if(open && currentKey.endsWith("*")) {
    open = false;
   // Serial.println("Door closed");
    setColor(0, 255, 0);
    currentKey = "";
  }
  //If the current key contains '#' reset attempt
  if(currentKey.endsWith("#")&&currentKey.length()<=KEY.length()) {
    currentKey = "";
   // Serial.println("Attempt deleted");
  }

  //If current key matches the key length
  if (currentKey.length()== KEY.length()) {
    if(currentKey == KEY||currentKey == KEY2||currentKey == KEY3) {

      
      setColor(0, 0, 255); 
      
      open = true;
      //Serial.println("Door opened!!");
      attempts = 0;
      currTime = millis();
      if(open=true) {
        
        while(open=true){
      if((millis()-currTime)>=10000) {
        setColor(255, 0, 0);
       // Serial.println("door open too much time");
        Serial.println(recidencia+coma+apto+coma+id+coma+puertaTiempo+coma+remitente+coma+correo1+coma+correo2);
         delay(3000);
        
    customKey = customKeypad.getKey();

  
  //Verification of input and appended value
  if (customKey) {  
    currentKey+=String(customKey);
    Serial.println(currentKey);
  }

  //If the current key contains '*' and door is open
  if(open && currentKey.endsWith("*")) {
    open = false;
    //Serial.println("Door closed");
    setColor(0, 255, 0);
    currentKey = "";
    break;
  }
      }
      }
      }
    }
    else {
      setColor(255, 0, 0); 
      delay(1000);
      attempts++;
      currentKey = "";
     // Serial.println("Number of attempts: "+String(attempts));
       setColor(0, 255, 0); 
    }
  }else if(currentKey.length()> KEY.length()){
   // Serial.println("Door opened!!");
  }
  if(attempts>=maxAttempts) {
    block = true;
  }

  delay(100);
  
 }



//Method that outputs the RGB specified color
void setColor(int redValue, int greenValue, int blueValue) {
  analogWrite(R_LED_PIN, redValue);
  analogWrite(G_LED_PIN, greenValue);
  analogWrite(B_LED_PIN, blueValue);
}


