package com.prueba.tecnica.prueba.dao;

import com.prueba.tecnica.prueba.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<UsuarioDTO, Integer> {
    boolean existsByUsername(String username);

    boolean existsById(Integer id);

    @Override
    Optional<UsuarioDTO> findById(Integer integer);
}
