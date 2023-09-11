package com.example.TPPersistenciaTUP.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DetallePedido extends EntityFactory {
    private int cantidad;
    private double subtotal;
    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;
}
