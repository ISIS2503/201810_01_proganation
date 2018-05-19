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
package co.edu.uniandes.isis2503.nosqljpa.model.entity;

import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AlertasDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DispositivoDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.InmuebleDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author js.palacios437
 */
@Entity
@Table(name = "Inmueble")
public class InmuebleEntity implements Serializable{
    
    @Id
    private Long id;
    
    private Boolean activo = true;
    
    private String nombrePropietario;
    
    private String numeroDeContacto;
    
    private String correo; 
    
  @OneToMany(mappedBy = "Dispositivos", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<DispositivoEntity> dispositivos;
  
    @ManyToOne(cascade = CascadeType.PERSIST)
    private unidadRecidencialEntity unidad;
     
    public InmuebleEntity()
    {
        dispositivos = new ArrayList<>();
    }
       public InmuebleEntity(InmuebleDTO dto )
    {
        this.id = dto.getId();
        // cambiar 
        List<DispositivoDTO> al = dto.getDispositivo();
        ArrayList ar = new ArrayList();
        for(int i=0;i<al.size();i++)
        {
            ar.add(al.get(i).DtoToEntity(al.get(i)));
        }
        this.dispositivos =  ar ;
        this.nombrePropietario = dto.getNombrePropietario();
        this.numeroDeContacto = dto.getNumeroDeContacto();
        this.correo = dto.getCorreo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<DispositivoEntity> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<DispositivoEntity> dispositivos) {
        this.dispositivos = dispositivos;
    }



    public unidadRecidencialEntity getUnidad() {
        return unidad;
    }

    public void setUnidad(unidadRecidencialEntity unidad) {
        this.unidad = unidad;
    }
    
    /**
     * convierte un entity a dto
     * @param entity
     * @return 
     */
    public InmuebleDTO entityToDTO(InmuebleEntity entity)
    {
        return new InmuebleDTO(entity);
    }
    /**
     * convierte una lsita a dto
     * @param list
     * @return 
     */
       public List<InmuebleDTO> listToEntity(List<InmuebleEntity> list)
    {
         ArrayList<InmuebleDTO> dtos = new ArrayList<>();
        for (InmuebleEntity entity : list) {
            
            dtos.add(entity.entityToDTO(entity));
        }
        return dtos;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getNumeroDeContacto() {
        return numeroDeContacto;
    }

    public void setNumeroDeContacto(String numeroDeContacto) {
        this.numeroDeContacto = numeroDeContacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
