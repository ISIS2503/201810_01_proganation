'''
Created on 3/03/2018

@author: IVAN
'''

import paho.mqtt.client as mqttClient
import time
import requests 


mensaje =""
base_url="172.24.42.60:8080"
final_url="172.24.42.60:8080/floors"

def on_connect(client, userdata, flags, rc):
 
    if rc == 0:
 
        print("Connected to broker")
 
        global Connected                #Use global variable
        Connected = True                #Signal connection 
 
    else:
 
        print("Connection failed")
 
def on_message(client, userdata, message):
    mensaje =message.payload.decode()
    print ("Message received: "  + mensaje)
    
    

payload = mensaje
response = requests.post(final_url, data=payload)



print(response.text) #TEXT/HTML
print(response.status_code, response.reason) #HTTP
 
Connected = False   #global variable for the state of the connection
 
broker_address= "172.24.42.97"  #Broker address
port = 8083                         #Broker port
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
 
client.subscribe("lock/casa/puerta1")
 
try:
    while True:
        time.sleep(1)
 
except KeyboardInterrupt:
    print ("exiting")
    client.disconnect()
    client.loop_stop()