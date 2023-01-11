package br.com.attornatus.mscadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.mscadastro.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
