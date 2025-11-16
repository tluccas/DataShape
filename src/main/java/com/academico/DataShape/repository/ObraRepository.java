package com.academico.DataShape.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.DataShape.model.entity.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long> {
   // Optional<Obra> findByTituloObra(String tituloObra); // Quando deixar titulo obra unique

    List<Obra> findAllByTituloObra(String tituloObra);
}
