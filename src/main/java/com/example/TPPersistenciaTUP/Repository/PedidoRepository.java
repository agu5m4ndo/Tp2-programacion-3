package com.example.TPPersistenciaTUP.Repository;

import com.example.TPPersistenciaTUP.Entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
