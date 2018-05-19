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
import java.util.Calendar;
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
        unidadRecidencialEntity result = persistance.find(id);      
          ArrayList<AlertasDTO> dd = new ArrayList<AlertasDTO>() ;
            if(result != null)
              {
                 ArrayList<InmuebleEntity> list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for( int i=0 ;i<list.size();i++)
                  {
                      InmuebleEntity in = list.get(i);
                     ArrayList<DispositivoEntity>  al = (ArrayList<DispositivoEntity>) in.getDispositivos();       
                         for(int j =0;j<al.size();j++)
                           {                     
                    
                            DispositivoEntity kk = al.get(j);  
                          dd.addAll(kk.alerToDto()); 
                              
                         }
                  
                
                  }
                  
                  return dd;
              }
         
              else
              {
                  return null;
              }
     }
      public List<AlertasDTO> getAllInmueble(Long id,Long id2)
     {
         unidadRecidencialEntity result = persistance.find(id);
             
           InmuebleEntity entity= null;
           InmuebleEntity inmu = null;
           ArrayList<DispositivoEntity> al = new ArrayList<DispositivoEntity>();
           ArrayList<InmuebleEntity> list = new ArrayList<InmuebleEntity>();
          ArrayList<AlertasDTO> dd = new ArrayList<AlertasDTO>() ;
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
                              
                  al = (ArrayList<DispositivoEntity>) inmu.getDispositivos();       
                  for(int i =0;i<al.size();i++)
                  {                     
                   
                      DispositivoEntity kk = al.get(i);  
                      dd.addAll(kk.alerToDto());
                    
                  }
                  
                  return dd;
              }
        
              else
              {
                  return null;
              }
     }
       
     public List<AlertasDTO> getAllInmuebleActivos(Long id,Long id2)
     {
         ArrayList<AlertasDTO> res = new ArrayList<AlertasDTO>();
         ArrayList<AlertasDTO> con =(ArrayList<AlertasDTO>) getAllInmueble(id,id2);
         for(int i = 0;i<con.size();i++)
         {
             AlertasDTO al = con.get(i);
             if(al.getActiva())
                 res.add(al);
         }
         
         return res;
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
                    
                      if(kk.getId()==id3)
                      {
                        System.err.println(kk.getAlertas());
                        dd = kk.alerToDto();
                        encontro = true;
                     
                                     
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
         DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
         ArrayList<AlertasDTO> con =(ArrayList<AlertasDTO>) getAllUnidad(id);
        if(con==null)
            return null;
        else
        {
        ArrayList<AlertasDTO> res = new ArrayList<AlertasDTO>();
         Calendar cal1 = Calendar.getInstance();
         Calendar cal2 = Calendar.getInstance();
         Date date = new Date();
             switch(mes){
            case "Enero": 
                 date.setMonth(0);               
            break;
              case "Febrero":
                   date.setMonth(1);  
            break;
              case "Marzo": 
                   date.setMonth(2);  
            break;
              case "Abril": 
                   date.setMonth(3);  
            break;
              case "Mayo": 
                   date.setMonth(4);  
            break;
              case "Junio": 
                   date.setMonth(5);  
            break;
              case "Julio": 
                   date.setMonth(6);  
            break;
               case "Agosto": 
                    date.setMonth(7);  
            break;
               case "Septiembre": 
                    date.setMonth(8);  
            break;
               case "Octubre": 
                    date.setMonth(9);  
            break;
               case "Novienbre": 
                    date.setMonth(10);  
            break;
               case "Diciembre": 
                    date.setMonth(11);  
            break;     
        }
         cal1.setTime(date);
         
         for(int i=0 ;i<con.size();i++)
         {
             AlertasDTO al = con.get(i);
             try {
                 Date nd = df.parse(al.getTimeStamp());
                 cal2.setTime(nd);
                 if(cal2.get(Calendar.MONTH)== cal1.get(Calendar.MONTH))
                 {
                     res.add(al);
                 }
             } catch (Exception e) {
                 return null;
             }
             
         }
         return res;  
        }
     
     }
     public List<AlertasDTO> getMensualInmueble(Long id,Long id2,String mes)
     {
         DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
         ArrayList<AlertasDTO> con =(ArrayList<AlertasDTO>) getAllInmueble(id,id2);
        if(con==null)
            return null;
        else
        {
        ArrayList<AlertasDTO> res = new ArrayList<AlertasDTO>();
         Calendar cal1 = Calendar.getInstance();
         Calendar cal2 = Calendar.getInstance();
         Date date = new Date();
             switch(mes){
            case "Enero": 
                 date.setMonth(0);               
            break;
              case "Febrero":
                   date.setMonth(1);  
            break;
              case "Marzo": 
                   date.setMonth(2);  
            break;
              case "Abril": 
                   date.setMonth(3);  
            break;
              case "Mayo": 
                   date.setMonth(4);  
            break;
              case "Junio": 
                   date.setMonth(5);  
            break;
              case "Julio": 
                   date.setMonth(6);  
            break;
               case "Agosto": 
                    date.setMonth(7);  
            break;
               case "Septiembre": 
                    date.setMonth(8);  
            break;
               case "Octubre": 
                    date.setMonth(9);  
            break;
               case "Novienbre": 
                    date.setMonth(10);  
            break;
               case "Diciembre": 
                    date.setMonth(11);  
            break;     
        }
         cal1.setTime(date);
         
         for(int i=0 ;i<con.size();i++)
         {
             AlertasDTO al = con.get(i);
             try {
                 Date nd = df.parse(al.getTimeStamp());
                 cal2.setTime(nd);
                 if(cal2.get(Calendar.MONTH)== cal1.get(Calendar.MONTH))
                 {
                     res.add(al);
                 }
             } catch (Exception e) {
                 return null;
             }
             
         }
         return res;  
        }
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
               if(result != null )
              {
                if(!result.getActivo())
                {
                    return null;
                }
                  
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
                   if(!inmu.getActivo())
                {
                    return null;
                }
                  Boolean encontro = false;               
                  al = (ArrayList<DispositivoEntity>) inmu.getDispositivos();       
                  for(int i =0;i<al.size();i++)
                  {                     
                      System.out.println(ij + "fjnekjfnkjdsjkesjflkkes");
                      DispositivoEntity kk = al.get(i);   
                      System.out.println(kk.getAlertas().size());
                      if(kk.getId()==id3)
                      {
                      if(!kk.getActiva())
                      {
                      return null;
     
                      }
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
     public AlertasDTO turnOff(Long id,Long id2)
     {
       boolean encontro = false;
       unidadRecidencialEntity result = persistance.find(id);      
          ArrayList<AlertasEntity> dd = new ArrayList<AlertasEntity>() ;
          
          AlertasDTO resp = null;
            if(result != null)
              {
                 ArrayList<InmuebleEntity> list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for( int i=0 ;i<list.size() && !encontro;i++)
                  {
                      InmuebleEntity in = list.get(i);
                     ArrayList<DispositivoEntity>  al = (ArrayList<DispositivoEntity>) in.getDispositivos();       
                       for(int j =0;j<al.size() && !encontro;j++)
                       {                     
                          
                             DispositivoEntity di = al.get(i);
                             dd = (ArrayList<AlertasEntity>)di.getAlertas();
                              for(int k =0;k<dd.size() && !encontro;k++)
                              {
                                  AlertasEntity tt = dd.get(i);
                                  if(tt.getId() == id2)
                                  {                          
                                      tt.setActiva(false);
                                      resp = tt.toDTO(tt);
                                      dd.set(k, tt);
                                      //encontro=true;
                                  }
                              }
                              di.setAlertas(dd);
                              al.set(j, di);
                            
                         }
                    in.setDispositivos(al);
                    list.set(i, in);
                    result.setCasas(list);
                   
                  }
                  persistance.update(result);
                  return resp;
              }
         
              else
              {
                  return null;
              }
     }
}
