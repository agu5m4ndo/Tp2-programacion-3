package com.example.TPPersistenciaTUP.Repository;

import com.example.TPPersistenciaTUP.Entities.Cliente;
import com.example.TPPersistenciaTUP.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
