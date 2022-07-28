package com.dseliel.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dseliel.crud.entities.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
