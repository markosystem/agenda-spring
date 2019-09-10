package com.brq.agenda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brq.agenda.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	@Query("select c from contato c where LOWER(c.nome) LIKE %?1% OR LOWER(c.sobrenome) LIKE %?1% OR LOWER(CONCAT(c.nome, ' ', c.sobrenome)) LIKE %?1% OR LOWER(c.github) LIKE %?1% OR LOWER(c.email) LIKE %?1%")
	List<Contato> findBySearchAll(String search);
}
