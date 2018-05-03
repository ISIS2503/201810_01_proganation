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

import co.edu.uniandes.isis2503.nosqljpa.model.entity.DispositivoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;


/**
 *
 * @author js.palacios437
 */
public class DispositivoDTO  {
    
    @Id
    private Long id;
    
    private String tipo;
    
    private Boolean activa;
    
    private List<AlertasDTO> alertas;
    
    public DispositivoDTO()
    {
        alertas = new ArrayList<AlertasDTO>();
        this.activa = false;
    }
    public DispositivoDTO(DispositivoEntity entity)
    {
        this.id = entity.getId();
        this.tipo = entity.getTipo();
        this.activa = entity.getActiva();
        this.alertas = new ArrayList<AlertasDTO>();
        this.alertas = entity.alerToDto();
    }

    public DispositivoDTO entityToDto(DispositivoEntity entity)
    {
        return new DispositivoDTO(entity);
    }
        public DispositivoEntity DtoToEntity(DispositivoDTO dto)
    {
        return new DispositivoEntity(dto);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getActiva() {
        return activa;
    }

    public List<AlertasDTO> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<AlertasDTO> alertas) {
        this.alertas = alertas;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
    
    
}
