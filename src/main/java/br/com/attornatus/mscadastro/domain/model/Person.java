package br.com.attornatus.mscadastro.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	public Person() {
		super();
	}
	
	public Person(Long id, String nome, LocalDate dataDeNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}


	
	
}
