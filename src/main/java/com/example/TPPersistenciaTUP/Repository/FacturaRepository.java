package com.example.TPPersistenciaTUP.Repository;

import com.example.TPPersistenciaTUP.Entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
