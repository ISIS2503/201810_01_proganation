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

import co.edu.uniandes.isis2503.nosqljpa.authentificacion.AuthorizationFilter;
import co.edu.uniandes.isis2503.nosqljpa.authentificacion.Secured;
import co.edu.uniandes.isis2503.nosqljpa.logic.AlertasLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertasDTO;
import co.edu.uniandes.isis2503.nosqljpa.persistence.unidadRecidencialPersistance;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author js.palacios437
 */

@Path("/Alertas")
//@Secured @Secured({AuthorizationFilter.Role.user})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlertasService {
    
   private final AlertasLogic logica;
   
   public AlertasService()
   {
       this.logica = new AlertasLogic();
   }
    /**
     * devuelve todas las alertas del sistema
     * @return 
     */
    @GET
    @Path("/UnidadResidencial")
    public List<AlertasDTO> getAll()
    {
        return logica.getAll();
    }
    
    /**
     * devuelve las alaertas de una unidad residencial o de todos los inmuebles
     * @param id
     * @return 
     */
    @GET
    @Path("/UnidadResidencial/{id}")
    public List<AlertasDTO> getAllUnidad(@PathParam("id") Long id)
    {
       return logica.getAllUnidad(id);
    }
    /**
     * devuelve las alarmas de un inmueble y todos sus dispositivos
     * @param id
     * @param id2
     * @return 
     */
    @GET
    @Path("/UnidadResidencial/{id}/Inmueble/{id2}")
    public List<AlertasDTO> getAllInmuebles(@PathParam("id") Long id,@PathParam("id2") Long id2)
    {
       return logica.getAllInmueble(id,id2);
    }
    /**
     * devuelve todas las alarmas de un dispositivo
     * @param id
     * @param id2
     * @param id3
     * @return 
     */
    @GET
    @Path("/UnidadResidencial/{id}/Inmueble/{id2}/dispositivo/{id3}")
    public List<AlertasDTO> getDispositivo(@PathParam("id") Long id,@PathParam("id2") Long id2,@PathParam("id3") Long id3)
    {
       return logica.getDispositivo(id,id2,id3);
    }
    
    /**
     * regresa las alarmas de una unidad por mes
     * @param id
     * @param mes
     * @return 
     */
    @GET
    @Path("/UnidadResidencial/{id}/mensual/{mes}")
    public List<AlertasDTO> getMensualUnidad(@PathParam("id") Long id,@PathParam("mes") String mes)
    {
       return logica.getMensualUnidad(id,mes);
    }
    
    @GET
    @Path("/UnidadResidencial/{id}/Inmueble/{id2}/mensual/{mes}")
    public List<AlertasDTO> getMensualInmueble(@PathParam("id") Long id,@PathParam("id2") Long id2,@PathParam("mes") String mes)
    {
       return logica.getMensualInmueble(id,id2,mes);
    }
    
    @POST
    @Path("/UnidadResidencial/{id}/Inmueble/{id2}/dispositivo/{id3}")
    public AlertasDTO createAlarma(@PathParam("id") Long id,@PathParam("id2") Long id2,@PathParam("id3") Long id3,AlertasDTO dto)
    {
        return logica.createAlarma(id,id2,id3,dto);
    }
}
