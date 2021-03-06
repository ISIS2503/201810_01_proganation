#include <PubSubClient.h>
#include <ESP8266WiFi.h>

//DEFINES
#define TOPIC_SUBSCRIBE        "lock/casa/puerta1"
#define  TOPIC_PUBLISH          "lock/casa/puerta2"
#define  TOPIC_HEARTBEAT          "casa/lock/heartbeat"
#define SIZE_BUFFER_DATA       150                  

//VARIABLES
const char* idDevice = "ISIS2503";
boolean     stringComplete = false;
boolean     heartBeat =false;
boolean     init_flag = false;
String      inputString = "";
char        bufferData [SIZE_BUFFER_DATA];

// CLIENTE WIFI & MQTT
WiFiClient    clientWIFI;
PubSubClient  clientMQTT(clientWIFI);

// CONFIG WIFI
//const char* ssid = "isis2503";
//const char* password = "Yale2018.";
const char* ssid = "FAMILIAZALAZAR";
const char* password = "79276289";

// CONFIG MQTT
//casa 192,168,0,16
//172.24.42.97 MV
IPAddress serverMQTT (192,168,0,16);
//default 1883
// MV 8083
const uint16_t portMQTT = 1883;
// const char* usernameMQTT = "lock";
// const char* passwordMQTT = "yale";

void connectWIFI() {
  // Conectar a la red WiFi
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  

  WiFi.mode(WIFI_STA); 
  WiFi.begin(ssid, password);
  
while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println(WiFi.localIP());
}


void callback(char* topic, byte* payload, unsigned int length) {


  Serial.print("Message arrived in topic: ");
  Serial.println(topic);
  Serial.println();
  Serial.write(payload, length);
  Serial.println();
  Serial.println("-----------------------");
}

void setup() {
  Serial.begin(9600);
  inputString.reserve(100);


  connectWIFI();
  clientMQTT.setServer(serverMQTT, portMQTT);
  clientMQTT.setCallback(callback);
  
  connectMQTT();
}

void reconnectWIFI() {
  // Conectar a la red WiFi
  if(WiFi.status() != WL_CONNECTED) {
    WiFi.begin(ssid, password);
  }

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }
}

void connectMQTT(){
 
  if(WiFi.status() == WL_CONNECTED){
  while (!clientMQTT.connected()){
    Serial.println("Connecting to MQTT...");
    
    if (clientMQTT.connect("ISIS2503")) {
 
      Serial.println("connected");  
 
    } else {
 
      Serial.print("failed with state ");
      Serial.print(clientMQTT.state());
      delay(2000);
 
     }
    }
    clientMQTT.subscribe(TOPIC_SUBSCRIBE);
    Serial.println("Subscribe OK");
  }
  }
void processData() {
if(heartBeat && clientMQTT.connected()){
  if(clientMQTT.publish(TOPIC_HEARTBEAT, bufferData)){
    inputString = "";
    heartBeat=false;
  }
   init_flag = false;
}
    if (stringComplete && clientMQTT.connected()) {
       
      if(clientMQTT.publish(TOPIC_PUBLISH, bufferData)) {
        inputString = "";
        stringComplete = false;
      }
      init_flag = false;
    }
    
   clientMQTT.loop();
}

void receiveData() {
 
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read();
    if(inChar=='<'){
      inputString = "<3";
      inputString.toCharArray(bufferData, SIZE_BUFFER_DATA);
      heartBeat =true;
      
    }
    else if(inChar!='<'&& !inputString.equals("<3")){
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == '\n') {
      inputString.toCharArray(bufferData, SIZE_BUFFER_DATA);
      stringComplete = true;
      
    }
  }
}}

void loop() {
  
  
  receiveData();
  processData();
   
}
