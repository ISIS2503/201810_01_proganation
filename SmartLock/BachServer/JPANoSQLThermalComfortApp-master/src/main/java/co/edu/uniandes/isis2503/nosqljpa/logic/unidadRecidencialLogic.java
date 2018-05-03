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

import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DispositivoDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.InmuebleDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.unidadRecidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.DispositivoEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.InmuebleEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.unidadRecidencialEntity;
import co.edu.uniandes.isis2503.nosqljpa.persistence.unidadRecidencialPersistance;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author js.palacios437
 */
public class unidadRecidencialLogic {
   
    private final unidadRecidencialPersistance persistance;
    
    public unidadRecidencialLogic()
    {
        this.persistance = new unidadRecidencialPersistance();
    }
    
    /**
     * metodo que agrega una unidad residencial
     * @param dto
     * @return la unidad crerada
     */
     public unidadRecidencialDTO add(unidadRecidencialDTO dto)
     {
          System.out.println(dto.getId());
         unidadRecidencialEntity result = persistance.add(dto.toEntity(dto));
         System.out.println(result.getId());
         return result.entitytoDTO(result);
     }
     
          public unidadRecidencialDTO find(Long id)
     {
         unidadRecidencialEntity result = persistance.find(id);
         System.out.print(result);
         if(result!=null)
         {
              return result.entitytoDTO(result);
         }
         else
         {
             return null;
         }
        
     }
          /**
           * metodo que regresa un imueble especifico de una unidad
           * @param id de la unidad
           * @param id2 del inmueble
           * @return  el inmueble
           */
          public InmuebleDTO findIn(Long id,Long id2)
          {
              Boolean buscar = false;
              unidadRecidencialEntity result = persistance.find(id);
              if(result != null)
              {
                  InmuebleEntity entity= null;
                  ArrayList<InmuebleEntity> list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for(int i =0;i<list.size()&& !buscar;i++)
                  {
                      
                      if(list.get(i).getId() == id2)
                      {
                           entity = list.get(i);
                           buscar = true;
                      }
                  }
                  return entity.entityToDTO(entity);
              }
              else
              {
                  return null;
              }
          }
          /**
           * metodo que retorna todas las unidades
           * @return  lista de unidades
           */
          public List<unidadRecidencialEntity> all()
          {
              return persistance.all();
          }
          
          /**
           * metodo que agrega una iomueble a una unidad
           * @param id de la unidad
           * @param dto el inmueble
           * @return  el inmueble agregado
           */
          public InmuebleDTO addIn(Long id,InmuebleDTO dto)
          {
              unidadRecidencialEntity result = persistance.find(id);
              if(result!=null && result.getActivo())
              {
                  InmuebleEntity in = new InmuebleEntity(dto);
                  ArrayList<InmuebleEntity> ar =  (ArrayList)result.getCasas();
          
                  ar.add(in);
             
                  result.setCasas(ar);
              
                  persistance.update(result);
                  return dto;
              }
              else
              {
                  return null;
              }
          }
          /**
           * metodo que elimina logicamente una unidad
           * @param id de la unidad
           * @return la unidad desabilitada
           */
          public unidadRecidencialDTO desabilitar(Long id)
          {
              unidadRecidencialEntity result = persistance.find(id);
              if(result != null)
              {
                  result.setActivo(false);
                  persistance.update(result);
                  return result.entitytoDTO(result);
              }
              else
              {
                  return null;
              }
          }
          
          public InmuebleDTO desabilitarInmueble(Long id,Long id2)
          {
              boolean encontro = false;
              unidadRecidencialEntity result = persistance.find(id);
              if(result!=null)
              {
                  InmuebleEntity in=new InmuebleEntity();
                  ArrayList<InmuebleEntity> al = (ArrayList<InmuebleEntity>) result.getCasas();
                  for(int i =0;i<al.size()&& !encontro;i++)
                  {
                      in = al.get(i);
                      if(in.getId()==id2)
                      {
                          in.setActivo(Boolean.FALSE);
                          al.set(i,in );
                          encontro = true;
                      }
                  }
                  result.setCasas(al);
                  persistance.update(result);
                  return in.entityToDTO(in);
              }
              else
              {
                  return null;
              }
          }
          /**
           * metoddo que retorna todas als arlertas
           * @param id
           * @param id2
           * @return 
           */
             public List<DispositivoDTO> allDispositivos(Long id ,Long id2)
          {
                unidadRecidencialEntity result = persistance.find(id);
                InmuebleEntity inmu = null;
               if(result != null)
              {
                  boolean buscar = false;
                  InmuebleEntity entity= null;
                  ArrayList<InmuebleEntity> list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for(int i =0;i<list.size()&& !buscar;i++)
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
                  
                  ArrayList<DispositivoEntity> al = (ArrayList<DispositivoEntity>) inmu.getDispositivos();
                  ArrayList<DispositivoDTO> ar = new ArrayList<>();
                  for(int i =0;i<al.size();i++)
                  {
                      DispositivoEntity kk = al.get(i);
                      ar.add(kk.entityToDto(kk));
                  }
                 
                  return ar;
              }
              else
              {
                  return null;
              }
          }
             /**
              * metodo que encuentra una alerta
              * @param id
              * @param id2
              * @param id3
              * @return 
              */
              public DispositivoDTO findDispositivo(Long id ,Long id2,Long id3)
          {
             unidadRecidencialEntity result = persistance.find(id);
             DispositivoEntity alert = new DispositivoEntity();
                InmuebleEntity inmu = null;
               if(result != null)
              {
                  boolean buscar = false;
                  InmuebleEntity entity= null;
                  ArrayList<InmuebleEntity> list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for(int i =0;i<list.size()&& !buscar;i++)
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
               
                  ArrayList<DispositivoEntity> al = (ArrayList<DispositivoEntity>) inmu.getDispositivos();
         
                  for(int i =0;i<al.size();i++)
                  {
                       
                      DispositivoEntity kk = al.get(i);
           
                      if(kk.getId()==id3)
                      {
                        
                          alert = kk;
                          encontro =true;
                      }
                  }
                  return alert.entityToDto(alert);
              }
              else
              {
                  return null;
              }
          }
          /**
           * metodo que agrega una alerta a una casa
           * @param id id de la unidad
           * @param id2 del inmueble
           * @param dto la alerta
           * @return la lareta agregada
           */
          public DispositivoDTO addDispositivo(Long id,Long id2,DispositivoDTO dto)
          {
                unidadRecidencialEntity result = persistance.find(id);
                Boolean buscar = false;
                int cont = 0;
                   InmuebleDTO inmueble =  findIn(id,id2);
              if(result != null)
              {
                  InmuebleEntity entity= null;
                  ArrayList<InmuebleEntity> list = (ArrayList<InmuebleEntity>) result.getCasas();
                  for(int i =0;i<list.size()&& !buscar;i++)
                  {
                      
                      if(list.get(i).getId() == id2 && list.get(i).getActivo())
                      {
                           entity = list.get(i);
                           buscar = true;
                           cont = i;
                      }
                  }
                   
                  if(entity != null)
                  {
                      ArrayList<DispositivoEntity> ar = (ArrayList<DispositivoEntity>) entity.getDispositivos();
                      //    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
                         //  Date dateobj = new Date(); 
                       //  String ss =  df.format(dateobj);
                        
                         //   dto.setTimeStamp(ss);
                      ar.add(dto.DtoToEntity(dto));
   
                      entity.setDispositivos(ar);
                      list.set(cont,entity);
                      result.setCasas(list);
                     persistance.update(result);    
                     }
                  
                  return dto;
                 }   
              else
              {
                  return null;
              }
                   
          }
          
          
     //  public List<AlertasDTO> AlertasBefore(Long id,Long id2,String Date)
     //  {
       //    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
       //    try {
       //        Date dd = df.parse(Date);
       //        ArrayList<AlertasDTO> alerts =  (ArrayList<AlertasDTO>) allAlertas(id,id2);
        //       ArrayList<AlertasDTO> res = new ArrayList<>();
       //       for(int i =0;i<alerts.size();i++)
        //      {
        //          AlertasDTO al = alerts.get(i);
        //          Date cd = df.parse(al.getTimeStamp());
         //         if(cd.before(dd))
         //         {
         //             res.add(al);
          //        }
         //     }
         //       return res;
        //   } catch (Exception e) {
        //       return null;
       //   }
         
           
      // }
       public List<InmuebleDTO> allIn(Long id)
       {
             unidadRecidencialEntity result = persistance.find(id);
             if(result != null)
             {
                 List<InmuebleEntity> al = result.getCasas();
                 List<InmuebleDTO> ar = new ArrayList<>();
                 for(int i=0;i<al.size();i++)
                 {
                     InmuebleEntity en = al.get(i);
                     ar.add(en.entityToDTO(en));
                 }
                 return ar;
             }
             else
             {
                 return null;
             }
       }
       
      public DispositivoDTO silenciar(Long id,Long id2,Long id3)
      {
          return null;
      }
}
