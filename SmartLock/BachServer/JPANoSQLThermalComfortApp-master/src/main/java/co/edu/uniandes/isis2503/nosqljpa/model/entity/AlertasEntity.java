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
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author js.palacios437
 */
@Entity
@Table(name = "Alertas")
public class AlertasEntity implements Serializable {
    
    @Id
    private Long id;
    
    private String tipoDeAlarma;
    
    private String timeStamp;
      
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private DispositivoEntity Dispositivo;
      
      
      public AlertasEntity()
      {
      
      }
            public AlertasEntity(AlertasDTO dto)
      {
          this.id = dto.getId();
          this.tipoDeAlarma = dto.getTipoDeAlarma();
          this.timeStamp = dto.getTimeStamp();
      }
      /**
       * convierte un dto a entity
       * @param dto
       * @return 
       */
      public AlertasEntity DTOtoEntity(AlertasDTO dto)
      {
          return new AlertasEntity(dto);
      }
      
      public AlertasDTO toDTO(AlertasEntity entity)
      {
          return new AlertasDTO(entity);
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

    public DispositivoEntity getDispositivo() {
        return Dispositivo;
    }

    public void setDispositivo(DispositivoEntity Dispositivo) {
        this.Dispositivo = Dispositivo;
    }

    
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
}
