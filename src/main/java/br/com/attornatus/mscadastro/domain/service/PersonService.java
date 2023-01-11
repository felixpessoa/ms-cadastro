package br.com.attornatus.mscadastro.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.attornatus.mscadastro.domain.model.Endereco;
import br.com.attornatus.mscadastro.domain.model.Person;
import br.com.attornatus.mscadastro.domain.repository.EnderecoRepository;
import br.com.attornatus.mscadastro.domain.repository.PersonRepository;
import br.com.attornatus.mscadastro.domain.service.exceptions.ObjectNotFoundException;
import br.com.attornatus.mscadastro.dto.PersonNewDTO;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Person getById(Long id) {
		Optional<Person> obj = personRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cadastro não encontrado! " + id + ", Tipo " + Person.class.getName()));
	}

	@Transactional
	public Person create(Person person) {
		person = personRepository.save(person);
		enderecoRepository.saveAll(person.getEnderecos());

		return person;
	}

	public Person fromDTO(PersonNewDTO objDTO) {
		Person pers = new Person(null, objDTO.getNome(), objDTO.getDataDeNascimento());
		objDTO.getEnderecos().forEach(x -> {
			Endereco end = new Endereco(null, x.getLogradouro(), x.getCep(), x.getNumero(), x.getCidade(),
					x.isPrincipal(), pers);
			pers.getEnderecos().add(end);
		});
		return pers;
	}

	public Person update(Long id, @Valid Person obj) {
		Person newobj = getById(id);
		updateData(newobj, obj);
		return personRepository.save(newobj);
	}
	
	private void updateData(Person newObj, Person obj) {
		newObj.setNome(obj.getNome());
		newObj.setDataDeNascimento(obj.getDataDeNascimento());
	}
	

}
