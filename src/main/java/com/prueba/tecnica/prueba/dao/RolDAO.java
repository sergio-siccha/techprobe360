package com.prueba.tecnica.prueba.dao;

import com.prueba.tecnica.prueba.dto.RolDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Esta clase establece la comunicacion con la BD
public interface RolDAO extends JpaRepository<RolDTO, Integer> {
    Optional<RolDTO> findByNombre(String nombre);
}
