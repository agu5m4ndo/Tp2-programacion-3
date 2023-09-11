package com.example.TPPersistenciaTUP.Repository;

import com.example.TPPersistenciaTUP.Entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
