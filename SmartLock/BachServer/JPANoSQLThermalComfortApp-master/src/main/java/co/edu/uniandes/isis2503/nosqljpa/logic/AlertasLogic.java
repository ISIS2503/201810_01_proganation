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
package co.edu.uniandes.isis2503.nosqljpa.logic;

import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertasDTO;

import co.edu.uniandes.isis2503.nosqljpa.model.entity.AlertasEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.DispositivoEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.InmuebleEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.unidadRecidencialEntity;


import co.edu.uniandes.isis2503.nosqljpa.persistence.unidadRecidencialPersistance;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author js.palacios437
 */
public class AlertasLogic {
    
    private final unidadRecidencialPersistance persistance;
   private final unidadRecidencialLogic Ulogica;
    
    public AlertasLogic()
    {
        this.persistance = new unidadRecidencialPersistance();
        this.Ulogica = new unidadRecidencialLogic();
    }
     
     public List<AlertasDTO> getAll()
     {
         return null;
     }
     
     public List<AlertasDTO> getAllUnidad(Long id)
     {
         return null;
     }
      public List<AlertasDTO> getAllInmueble(Long id,Long id2)
     {
         return null;
     }
       
     public List<AlertasDTO> getDispositivo(Long id,Long id2,Long id3)
     {
        unidadRecidencialEntity result = persistance.find(id);
             
              InmuebleEntity entity= null;
                InmuebleEntity inmu = null;
                 ArrayList<DispositivoEntity> al = new ArrayList<DispositivoEntity>();
                  ArrayList<InmuebleEntity> list = new ArrayList<InmuebleEntity>();
                List<AlertasDTO> dd = null;
               if(result != null)
              {
                  
                  boolean buscar = false;
                  list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for( int i=0 ;i<list.size()&& !buscar;i++)
                  {
                    if(list.get(i).getId() == id2)
                      {
                           entity = list.get(i);
                           buscar = true;
                      }
                  }
                  
                  inmu = entity;
              }
              if(inmu!=null)
              {
                  Boolean encontro = false;               
                  al = (ArrayList<DispositivoEntity>) inmu.getDispositivos();       
                  for(int i =0;i<al.size() && !encontro;i++)
                  {                     
                   
                      DispositivoEntity kk = al.get(i);  
                      System.out.println(kk.getAlertas().size());
                      if(kk.getId()==id3)
                      {
                        encontro = true;
                        dd = kk.alerToDto();
                                     
                      }
                  }
                  
                  return dd;
              }
        
              else
              {
                  return null;
              }
                      
                    
         
     }  
     
     public List<AlertasDTO> getMensualUnidad(Long id,String mes)
     {
         return null;
     }
     public List<AlertasDTO> getMensualInmueble(Long id,Long id2,String mes)
     {
         return null;
     }
     public AlertasDTO createAlarma(Long id,Long id2,Long id3,AlertasDTO dto)
     {
       unidadRecidencialEntity result = persistance.find(id);
             AlertasEntity aa = null ;
              InmuebleEntity entity= null;
                InmuebleEntity inmu = null;
                 ArrayList<DispositivoEntity> al = new ArrayList<DispositivoEntity>();
                  ArrayList<InmuebleEntity> list = new ArrayList<InmuebleEntity>();
                  int ij =0 ;
               if(result != null)
              {
                  boolean buscar = false;
                  list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for( ij=0 ;ij<list.size()&& !buscar;ij++)
                  {
                    if(list.get(ij).getId() == id2)
                      {
                           entity = list.get(ij);
                           buscar = true;
                      }
                  }
                   System.out.println(ij + "12312312312321");
                  inmu = entity;
              }
              if(inmu!=null)
              {
                  Boolean encontro = false;               
                  al = (ArrayList<DispositivoEntity>) inmu.getDispositivos();       
                  for(int i =0;i<al.size();i++)
                  {                     
                      System.out.println(ij + "fjnekjfnkjdsjkesjflkkes");
                      DispositivoEntity kk = al.get(i);   
                      System.out.println(kk.getAlertas().size());
                      if(kk.getId()==id3)
                      {
                       ArrayList<AlertasEntity> alerts = new ArrayList<AlertasEntity>();
                       alerts = (ArrayList<AlertasEntity>) kk.getAlertas();
                          
                       DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
                       Date dateobj = new Date(); 
                       String ss =  df.format(dateobj);
                       dto.setTimeStamp(ss);
                       aa = dto.toEntity(dto);
                       alerts.add(aa);
                       kk.setAlertas(alerts);
                       al.set(i, kk);
                       inmu.setDispositivos(al);
                       list.set(ij-1, inmu);
                       result.setCasas(list);
                       persistance.update(result);
                      }
                  }
                  
                  return aa.toDTO(aa);
              }
        
              else
              {
                  return null;
              }
         
     }
}
