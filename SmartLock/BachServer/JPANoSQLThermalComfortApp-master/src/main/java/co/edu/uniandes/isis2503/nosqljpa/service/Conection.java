/*
 * The MIT License
 *
 * Copyright 2018 Universidad De Los Andes - Departamento de Ingeniería de Sistemas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.isis2503.nosqljpa.service;

import co.edu.uniandes.isis2503.nosqljpa.logic.unidadRecidencialLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertasDTO;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
/**
 *
 * @author s.heim
 */
public class Conection  implements MqttCallback  {
     MqttClient client;



//public static void main(String[] args) {
//    new Conection().connect();
//}

public void connect() {
    try {
       
        System.out.println("holaaaa");
        client = new MqttClient("tcp://172.24.42.97:8083", "Sending");
        System.out.println("hola");
        client.connect();
        client.setCallback(this);
         client.subscribe("lock/casa/puerta2");
        client.subscribe("Casa/lock/heartbeat");
         System.out.println("hola2");
        MqttMessage message = new MqttMessage();
        message.setPayload("A single message from my computer fff"
                .getBytes());
        client.publish("lock/casa/puerta1", message);
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

@Override
public void connectionLost(Throwable cause) {
    // TODO Auto-generated method stub

}

@Override
public void messageArrived(String topic, MqttMessage message)
        throws Exception {
    
  
    //String mensaje=message.toString();
   // String[] lista = mensaje.split(",");
     
    //HttpClient client = HttpClients.createDefault();
    //HttpPost post = new HttpPost("http://172.24.42.60:8091/UnidadResidencial/"+Long.parseLong(lista[0])+"/Inmueble/"+Long.parseLong(lista[1]));
    //String json ="{\"id\": \" "+ Long.parseLong(lista[2])+ "\",\"idlock\": \"322\",\"tipoDeAlarma\": \""+ Long.parseLong(lista[3]) +"\"}";
    //StringEntity enitity = new StringEntity(json);
   // post.setEntity(enitity);
   // post.setHeader("Accept","application/json");
    // post.setHeader("Content-type","application/json");
//	 client.execute(post);

    
//    
//    String sincorchete = mensaje.replace("[", "");
//    sincorchete = sincorchete.replace("]", "");
 
//    AlertasDTO alerta = new AlertasDTO();
//   // alerta.setId(Long.MIN_VALUE);
//    //logica.addAlerta(lista[0], , dto)
 //  JSONObject json = new JSONObject(mensaje);
   //System.out.print(json.get("remitente"));
   //  System.out.println(mensaje +"34324324");
 System.out.println(message);   
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    // TODO Auto-generated method stub

}

    
}
