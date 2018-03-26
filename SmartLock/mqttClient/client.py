'''
Created on 3/03/2018
@author: IVAN
'''

import paho.mqtt.client as mqttClient
import time
import requests 
import json
from _ast import If


mensaje =""
base_url="172.24.42.97:8080"
final_url="http://172.24.42.97:8080/mail/newmail"

def on_connect(client, userdata, flags, rc):
 
    if rc == 0:
 
        print("Connected to broker")
 
        global Connected                #Use global variable
        Connected = True                #Signal connection 
 
    else:
 
        print("Connection failed")
 
def on_message(client, userdata, message):
    payload =message.payload.decode()
    data =payload.split(",")
    #3-6
    mensaje="";
    if(len(data)>5):
      print(data[3])
      print(data[4])
      print(data[5])
      print(data[6])
      str = data[6]
      str=str.replace("\r\n", "")
      mensaje =  {"remitente": data[4],
                 "correos": [data[5],str],
                 "asunto":"alerta",
                 "body":"El sistema ha enviado la siguiente alarma: " +data[3]
        }
                    
      print(mensaje)         

      print ("Message received: "  + payload)
      obj = json.dumps(mensaje) 
      obj=json.loads(obj)
      response = requests.post(final_url,json=obj)
      print(response.text) #TEXT/HTML
      print(response.status_code, response.reason) #HTTP
 
Connected = False   #global variable for the state of the connection
 
broker_address= "192.168.0.17"  #Broker address
port = 1883                         #Broker port
user =   ""                  #Connection username
password = ""           #Connection password
 
client = mqttClient.Client("Python")               #create new instance
client.username_pw_set(user, password=password)    #set username and password
client.on_connect= on_connect                      #attach function to callback
client.on_message= on_message                      #attach function to callback
 
client.connect(broker_address, port=port)          #connect to broker
 
client.loop_start()        #start the loop
 
while Connected != True:    #Wait for connection
    time.sleep(0.1)
 
client.subscribe("lock/casa/puerta2")
 
try:
    while True:
        time.sleep(1)
 
except KeyboardInterrupt:
    print ("exiting")
    client.disconnect()
    client.loop_stop()