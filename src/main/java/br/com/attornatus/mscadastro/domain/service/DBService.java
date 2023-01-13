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

		Person p2 = new Person(null, "Person Teste", LocalDate.of(1997, 12, 01));
		Endereco e4 = new Endereco(null, "Rua São Jorge", "89449-757", "9939", "Maranguape", true, p2);
		p2.getEnderecos().addAll(Arrays.asList(e4));
		
		Person p3 = new Person(null, "Kataleya Cavadas", LocalDate.of(1989, 05, 29));
		Endereco e5 = new Endereco(null, "Rua Vinte e Dois", "83543-815", "4601", "Paulista", true, p3);
		p3.getEnderecos().addAll(Arrays.asList(e5));
		
		Person p4 = new Person(null, "Taísa Cipriano", LocalDate.of(1995, 03, 25));
		Endereco e6 = new Endereco(null, "Rua Quinze de Novembro", "69071-220", "5605", "Itabuna", true, p4);
		p4.getEnderecos().addAll(Arrays.asList(e6));
		
		
		personRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e4, e5, e6));
		
	}
	
}
