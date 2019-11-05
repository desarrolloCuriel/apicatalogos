package com.curiel.catalogos.model.dto;

import java.io.Serializable;

import com.curiel.catalogos.util.FechaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SucursalDto extends FechaDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7296987827312189434L;
    
    Long id;
    String nombre;
    String descripcion;
        
}
