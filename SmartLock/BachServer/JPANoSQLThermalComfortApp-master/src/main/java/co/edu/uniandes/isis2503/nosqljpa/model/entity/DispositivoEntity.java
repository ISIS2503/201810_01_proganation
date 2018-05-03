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
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.DispositivoDTO;
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
@Table(name = "Dispositivos")
public class DispositivoEntity implements Serializable{
    
    @Id
    private Long id;
    
    private String tipo;
    
    private Boolean activa;
    
    @OneToMany(mappedBy = "Alertas", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<AlertasEntity> Alertas;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private InmuebleEntity inmueble;
    
    public DispositivoEntity()
    {
        Alertas = new ArrayList<>();
    }
    
   
    public DispositivoEntity(DispositivoDTO dto)
    {
        this.id = dto.getId();
        this.tipo = dto.getTipo();
        this.activa = dto.getActiva();
         Alertas = new ArrayList<>();
       // this.Alertas = dto.getAlertas();
    }
    public DispositivoDTO entityToDto(DispositivoEntity entity)
    {
        return new DispositivoDTO(entity);
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

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public InmuebleEntity getInmueble() {
        return inmueble;
    }

    public void setInmueble(InmuebleEntity inmueble) {
        this.inmueble = inmueble;
    }

 

    public List<AlertasEntity> getAlertas() {
        return Alertas;
    }

    public void setAlertas(List<AlertasEntity> Alertas) {
        this.Alertas = Alertas;
    }
    
}
