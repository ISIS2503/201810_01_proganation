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
import java.util.Date;

/**
 *
 * @author js.palacios437
 */
public class AlertasDTO {
    
    private Long id;
    
    private Boolean Activa;

    public Boolean getActiva() {
        return Activa;
    }

    public void setActiva(Boolean Activa) {
        this.Activa = Activa;
    }
    
    private String tipoDeAlarma;

     private String timeStamp;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
     
    public AlertasDTO()
    {
        this.Activa = true;
    }
    public AlertasDTO(AlertasEntity entity)
    {
        this.id = entity.getId();
        this.tipoDeAlarma = entity.getTipoDeAlarma();
        this.timeStamp = entity.getTimeStamp();
        this.Activa = entity.getActiva();
    }
    
    public AlertasEntity toEntity(AlertasDTO dto)
    {
        return new AlertasEntity(dto);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getTipoDeAlarma() {
        return tipoDeAlarma;
    }

    public void setTipoDeAlarma(String tipoDeAlarma) {
        this.tipoDeAlarma = tipoDeAlarma;
    }

    
}
