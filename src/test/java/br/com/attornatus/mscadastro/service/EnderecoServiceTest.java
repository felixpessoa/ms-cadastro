package br.com.attornatus.mscadastro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
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
import br.com.attornatus.mscadastro.domain.service.EnderecoService;
import br.com.attornatus.mscadastro.domain.service.PersonService;
import br.com.attornatus.mscadastro.domain.service.exceptions.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {
	
	@InjectMocks
	private EnderecoService enderecoService;
	@Mock
	private EnderecoRepository enderecoRepository;
	@Mock
	private PersonService personService;
	
	@Captor
	ArgumentCaptor<Endereco> enderecoCaptor;
	
	@Test
	@DisplayName("Salvando Endereco")
	void EnderecoSalvoComSucesso() {
		Person p1 = new Person(1L, "Andre Teste", LocalDate.now());
		
		Endereco e1 = new Endereco(1L, "Tiradentes", "68920-705", "4448", "Santana", true, p1);
		
		enderecoService.create(e1);
		
		Mockito.verify(enderecoRepository).save(enderecoCaptor.capture());
		
		Endereco enderecoSalvo = enderecoCaptor.getValue();
		
		assertEquals(1L, enderecoSalvo.getId());
		Assertions.assertThat(enderecoSalvo.getId()).isNotNull();
		
		
	}
	
	@Test
	@DisplayName("Lista de endereco salvas")
	void getAllEnderecoTest() {
		List<Endereco> listEndereco = new ArrayList<>();
		
		listEndereco = enderecoService.getAll();
		
		Mockito.verify(enderecoRepository).findAll();
		
		Assertions.assertThat(listEndereco).isNotNull();
		
	}
	
	@Test
	@DisplayName("pesquisar endereco por id")
	void getByIdEnderecoTest() {
	
		Person p1 = new Person(1L, "Andre Teste", LocalDate.now());
		
		Endereco e1 = new Endereco(1L, "Tiradentes", "68920-705", "4448", "Santana", true, p1);
		
		Mockito.when(enderecoRepository.findById(e1.getId())).thenReturn(Optional.of(e1));
		
		enderecoService.getById(e1.getId());
		
		Mockito.verify(enderecoRepository).findById(e1.getId());
		
		assertEquals(1L, e1.getId());
		Assertions.assertThat(e1).isNotNull();
		
	}
	
	@Test
	@DisplayName("retornar endereço não encontrado por id.")
	void getByIdEnderecoNotCadastroTest() {
	
		Person p1 = new Person(1L, "Andre Teste", LocalDate.now());
		
		Endereco e1 = new Endereco(1L, "Tiradentes", "68920-705", "4448", "Santana", true, p1);
		
		Mockito.when(enderecoRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		
		ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
			enderecoService.getById(e1.getId());
		});
		
		Assertions.assertThat(objectNotFoundException.getMessage()).isEqualTo("Endereço não encontrado! 1, Tipo br.com.attornatus.mscadastro.domain.model.Endereco");
		
	}
	

}
