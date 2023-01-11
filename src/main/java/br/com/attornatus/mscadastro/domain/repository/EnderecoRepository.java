package br.com.attornatus.mscadastro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.mscadastro.domain.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
}
