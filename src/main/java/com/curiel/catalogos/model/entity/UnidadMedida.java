package com.curiel.catalogos.model.entity;
 import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne; 

import com.curiel.catalogos.util.FechaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class UnidadMedida  extends FechaEntity implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    String clave;
    String descripcion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Sucursal sucursal;
  

}