package com.JMRocha.demoajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JMRocha.demoajax.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
