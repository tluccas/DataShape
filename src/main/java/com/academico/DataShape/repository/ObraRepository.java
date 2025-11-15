package com.academico.DataShape.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.DataShape.model.entity.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long> {
    
}
