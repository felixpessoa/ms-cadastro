package br.com.attornatus.mscadastro.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.attornatus.mscadastro.domain.model.Person;
import br.com.attornatus.mscadastro.domain.service.PersonService;
import br.com.attornatus.mscadastro.dto.PersonNewDTO;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping
	public ResponseEntity<Person> save(@Valid @RequestBody PersonNewDTO objDTO){
		Person obj = personService.fromDTO(objDTO);
		obj = personService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> upDate(@PathVariable Long id, @Valid @RequestBody Person newObj){
		Person obj = personService.update(id, newObj);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(personService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> findAll(){
		return ResponseEntity.ok().body(personService.getAll());
	}
	
	
	
	

}
