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

/**
 *
 * @author js.palacios437
 */
public class AlertasDTO {
    
       private Long id;
    
    private String idlock;
    
    private String tipoDeAlarma;

    
    public AlertasDTO()
    {
        
    }
    public AlertasDTO(AlertasEntity entity)
    {
        this.id = entity.getId();
        this.idlock = entity.getIdlock();
        this.tipoDeAlarma = entity.getTipoDeAlarma();
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

    public String getIdlock() {
        return idlock;
    }

    public void setIdlock(String idlock) {
        this.idlock = idlock;
    }

    public String getTipoDeAlarma() {
        return tipoDeAlarma;
    }

    public void setTipoDeAlarma(String tipoDeAlarma) {
        this.tipoDeAlarma = tipoDeAlarma;
    }

    
}
