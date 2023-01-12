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

import br.com.attornatus.mscadastro.domain.model.Endereco;
import br.com.attornatus.mscadastro.domain.service.EnderecoService;
import br.com.attornatus.mscadastro.dto.EnderecoNewDTO;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping
	public ResponseEntity<Endereco> save(@Valid @RequestBody EnderecoNewDTO objDTO){
		Endereco obj =  enderecoService.fromDTO(objDTO);
		enderecoService.create(obj);;
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> upDate(@PathVariable Long id, @RequestBody Endereco newObj){
		enderecoService.update(id, newObj);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(enderecoService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll(){
		return ResponseEntity.ok().body(enderecoService.getAll());
	}
	
	
	
	

}
