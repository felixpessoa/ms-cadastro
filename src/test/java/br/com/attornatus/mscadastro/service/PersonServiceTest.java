package br.com.attornatus.mscadastro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.attornatus.mscadastro.domain.model.Endereco;
import br.com.attornatus.mscadastro.domain.model.Person;
import br.com.attornatus.mscadastro.domain.repository.EnderecoRepository;
import br.com.attornatus.mscadastro.domain.repository.PersonRepository;
import br.com.attornatus.mscadastro.domain.service.PersonService;
import br.com.attornatus.mscadastro.domain.service.exceptions.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService personService;
	@Mock
	private PersonRepository personRepository;
	@Mock
	private EnderecoRepository enderecoRepository;
	
	@Captor
	ArgumentCaptor<Person> personCaptor;

	
	@Test
	@DisplayName("Salvando Person")
	void PersonSalvoComSucesso() {
		
		// arrange
		Person p1 = new Person(1L, "Andre Teste", LocalDate.now());
		
		Endereco e1 = new Endereco(1L, "Tiradentes", "68920-705", "4448", "Santana", true, p1);
		Endereco e2 = new Endereco(2L, "Maranhão", "76966-485", "6597", "Marituba", false, p1);
		
		p1.getEnderecos().addAll(Arrays.asList(e1, e2));

		Mockito.when(personRepository.save(p1)).thenReturn(p1);
		Mockito.when(enderecoRepository.saveAll(p1.getEnderecos())).thenReturn(p1.getEnderecos());
		
		// action
		personService.create(p1);
		
		
		// assertions
		Mockito.verify(enderecoRepository).saveAll(p1.getEnderecos());
		Mockito.verify(personRepository).save(personCaptor.capture());
		
		Person personSalva = personCaptor.getValue();
		
		assertEquals(1L, personSalva.getId());
		Assertions.assertThat(personSalva.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Lista de person salvas")
	void getAllPacienteTest() {
		List<Person> listPerson = new ArrayList<>();
		
		listPerson = personService.getAll();
		
		Mockito.verify(personRepository).findAll();
		
		Assertions.assertThat(listPerson).isNotNull();
		
	}
	
	@Test
	@DisplayName("pesquisar person por id")
	void getByIdPersonTest() {
	
		Person p1 = new Person(1L, "Andre Teste", LocalDate.now());
		
		Mockito.when(personRepository.findById(p1.getId())).thenReturn(Optional.of(p1));
		
		personService.getById(p1.getId());
		
		Mockito.verify(personRepository).findById(p1.getId());
		
		assertEquals(1L, p1.getId());
		Assertions.assertThat(p1).isNotNull();
		
	}
	
	@Test
	@DisplayName("retornar Person não encontrado por id.")
	void getByIdPersonNotCadastroTest() {
	
		Person p1 = new Person(1L, "Andre Teste", LocalDate.now());
		
		Mockito.when(personRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		
		ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
			personService.getById(p1.getId());
		});
		
		Assertions.assertThat(objectNotFoundException.getMessage()).isEqualTo("Cadastro não encontrado! 1, Tipo br.com.attornatus.mscadastro.domain.model.Person");
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
