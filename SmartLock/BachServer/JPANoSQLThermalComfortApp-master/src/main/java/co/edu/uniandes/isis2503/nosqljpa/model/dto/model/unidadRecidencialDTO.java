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
package co.edu.uniandes.isis2503.nosqljpa.model.dto.model;


import co.edu.uniandes.isis2503.nosqljpa.model.entity.InmuebleEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.unidadRecidencialEntity;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author js.palacios437
 */
//@XmlRootElement
public class unidadRecidencialDTO {
    
    private  Long id;
    
    private  Boolean activo;
            
    private List<InmuebleDTO> inmuebles;
    

    
    public unidadRecidencialDTO()
    {
        activo = true;
        inmuebles = new ArrayList<InmuebleDTO>();
    }
    public unidadRecidencialDTO(unidadRecidencialEntity entity)
    {
        System.out.println(entity.getId());
        this.id = entity.getId();
       
            List<InmuebleEntity> al =   entity.getCasas();  
             ArrayList<InmuebleDTO> ar = new ArrayList<>();
             System.out.println(entity.getId());
             System.out.println(al);
            if(al!=null)
            {
                System.out.println("tamanio");
                   for(int i =0;i<al.size();i++)
                    {
                        System.out.println("pre Agrego");
                        ar.add(al.get(i).entityToDTO(al.get(i)));
                        System.out.println("pos Agrego");
                    }
                   this.inmuebles = ar;
                   System.out.println("21412421412412412412214124");
            }
            else
            {
                this.inmuebles = new ArrayList<>();
                System.out.println("ELSE");
            }
        
      
       
     
        
         this.activo = entity.getActivo();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InmuebleDTO> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(List<InmuebleDTO> inmuebles) {
        this.inmuebles = inmuebles;
    }

 

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }


    
    
    public unidadRecidencialEntity toEntity(unidadRecidencialDTO dto)
    {
        return new unidadRecidencialEntity(dto);
    }
}
