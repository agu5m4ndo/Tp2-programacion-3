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
@Builder
@Entity
public class Rubro extends EntityFactory {
    private String denominacion;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto getOneProducto(Long producto_id) {
        for (Producto value : productos) {
            if (value.getId().equals(producto_id)) return value;
        }
        return null;
    }

    public void mostrarProductos() {
        System.out.print("Productos: [");
        for (int i = 0; i < productos.size(); i++) {
            if (i == productos.size()- 1) System.out.println(productos.get(i) + "]");
            else System.out.print(productos.get(i) + ", ");
        }
    }
}
