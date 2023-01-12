package br.com.attornatus.mscadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.attornatus.mscadastro.domain.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	@Query("SELECT obj FROM Endereco obj WHERE obj.person.id =:person AND obj.principal = true")
	Endereco findByPersonTrue(@Param("person") Long person);
}
