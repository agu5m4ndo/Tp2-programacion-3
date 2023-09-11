package com.example.TPPersistenciaTUP.Entities;

import com.example.TPPersistenciaTUP.Enum.EnumFormaPago;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Factura extends EntityFactory {
    private LocalDateTime fecha;
    private int numero;
    private double descuento;
    private EnumFormaPago formaPago;
    private int total;
}
