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
public class Usuario extends EntityFactory {
    private String nombre;
    private String password;
    private String rol;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido getOnePedido(Long pedido_id) {
        for (Pedido value : pedidos) {
            if (value.getId().equals(pedido_id)) return value;
        }
        return null;
    }

    public void mostrarPedidos() {
        System.out.print("Pedidos: [");
        for (int i = 0; i < pedidos.size(); i++) {
            if (i == pedidos.size()- 1) System.out.println(pedidos.get(i) + "]");
            else System.out.print(pedidos.get(i) + ", ");
        }
    }
}






