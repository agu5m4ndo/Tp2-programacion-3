package com.example.TPPersistenciaTUP.Repository;

import com.example.TPPersistenciaTUP.Entities.ConfiguracionGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionGeneralRepository extends JpaRepository<ConfiguracionGeneral, Long> {
}
