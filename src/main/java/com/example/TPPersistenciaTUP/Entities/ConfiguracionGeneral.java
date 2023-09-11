package com.example.TPPersistenciaTUP.Entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ConfiguracionGeneral extends EntityFactory{
    private int cantidadCocineros;
    private String emailEmpresa;
    private String tokenMercadoPago;
}
