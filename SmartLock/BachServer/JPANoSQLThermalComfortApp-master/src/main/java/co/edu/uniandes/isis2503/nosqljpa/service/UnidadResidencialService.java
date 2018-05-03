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


import co.edu.uniandes.isis2503.nosqljpa.authentificacion.AuthorizationFilter.Role;
import co.edu.uniandes.isis2503.nosqljpa.authentificacion.Secured;
import co.edu.uniandes.isis2503.nosqljpa.logic.unidadRecidencialLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertasDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DispositivoDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.InmuebleDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.unidadRecidencialDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.unidadRecidencialEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author js.palacios437
 */
@Path("/UnidadResidencial")
//@Secured
 @Secured({Role.user})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UnidadResidencialService {
  
private final unidadRecidencialLogic logica;
    
    
    
    public UnidadResidencialService()
    {
        logica = new unidadRecidencialLogic();
    }
    
    /**
     * metodo get que regresa todas las unidades residenciales url: http://172.24.42.60:8080/UnidadResidencial/
     * @return lsita con las unidades recidenciales
     */
   @GET
   @Secured({Role.Yale})
   public List<unidadRecidencialDTO> all() {
     List<unidadRecidencialEntity> lo = logica.all();
     unidadRecidencialEntity uu = new unidadRecidencialEntity();
     return uu.listToEntity(lo);
    }
    /**
     * metodo que regresa una unidad en particular url: http://172.24.42.60:8080/UnidadResidencial/1
     * @param id de la unidad
     * @return la unidad
     */
    @GET
    @Secured({Role.Secure})
    @Path("/{id}")
    public unidadRecidencialDTO find(@PathParam("id") Long id) {
        return logica.find(id);
    }
    @GET
    @Secured({Role.admin})
   @Path("{id}/Inmueble")
   public List<InmuebleDTO> allInmueble(@PathParam("id") Long id) {
      return logica.allIn(id);
   }
   /**
    * metodo que busca un inmueble especifico en una unidad url http://172.24.42.60:8080/UnidadResidencial/1/Inmueble/1
    * @param id
    * @param id2
    * @return 
    */
   @GET
   @Secured({Role.user})
   @Path("{id}/Inmueble/{id2}")
   public InmuebleDTO findInmueble(@PathParam("id") Long id,@PathParam("id2") Long id2) {
      return logica.findIn(id,id2);
   }
   /**
    * metodo que regresa todas las alartas url http://172.24.42.60:8080/UnidadResidencial/1/Inmueble/1/Alertas/
    * @param id
    * @param id2
    * @return 
    */
   @GET
   @Secured({Role.user})
   @Path("{id}/Inmueble/{id2}/Alertas")
   public List<DispositivoDTO> allAlertas(@PathParam("id") Long id,@PathParam("id2") Long id2) {
     List<DispositivoDTO> lista = logica.allAlertas(id, id2);
     unidadRecidencialEntity uu = new unidadRecidencialEntity();
     return  lista;
    }
   /**
    * metodo que regrsa una alarma url http://172.24.42.60:8080/UnidadResidencial/1/Inmueble/1/Alertas/1
    * @param id
    * @param id2
    * @param id3
    * @return 
    */
   @GET
   @Path("{id}/Inmueble/{id2}/Alertas/{id3}")
   public DispositivoDTO findAlertas(@PathParam("id") Long id,@PathParam("id2") Long id2,@PathParam("id3") Long id3) {
          return logica.findAlertas(id, id2, id3);
    }
    
//   @GET
//   @Path("{id}/Inmueble/{id2}/beforeToDay")
//   public List<AlertasDTO> findAlertasBeforeInmueble(@PathParam("id") Long id,@PathParam("id2") Long id2) {
//       
//       
//         DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
//         Date td = new Date();
//         String date = df.format(td);
//        return logica.AlertasBefore(id, id2, date);
//      
//    }
    /**
     * crea una nueva unidada residencial url: http://172.24.42.60:8080/UnidadResidencial/
     * @param dto de la nueva unidad
     * @return la unidad creada
     */
   @POST
   public unidadRecidencialDTO creat(unidadRecidencialDTO dto)
   {
       
       return logica.add(dto);
        
    }
   
    /**
     * metodo que crea y agrega un inmueble a la unidad residencial  url http://172.24.42.60:8080/UnidadResidencial/1/Inmueble/
     * @param id de la unidad
     * @param dto del inmueble
     * @return el inmueble creado
     */
    @POST
    @Path("{id}/Inmueble")
    public InmuebleDTO creatInmueble(@PathParam("id") Long id,InmuebleDTO dto)
    {
        return logica.addIn(id, dto);
    }
    
    /**
     * metodo que agrega una alerta a una casa url http://172.24.42.60:8080/UnidadResidencial/1/Inmueble/1
     * @param id
     * @param id2
     * @param dto
     * @return 
     */
    @POST
    @Path("{id}/Inmueble/{id2}")
    public DispositivoDTO creatAlerta(@PathParam("id") Long id,@PathParam("id2") Long id2,DispositivoDTO dto)
    {
        return logica.addAlerta(id,id2,dto);
    }
    
    /**
     * metodo que elimina logicamente una unidad dejandola inactiva  http://172.24.42.60:8080/UnidadResidencial/1
     * @param id la unidad
     * @return  la unidad 
     */
    @DELETE
    @Secured({Role.Yale})
    @Path("/{id}")
    public unidadRecidencialDTO desactivar(@PathParam("id") Long id)
    {
        return logica.desabilitar(id);
    }
    
   @DELETE
   @Path("{id}/Inmueble/{id2}")
   public InmuebleDTO desactibarInmueble(@PathParam("id") Long id,@PathParam("id2") Long id2)
    {
        return logica.desabilitarInmueble(id,id2);
    }
    
    
}
