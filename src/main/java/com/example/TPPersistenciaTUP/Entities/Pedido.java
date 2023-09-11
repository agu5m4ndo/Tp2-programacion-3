package com.example.TPPersistenciaTUP.Entities;

import com.example.TPPersistenciaTUP.Enum.EnumEntity;
import com.example.TPPersistenciaTUP.Enum.EnumTipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name= "pedido")
public class Pedido extends EntityFactory {
    private LocalDateTime fecha;
    private EnumEntity estado;
    private LocalDateTime horaEstimadaEntrega;
    private EnumTipoEnvio tipoEnvio;
    private double total;

    @OneToMany( orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;
    public void addDetallePedidos(DetallePedido detalle) {
        detallePedidos.add(detalle);
        System.out.println("Adentro de la lista: " + detallePedidos);
    }

    public DetallePedido getOnePedido(Long pedido_id) {
        for (DetallePedido value : detallePedidos) {
            if (value.getId().equals(pedido_id)) return value;
        }
        return null;
    }
    public void mostrarDetallesPedido() {
        System.out.print("Pedidos: [");
        for (int i = 0; i < detallePedidos.size(); i++) {
            if (i == detallePedidos.size()- 1) System.out.println(detallePedidos.get(i) + "]");
            else System.out.print(detallePedidos.get(i) + ", ");
        }
    }
}
