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


import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.unidadRecidencialDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author js.palacios437
 */
@Entity
@Table(name = "unidadRecidencial")
public class unidadRecidencialEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    private Boolean activo =true;
   
   @OneToMany(mappedBy = "Inmueble", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<InmuebleEntity> casas;
    
    public unidadRecidencialEntity()
    {
       casas = new ArrayList<InmuebleEntity>();
      
    }
    
    public unidadRecidencialEntity(unidadRecidencialDTO dto)
    {
        //this.id = dto.getId();
        
        
        
    }

    //////getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InmuebleEntity> getCasas() {
        return casas;
    }

    public void setCasas(List<InmuebleEntity> casas) {
        this.casas = casas;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
   /**
    * convierte una entidad a dto
    * @param entity
    * @return 
    */
    public unidadRecidencialDTO entitytoDTO(unidadRecidencialEntity entity )
    {
        
        return new unidadRecidencialDTO(entity);
    }
    /**
     * convierte una lista de sto a entitys
     * @param list
     * @return 
     */
        public List<unidadRecidencialDTO> listToEntity(List<unidadRecidencialEntity> list)
    {
         ArrayList<unidadRecidencialDTO> dtos = new ArrayList<>();
        for (unidadRecidencialEntity entity : list) {
            
            dtos.add(entity.entitytoDTO(entity));
        }
        return dtos;
    }
}
