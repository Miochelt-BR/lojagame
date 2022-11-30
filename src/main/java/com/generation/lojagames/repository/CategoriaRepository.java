package com.generation.lojagames.repository;


import com.generation.lojagames.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Long> {

    public List<Categorias> findAllByTipoContainingIgnoreCase(@Param("tipo") String tipo);
}
