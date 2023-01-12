package br.com.attornatus.mscadastro.domain.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.mscadastro.domain.model.Endereco;
import br.com.attornatus.mscadastro.domain.model.Person;
import br.com.attornatus.mscadastro.domain.repository.EnderecoRepository;
import br.com.attornatus.mscadastro.domain.repository.PersonRepository;

@Service
public class DBService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PersonRepository personRepository;
	
	public void instaciaDB() {
		
		Person p1 = new Person(null, "Person Teste", LocalDate.of(1997, 12, 01));
		Endereco e1 = new Endereco(null, "Tiradentes", "68920-705", "4448", "Santana", true, p1);
		Endereco e2 = new Endereco(null, "Sergipe", "76879-119", "9061", "Brasília", false, p1);
		
		p1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		personRepository.save(p1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
	
}
