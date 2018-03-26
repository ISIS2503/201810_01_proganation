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


import co.edu.uniandes.isis2503.nosqljpa.model.entity.AlertasEntity;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.InmuebleEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author js.palacios437
 */
public class InmuebleDTO {
    
    private Long id;
    
    private Boolean activo = true;
    
    private List<AlertasDTO> alertas;
    
    public InmuebleDTO()
    {
        alertas = new ArrayList<>();
    }
     public InmuebleDTO(InmuebleEntity entity)
    {
        this.id = entity.getId();
        ArrayList<AlertasEntity> al = (ArrayList<AlertasEntity>) entity.getAlertas();
        ArrayList<AlertasDTO> ar = new ArrayList<>();
        for(int i=0;i<al.size();i++)
        {
            ar.add(al.get(i).toDTO(al.get(i)));
        }
        this.alertas = ar;
        this.activo = entity.getActivo();
    }
     
     public InmuebleEntity toEntity(InmuebleDTO dto)
     {
         return new InmuebleEntity(dto);
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   

    public List<AlertasDTO> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<AlertasDTO> alertas) {
        this.alertas = alertas;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
}
