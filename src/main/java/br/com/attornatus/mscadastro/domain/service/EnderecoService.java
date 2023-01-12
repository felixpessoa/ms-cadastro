package br.com.attornatus.mscadastro.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.mscadastro.domain.model.Endereco;
import br.com.attornatus.mscadastro.domain.model.Person;
import br.com.attornatus.mscadastro.domain.repository.EnderecoRepository;
import br.com.attornatus.mscadastro.domain.service.exceptions.ObjectNotFoundException;
import br.com.attornatus.mscadastro.dto.EnderecoNewDTO;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PersonService personService;

	public List<Endereco> getAll() {
		return enderecoRepository.findAll();
	}

	public Endereco getById(Long id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Endereço não encontrado! " + id + ", Tipo " + Endereco.class.getName()));
	}

	public Endereco create(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Endereco update(Long id, @Valid Endereco obj) {
		Endereco newobj = getById(id);
		updateData(newobj, obj);
		return enderecoRepository.save(newobj);
	}
	
	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setCep(obj.getCep());
		newObj.setNumero(obj.getNumero());
		newObj.setCidade(obj.getCidade());
		if(obj.isPrincipal()) {
			Endereco e2 = enderecoRepository.findByPersonTrue(newObj.getPerson().getId());
			if(e2 != null) {
				e2.setPrincipal(false);
				enderecoRepository.save(e2);
			}
		}
		newObj.setPrincipal(obj.isPrincipal());
		
		if(obj.getPerson()!=null) {
			newObj.setPerson(obj.getPerson());
		}
	}

	public Endereco fromDTO(EnderecoNewDTO objDTO) {
		Person p1 = personService.getById(objDTO.getPerson());
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getCep(), objDTO.getNumero(), objDTO.getCidade(), objDTO.isPrincipal(), p1);
		return end;
	}
	

}
