package com.example.TPPersistenciaTUP.Repository;

import com.example.TPPersistenciaTUP.Entities.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long> {
}
