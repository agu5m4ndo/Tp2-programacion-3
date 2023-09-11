package com.example.TPPersistenciaTUP.Repository;

import com.example.TPPersistenciaTUP.Entities.Cliente;
import com.example.TPPersistenciaTUP.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
